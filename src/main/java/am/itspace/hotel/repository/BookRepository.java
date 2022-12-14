package am.itspace.hotel.repository;


import am.itspace.hotel.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findAllByRoomId(int id);

    List<Book> findAllByRoomIdAndInputDateAfter(int roomId, LocalDate localDate);
}
