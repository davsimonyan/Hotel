package am.itspace.hotel.repository;

import am.itspace.hotel.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Integer> {

  @Query(nativeQuery = true,value = "select avg(rating) from rating where room_id=:id")
  Double ratingAvg(@Param("id")int id);

  Optional<Rating> findRatingByUserIdAndRoomId(int userId, int roomId);
}
