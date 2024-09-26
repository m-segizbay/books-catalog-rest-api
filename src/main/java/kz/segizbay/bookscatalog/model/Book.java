package kz.segizbay.bookscatalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Setter
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", unique = true)
    @Size(min = 3, max = 200, message = "Название книги должен иметь более чем 2 символа или же менее 200!")
    private String title;

    @Column(name = "author")
    @NotNull(message = "Имя автора не должна быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должен иметь более чем 2 символа или же менее 100!")
    private String author;

    @NotNull(message = "Цена не должна быть пустым")
    @Min(value = 0, message = "Цена должна быть выше чем 0")
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Book(String title, String author, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
