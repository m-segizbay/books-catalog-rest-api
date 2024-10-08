package kz.segizbay.bookscatalog.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ResponseBookDTO {
    private int id;

    private String title;

    private String author;

    private BigDecimal price;

    private String description;
}
