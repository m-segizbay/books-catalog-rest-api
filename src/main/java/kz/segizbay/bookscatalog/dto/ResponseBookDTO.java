package kz.segizbay.bookscatalog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseBookDTO {
    private int id;

    private String title;

    private String author;

    private BigDecimal price;
}
