package am.itspace.hotel.endpoint;

import am.itspace.hotel.dto.BookDto;
import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.entiti.Room;
import am.itspace.hotel.mapper.RoomMapper;
import am.itspace.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class RoomEndpoint {

    private final RoomService roomService;
    private final RoomMapper roomMapper;


    //    public List<HotelDto> findAllRoom(int id) {
//        roomService.roomId(id);
//
//        List<HotelDto> hotelDtos = hotelMapper.toDto(hotelRepository.findAll());
//        System.out.println(hotelDtos);
//        return hotelDtos;
//    }
    @GetMapping("/room")
    public ResponseEntity<List<RoomDto>> getAllRoom() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @PutMapping("/roomUpdate")
    public ResponseEntity<?> createUpdate(@RequestBody RoomDto roomDto) {
        Room updateRoom = roomService.update(roomMapper.toEntity(roomDto));
        return ResponseEntity.ok(updateRoom);
    }

    @PostMapping("/room")
    public ResponseEntity<?> createRoom(@RequestBody RoomDto roomDto) {
        Room saveRoom = roomService.save(roomMapper.toEntity(roomDto));
        return ResponseEntity.ok(saveRoom);
    }

    @DeleteMapping("/room/{id}")
    public void deleteRoom(@PathVariable("id") int id) {
        roomService.removeById(id);
    }


    @PostMapping("/user/{roomId}/book/{userId}")
    public ResponseEntity<?> roomBookDelete(@RequestBody BookDto bookDto) {
        roomService.userBookDelete(bookDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
