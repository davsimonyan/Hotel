package am.itspace.hotel.service;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.entity.Room;

import java.util.List;

public interface RoomService {

    List<RoomDto> findAll();

    Room save(Room room);

    Room update(Room room);

    void removeById(int id);

    RoomDto findById(int id);

    void userBook(BookDto bookDto);

    void userBookDelete(BookDto bookDto);

}
