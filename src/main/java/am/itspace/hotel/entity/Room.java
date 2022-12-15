package am.itspace.hotel.entity;

import am.itspace.hotel.entity.enums.RoomType;
import am.itspace.hotel.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int number;
    private String description;
    @Min(value = 1)
    @Max(value = 5)
    private int rating;
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;
    private double price;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String userEmail;
}

