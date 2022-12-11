package am.itspace.hotel.mapper;

import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.entiti.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper extends EntityMapper<RoomDto, Room>{
}
