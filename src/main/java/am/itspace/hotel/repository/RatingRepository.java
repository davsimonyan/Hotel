package am.itspace.hotel.repository;

import am.itspace.hotel.entiti.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Integer> {

  Optional<Rating> findRatingByUserIdAndRoomId(int userId, int roomId);
}
