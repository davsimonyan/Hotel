package am.itspace.hotel.dto;

import am.itspace.hotel.entity.enums.RoomType;
import am.itspace.hotel.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private int id;
    private int number;
    private String description;
    private int rating;
    private RoomType roomType;
    private double price;
    private Status status;
    private String userEmail;
}
