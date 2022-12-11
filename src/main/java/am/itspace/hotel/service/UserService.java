package am.itspace.hotel.service;

import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.dto.UserAuthDto;
import am.itspace.hotel.dto.UserAuthResponseDto;
import am.itspace.hotel.dto.UserDto;
import am.itspace.hotel.entiti.User;
import am.itspace.hotel.entiti.enums.UserType;
import am.itspace.hotel.exception.DuplicateResourceException;
import am.itspace.hotel.mapper.UserMapper;
import am.itspace.hotel.repository.UserRepository;
import am.itspace.hotel.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
//
//    public void save(UserDto userDto) throws DuplicateResourceException {
//        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
//            throw new DuplicateResourceException("User already exists!");
//        }
//        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        userDto.setUserType(UserType.USER);
//        userDto.setEnable(false);
//        User user = userMapper.toEntity(userDto);
//        userRepository.save(user);
//
//    }

    public List<UserDto> findAll() {
        List<UserDto> userDtos = userMapper.toDto(userRepository.findAll());
        System.out.println(userDtos);
        return userDtos;
    }

    public void save(UserDto userDto) throws DuplicateResourceException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User already exists!");
        }
        userDto.setUserType(UserType.USER);
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    public boolean login(UserDto userDto) throws DuplicateResourceException{
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if (byEmail.isPresent() && userDto.getPassword().equals(byEmail.get().getPassword())) {
            return true;
       } return false;

    }
    public void removeById(int id) {
        userRepository.deleteById(id);
    }


}
