package am.itspace.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private int id;
    private int userId;
    private int roomId;
    private LocalDate inputDate;
    private LocalDate exitDate;

}
