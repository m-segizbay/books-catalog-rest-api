package kz.segizbay.bookscatalog.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kz.segizbay.bookscatalog.dto.BookDTO;
import kz.segizbay.bookscatalog.dto.ResponseBookDTO;
import kz.segizbay.bookscatalog.mapper.BookMapper;
import kz.segizbay.bookscatalog.model.Book;
import kz.segizbay.bookscatalog.service.BookService;
import kz.segizbay.bookscatalog.util.BookValidator;
import kz.segizbay.bookscatalog.util.ErrorsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final BookValidator bookValidator;

    @GetMapping
    @Operation(
            summary = "Берет все книги из базы",
            description = "Достает все книги из базы данных и конвертирует их с помощью Маппера и передает их клиенту."
    )
    public List<ResponseBookDTO> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Берет книгу через id",
            description = "Достает книгу через id из базы данных и конвертирует его помощью Маппера и передает их клиенту."
    )
    public ResponseBookDTO getById(@PathVariable("id") int id){
        return bookService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создает книгу",
            description = "Проверяет есть ли это книга в базе данных через название, если есть кидает ошибку. Так же добавляет дату создание"
    )
    public ResponseBookDTO addBook(@RequestBody @Valid BookDTO bookDTO,
                                   BindingResult bindingResult){
        bookValidator.validate(bookDTO, bindingResult);
        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);
        return bookService.save(bookMapper.toBook(bookDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Обновление книги",
            description = "Просто конвертует объект BookDTO который пришел в Book и сохраняет его в базе данных, так же ставить время обновление"
    )
    public ResponseBookDTO updateBook(@PathVariable("id") int id, @RequestBody @Valid BookDTO bookDTO,
                                      BindingResult bindingResult){
        bookValidator.validate(bookDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }
        return bookService.update(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удаление книги",
            description = "Удаляет книгу из базы данных"
    )
    public ResponseBookDTO deleteBook(@PathVariable int id){
        return bookService.delete(id);
    }
}
