package am.itspace.hotel.dto;

import am.itspace.hotel.entity.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserType userType;
    private boolean isEnable;

}
