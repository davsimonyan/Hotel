package am.itspace.hotel.endpoint;

import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.entity.Room;
import am.itspace.hotel.mapper.RoomMapper;
import am.itspace.hotel.service.RoomService;
import am.itspace.hotel.service.impl.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class RoomEndpoint {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping("/room")
    public ResponseEntity<List<RoomDto>> getAllRoom() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @PutMapping("/roomUpdate")
    public ResponseEntity<?> createUpdate(@RequestBody RoomDto roomDto) {
        Room updateRoom = roomService.update(roomMapper.toEntity(roomDto));
        return ResponseEntity.ok(updateRoom);
    }

    @PostMapping("/room/add")
    public ResponseEntity<?> createRoom(@RequestBody RoomDto roomDto) {
        RoomDto saveRoom = roomService.save(roomDto);
        return ResponseEntity.ok(saveRoom);
    }

    @DeleteMapping("/room/{id}")
    public void deleteRoom(@PathVariable("id") int id) {
        roomService.removeById(id);
    }


}
