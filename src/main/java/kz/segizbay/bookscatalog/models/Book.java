package kz.segizbay.bookscatalog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @Column(name = "title")
    @Size(min = 2, max = 200, message = "Book titile should be between 3 and 200 characters!")
    private String title;

    @Column(name = "id")
    @Min(value = 1600, message = "Year of release be greater than 1600")
    private int yearOfRelease;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToMany(mappedBy = "")
    private List<Genre> genres;

}
