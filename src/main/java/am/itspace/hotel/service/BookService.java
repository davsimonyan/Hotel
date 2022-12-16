package am.itspace.hotel.service;

import am.itspace.hotel.dto.BookDto;

import java.util.List;

public interface BookService {

    void userBook(BookDto bookDto);

    void userBookDelete(BookDto bookDto);

    List<BookDto> bookedCalendar(int roomId);
}
