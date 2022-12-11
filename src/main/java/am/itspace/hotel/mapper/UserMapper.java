package am.itspace.hotel.mapper;

import am.itspace.hotel.dto.UserDto;
import am.itspace.hotel.entiti.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
