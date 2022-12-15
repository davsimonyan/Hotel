package am.itspace.hotel.endpoint;


import am.itspace.hotel.dto.RatingDto;
import am.itspace.hotel.service.impl.RatingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RatingEndpoint {

    private final RatingServiceImpl ratingServiceImpl;


    @PostMapping("/rating")
    public ResponseEntity<?> roomRating(@Valid
                                        @RequestBody RatingDto ratingDto) {
        ratingServiceImpl.rating(ratingDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
