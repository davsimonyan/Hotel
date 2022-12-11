package am.itspace.hotel.endpoint;


import am.itspace.hotel.dto.RatingDto;
import am.itspace.hotel.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatingEndpoint {

    private final RatingService ratingService;


    @PostMapping("/rating")
    public ResponseEntity<?> roomRating(@RequestBody RatingDto ratingDto) {
        ratingService.rating(ratingDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
