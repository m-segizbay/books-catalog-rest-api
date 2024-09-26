package kz.segizbay.bookscatalog.mapper;

import kz.segizbay.bookscatalog.dto.BookDTO;
import kz.segizbay.bookscatalog.dto.ResponseBookDTO;
import kz.segizbay.bookscatalog.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    BookDTO toBookDTO(Book book);
    Book toBook(BookDTO bookDTO);
    ResponseBookDTO toResponseBookDto(Book book);
    List<ResponseBookDTO> toResponseBookDTOList(List<Book> books);
}