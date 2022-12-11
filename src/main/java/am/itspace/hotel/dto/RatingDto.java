package am.itspace.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto {

    private int id;
    private int userId;
    private int roomId;
    private int ratings;

}
