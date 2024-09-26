package kz.segizbay.bookscatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleBookNotFoundException(BookNotFoundException ex) {
        BookErrorResponse bookErrorResponse = new BookErrorResponse(
                ex.getMessage()
        );

        return new ResponseEntity<>(bookErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleBookValidException(BookValidException ex) {
        BookErrorResponse bookErrorResponse = new BookErrorResponse(
                ex.getMessage()
        );

        return new ResponseEntity<>(bookErrorResponse, HttpStatus.BAD_REQUEST);
    }
}