package am.itspace.hotel.service;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.entiti.Room;
import am.itspace.hotel.entiti.User;
import am.itspace.hotel.entiti.enums.Status;
import am.itspace.hotel.exception.DuplicateResourceException;
import am.itspace.hotel.mapper.RoomMapper;
import am.itspace.hotel.repository.RoomRepository;
import am.itspace.hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final UserRepository userRepository;
    private final BookService bookService;


    public List<RoomDto> findAll() {
        List<RoomDto> roomDtos = roomMapper.toDto(roomRepository.findAll());
        System.out.println(roomDtos);
        return roomDtos;
    }


    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public Room update(Room room) {
        return roomRepository.save(room);
    }

    public void removeById(int id) {
        roomRepository.deleteById(id);
    }

    public void userBook(BookDto bookDto) {
        Room room = roomRepository.findById(bookDto.getRoom_id()).orElseThrow(DuplicateResourceException::new);
        User user = userRepository.findById(bookDto.getUser_id()).orElseThrow(DuplicateResourceException::new);
        if (room.getStatus().equals(Status.FREE)){
            bookService.book(bookDto);
            room.setUserEmail(user.getEmail());
            room.setStatus(Status.BUSY);
            roomRepository.save(room);
        }else {
            throw new DuplicateResourceException("chexav");
        }
    }
    public void userBookDelete(BookDto bookDto) {
        Room room = roomRepository.findById(bookDto.getRoom_id()).orElseThrow(DuplicateResourceException::new);
        User user = userRepository.findById(bookDto.getUser_id()).orElseThrow(DuplicateResourceException::new);
        if (room.getStatus().equals(Status.BUSY)){
            room.setUserEmail(null);
            room.setStatus(Status.FREE);
            roomRepository.save(room);
        }else {
            throw new DuplicateResourceException("chexav");
        }
    }

}
