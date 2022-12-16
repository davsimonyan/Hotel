package am.itspace.hotel.service;

import am.itspace.hotel.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    void save(UserDto userDto);

    void removeById(int id);

    UserDto findById(int id);

}
