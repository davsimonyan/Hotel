package am.itspace.hotel.service.impl;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.entity.Book;
import am.itspace.hotel.entity.Room;
import am.itspace.hotel.entity.User;
import am.itspace.hotel.entity.enums.Status;
import am.itspace.hotel.exception.DuplicateResourceException;
import am.itspace.hotel.exception.EntityNotFoundException;
import am.itspace.hotel.mapper.BookMapper;
import am.itspace.hotel.repository.BookRepository;
import am.itspace.hotel.repository.RoomRepository;
import am.itspace.hotel.repository.UserRepository;
import am.itspace.hotel.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    private BookDto book(BookDto bookDto) {
        if (bookDto.getInputDate().isBefore(LocalDate.now())) {
            throw new EntityNotFoundException("invalidate command");
        }else {
            Book book = bookMapper.toEntity(bookDto);
            Book save = bookRepository.save(book);
            return  bookMapper.toDto(save);
        }
    }

    public void userBook(BookDto bookDto) {
        Room room = roomRepository.findById(bookDto.getRoomId()).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(bookDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        if (room.getStatus().equals(Status.FREE)) {
            book(bookDto);
            room.setUserEmail(user.getEmail());
            room.setStatus(Status.BUSY);
            roomRepository.save(room);
        } else {
            throw new EntityNotFoundException("invalidate command");
        }

    }
    public void userBookDelete(BookDto bookDto) {
        Room room = roomRepository.findById(bookDto.getRoomId()).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(bookDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        if (room.getStatus().equals(Status.BUSY)) {
            room.setUserEmail(null);
            room.setStatus(Status.FREE);
            roomRepository.save(room);
        } else {
            throw new EntityNotFoundException("invalidate command");
        }
    }
}
