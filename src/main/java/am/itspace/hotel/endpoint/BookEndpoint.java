package am.itspace.hotel.endpoint;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookEndpoint {

    private final RoomService roomService;

    @PostMapping("/book")
    public ResponseEntity<?> roomBook(@RequestBody BookDto bookDto) {
        roomService.userBook(bookDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
