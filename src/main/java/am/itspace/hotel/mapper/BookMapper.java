package am.itspace.hotel.mapper;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDto, Book>{
}
