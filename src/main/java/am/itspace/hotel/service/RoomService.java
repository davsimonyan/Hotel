package am.itspace.hotel.service;

import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.entity.Room;

import java.util.List;

public interface RoomService {

    List<RoomDto> findAll();

    RoomDto save(RoomDto roomDto);

    Room update(Room room);

    void removeById(int id);

    RoomDto findById(int id);


}
