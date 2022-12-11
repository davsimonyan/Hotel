package am.itspace.hotel.service;

import am.itspace.hotel.dto.RatingDto;
import am.itspace.hotel.entiti.Rating;
import am.itspace.hotel.mapper.RatingMapper;
import am.itspace.hotel.repository.RatingRepository;
import am.itspace.hotel.repository.RoomRepository;
import am.itspace.hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {


    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

//    public void rating(RatingDto ratingDto) {
//        if (ratingRepository.findById(ratingDto.getUserId()).isPresent()
//                && ratingRepository.findById(ratingDto.getRoomId()).isPresent()) {
//            Optional<Rating> rating = ratingRepository.findRatingByUserIdAndRoomId(ratingDto.getUserId(),
//                    ratingDto.getRoomId());
//            if (rating.isPresent()) {
//                rating.get().setRatings(ratingDto.getRatings());
//                ratingRepository.save(rating.get());
//            } else {
//                ratingRepository.save(ratingMapper.toEntity(ratingDto));
//            }
//        }
//    }
    public void rating(RatingDto ratingDto) {
        if (userRepository.findById(ratingDto.getUserId()).isPresent()
                && roomRepository.findById(ratingDto.getRoomId()).isPresent()) {
            Optional<Rating> rating = ratingRepository.findRatingByUserIdAndRoomId(ratingDto.getUserId(),
                    ratingDto.getRoomId());
            if (rating.isPresent()) {
                rating.get().setRatings(ratingDto.getRatings());
                ratingRepository.save(rating.get());
            } else {
                ratingRepository.save(ratingMapper.toEntity(ratingDto));
            }
        }
    }
}
