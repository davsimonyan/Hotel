package am.itspace.hotel.service;

import am.itspace.hotel.dto.BookDto;

public interface BookService {

    void userBook(BookDto bookDto);

    void userBookDelete(BookDto bookDto);

}
