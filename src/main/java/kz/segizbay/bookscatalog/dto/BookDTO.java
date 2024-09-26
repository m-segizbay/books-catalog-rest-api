package kz.segizbay.bookscatalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookDTO {
    @Size(min = 2, max = 200, message = "Название книги должен иметь более чем 2 символа или же менее 200!")
    private String title;

    @NotNull(message = "Имя автора не должна быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должен иметь более чем 2 символа или же менее 100!")
    private String author;

    @Min(value = 0, message = "Цена должна быть выше чем 0")
    private BigDecimal price;

    private String description;
}
