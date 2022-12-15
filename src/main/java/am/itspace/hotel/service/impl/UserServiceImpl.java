package am.itspace.hotel.service.impl;

import am.itspace.hotel.dto.UserDto;
import am.itspace.hotel.entity.User;
import am.itspace.hotel.entity.enums.UserType;
import am.itspace.hotel.exception.DuplicateResourceException;
import am.itspace.hotel.mapper.UserMapper;
import am.itspace.hotel.repository.UserRepository;
import am.itspace.hotel.service.UserService;
import am.itspace.hotel.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;


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

//    public ResponseEntity<?> login(@RequestBody UserAuthDto userAuthDto){
//        Optional<User> byEmail = userRepository.findByEmail(userAuthDto.getEmail());
//        if (byEmail.isPresent()) {
//            User user = byEmail.get();
//            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
//                return ResponseEntity.ok(UserAuthResponseDto.builder()
//                        .token(jwtTokenUtil.generateToken(user.getEmail()))
//                        .user(userMapper.toDto(user))
//                        .build()
//                );
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//
//    }



    public void removeById(int id) {
        userRepository.deleteById(id);
    }


}
