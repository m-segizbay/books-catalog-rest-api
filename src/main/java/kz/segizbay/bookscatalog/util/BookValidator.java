package kz.segizbay.bookscatalog.util;

import kz.segizbay.bookscatalog.dto.BookDTO;
import kz.segizbay.bookscatalog.model.Book;
import kz.segizbay.bookscatalog.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator {
    private final BookService bookService;

    @Override
    public boolean supports(Class<?> clazz) {
        return BookDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDTO bookDTO = (BookDTO) target;
        if (bookService.getByTitle(bookDTO.getTitle()).isPresent()){
            errors.rejectValue("title", "", "Книга с таким названием уже существует!");
        }
    }
}
