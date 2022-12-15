package am.itspace.hotel.endpoint;

import am.itspace.hotel.dto.UserAuthDto;
import am.itspace.hotel.dto.UserAuthResponseDto;
import am.itspace.hotel.dto.UserDto;
import am.itspace.hotel.entity.User;
import am.itspace.hotel.mapper.UserMapper;
import am.itspace.hotel.repository.UserRepository;
import am.itspace.hotel.service.UserService;
import am.itspace.hotel.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class UserEndpoint {


    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/user/add")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/user/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userRepository.findByEmail(userAuthDto.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(UserAuthResponseDto.builder()
                        .token(jwtTokenUtil.generateToken(user.getEmail()))
                        .user(userMapper.toDto(user))
                        .build()
                );
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.removeById(id);

    }

}
