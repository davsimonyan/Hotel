package am.itspace.hotel.entiti;

import am.itspace.hotel.entiti.enums.RoomType;
import am.itspace.hotel.entiti.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private int rating;
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;
    private double price;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String userEmail;
}

