package am.itspace.hotel.endpoint;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookEndpoint {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> roomBook(@RequestBody BookDto bookDto) {
        bookService.userBook(bookDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> bookDelete(@RequestBody BookDto bookDto) {
        bookService.userBookDelete(bookDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<List<BookDto>> calendar(@PathVariable int id){
        return ResponseEntity.ok(bookService.bookedCalendar(id));
    }

}
