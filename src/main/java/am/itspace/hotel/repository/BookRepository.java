package am.itspace.hotel.repository;


import am.itspace.hotel.entiti.Book;
import am.itspace.hotel.entiti.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
