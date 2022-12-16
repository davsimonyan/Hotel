package am.itspace.hotel.service.impl;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.dto.UserDto;
import am.itspace.hotel.entity.Book;
import am.itspace.hotel.entity.enums.Status;
import am.itspace.hotel.exception.EntityNotFoundException;
import am.itspace.hotel.exception.RoomAlreadyBookedExeption;
import am.itspace.hotel.mapper.BookMapper;
import am.itspace.hotel.repository.BookRepository;
import am.itspace.hotel.service.BookService;
import am.itspace.hotel.service.RoomService;
import am.itspace.hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final RoomService roomService;
    private final UserService userService;

    private void book(BookDto bookDto) {
        roomService.findById(bookDto.getRoomId());
        List<Book> allByRoomId = bookRepository.findAllByRoomId(bookDto.getRoomId());
        if (bookDto.getInputDate().isBefore(LocalDate.now())) {
            throw new RoomAlreadyBookedExeption("you can not book room by invalid date");
        } else {
            for (Book book : allByRoomId) {
                if (bookDto.getInputDate().isAfter(book.getInputDate()) &&
                        bookDto.getInputDate().isBefore(book.getExitDate())) {
                    throw new RoomAlreadyBookedExeption("In that days room already booked");
                } else if (bookDto.getExitDate().isAfter(book.getInputDate()) &&
                        bookDto.getExitDate().isBefore(book.getExitDate()) || bookDto.getExitDate().equals(book.getExitDate())) {
                    throw new RoomAlreadyBookedExeption("In that days room already booked");
                }
            }
            bookRepository.save(bookMapper.toEntity(bookDto));
        }
    }


    public void userBook(BookDto bookDto) {
        RoomDto room = roomService.findById(bookDto.getRoomId());
        UserDto user = userService.findById(bookDto.getUserId());
        if (room.getStatus().equals(Status.FREE)) {
            book(bookDto);
            room.setUserEmail(user.getEmail());
            if (bookDto.getInputDate().equals(LocalDate.now())) {
                room.setStatus(Status.BUSY);
                roomService.save(room);
            }
        } else {
            throw new RoomAlreadyBookedExeption("room already booked");
        }

    }

    public void userBookDelete(BookDto bookDto) {
        RoomDto room = roomService.findById(bookDto.getRoomId());
        if (room.getStatus().equals(Status.BUSY)) {
            room.setUserEmail(null);
            room.setStatus(Status.FREE);
            roomService.save(room);
            bookRepository.deleteById(bookDto.getId());
        } else {
            throw new EntityNotFoundException("invalidate command");
        }
    }

    @Override
    public List<BookDto> bookedCalendar(int roomId) {
        return bookRepository.findAllByRoomIdAndInputDateAfter(roomId,LocalDate.now())
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}
