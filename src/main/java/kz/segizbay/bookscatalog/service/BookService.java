package kz.segizbay.bookscatalog.service;

import kz.segizbay.bookscatalog.dto.BookDTO;
import kz.segizbay.bookscatalog.dto.ResponseBookDTO;
import kz.segizbay.bookscatalog.exception.BookNotFoundException;
import kz.segizbay.bookscatalog.mapper.BookMapper;
import kz.segizbay.bookscatalog.model.Book;
import kz.segizbay.bookscatalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional
    public ResponseBookDTO save(Book book) {
        bookRepository.save(addCreatedAt(book));
        log.info("Книга создана в базе данных");
        return bookMapper.toResponseBookDto(book);
    }

    private Book addCreatedAt(Book book){
        book.setCreatedAt(LocalDateTime.now());
        return book;
    }

    public List<ResponseBookDTO> getAll() {
        List<Book> books = bookRepository.findAll(Sort.by("id"));
        return bookMapper.toResponseBookDTOList(books);
    }

    public ResponseBookDTO getById(int id) {
        Book book = findByBookId(id);
        return bookMapper.toResponseBookDto(book);
    }

    public Optional<Book> getByTitle(String title){
        return  bookRepository.findBookByTitle(title);
    }

    @Transactional
    public ResponseBookDTO update(int id, BookDTO bookDTO) {
        Book book = findByBookId(id);
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(book);
        log.info("Книга обновилось в базе данных");
        return bookMapper.toResponseBookDto(book);
    }

    @Transactional
    public ResponseBookDTO delete(int id) {
        Book book = findByBookId(id);
        bookRepository.deleteById(id);
        log.info("Книга удалена из база данных");
        return bookMapper.toResponseBookDto(book);
    }

    private Book findByBookId(int id){
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(String.format("Книга с таким id(%d) не найдено", id)));
    }
}
