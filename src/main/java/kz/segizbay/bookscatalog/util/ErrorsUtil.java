package kz.segizbay.bookscatalog.util;

import kz.segizbay.bookscatalog.exception.BookValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorUtil {
    public static void returnErrorsToClient(BindingResult bindingResult){
        StringBuilder errorsMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors){
            errorsMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        throw new BookValidException(errorsMsg.toString());
    }
}
