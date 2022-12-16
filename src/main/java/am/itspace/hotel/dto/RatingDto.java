package am.itspace.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class RatingDto {

    private int id;
    private int userId;
    private int roomId;
    @Min(value = 1, message = "Value should be greater then equal to 1")
    @Max(value = 5, message = "Value should be less then equal to 5")
    @NotNull(message = "rating may not be null")
    private Integer rating;

}
