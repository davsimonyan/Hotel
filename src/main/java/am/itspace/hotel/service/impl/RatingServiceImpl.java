package am.itspace.hotel.service.impl;

import am.itspace.hotel.dto.RatingDto;
import am.itspace.hotel.dto.RoomDto;
import am.itspace.hotel.entity.Rating;
import am.itspace.hotel.mapper.RatingMapper;
import am.itspace.hotel.mapper.RoomMapper;
import am.itspace.hotel.repository.RatingRepository;
import am.itspace.hotel.repository.RoomRepository;
import am.itspace.hotel.repository.UserRepository;
import am.itspace.hotel.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {


    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomServiceImpl roomServiceImpl;

    public void rating(RatingDto ratingDto) {
        if (userRepository.findById(ratingDto.getUserId()).isPresent()
                && roomRepository.findById(ratingDto.getRoomId()).isPresent()) {
            Optional<Rating> rating = ratingRepository.findRatingByUserIdAndRoomId(ratingDto.getUserId(),
                    ratingDto.getRoomId());
            if (rating.isPresent()) {
                rating.get().setRating(ratingDto.getRating());
                ratingRepository.save(rating.get());
                RoomDto byId = roomServiceImpl.findById(ratingDto.getRoomId());
                Double id = ratingRepository.ratingAvg(byId.getId());
                byId.setRating(id.intValue());
                roomServiceImpl.save(byId);
            } else {
                ratingRepository.save(ratingMapper.toEntity(ratingDto));
                RoomDto byId = roomServiceImpl.findById(ratingDto.getRoomId());
                Double id = ratingRepository.ratingAvg(byId.getId());
                byId.setRating(id.intValue());
                roomServiceImpl.save(byId);
            }
        }
    }
}
