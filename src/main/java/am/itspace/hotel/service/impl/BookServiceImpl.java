package am.itspace.hotel.service.impl;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.entity.Book;
import am.itspace.hotel.exception.DuplicateResourceException;
import am.itspace.hotel.mapper.BookMapper;
import am.itspace.hotel.repository.BookRepository;
import am.itspace.hotel.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookDto book(BookDto bookDto) {
        if (bookDto.getInputDate().isBefore(LocalDate.now())) {
            throw new DuplicateResourceException("chexav");
        }else {
            Book book = bookMapper.toEntity(bookDto);
            Book save = bookRepository.save(book);
            return  bookMapper.toDto(save);
        }
    }
}
