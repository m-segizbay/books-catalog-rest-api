package kz.segizbay.bookscatalog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    @Size(min = 2, max = 100, message = "Author full name should be between 3 and 100 characters!")
    private String fullName;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Author year of birth be greater than 1900")
    private int yearOfBirth;

    @Column(name = "email")
    @NotEmpty(message = "Email should be not null!")
    @Email
    private String email;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(String fullName, int yearOfBirth, String email, List<Book> books) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.books = books;
    }
}
