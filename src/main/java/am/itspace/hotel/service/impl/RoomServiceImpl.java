package am.itspace.hotel.service.impl;

import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.entity.Room;
import am.itspace.hotel.exception.EntityNotFoundException;
import am.itspace.hotel.mapper.RoomMapper;
import am.itspace.hotel.repository.RoomRepository;
import am.itspace.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;


    public List<RoomDto> findAll() {
        List<RoomDto> roomDtos = roomMapper.toDto(roomRepository.findAll());
        System.out.println(roomDtos);
        return roomDtos;
    }


    public RoomDto save(RoomDto roomDto) {
        Room saved = roomRepository.save(roomMapper.toEntity(roomDto));
        return roomMapper.toDto(saved);
    }

    public Room update(Room room) {
        return roomRepository.save(room);
    }

    public void removeById(int id) {
        roomRepository.deleteById(id);
    }

    public RoomDto findById(int id) {
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()) {
            return roomMapper.toDto(byId.get());
        } else {
            throw new EntityNotFoundException("entity not found");
        }
    }


}
