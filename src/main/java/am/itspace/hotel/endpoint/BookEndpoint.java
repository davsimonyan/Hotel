package am.itspace.hotel.endpoint;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.service.BookService;
import am.itspace.hotel.service.RoomService;
import am.itspace.hotel.service.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookEndpoint {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> roomBook(@RequestBody BookDto bookDto) {
        bookService.userBook(bookDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/book/delete")
    public ResponseEntity<?> roomBookDelete(@RequestBody BookDto bookDto) {
        bookService.userBookDelete(bookDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
