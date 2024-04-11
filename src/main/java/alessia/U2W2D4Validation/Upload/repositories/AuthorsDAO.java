package alessia.U2W2D4Validation.Upload.repositories;


import alessia.U2W2D4Validation.Upload.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsDAO extends JpaRepository<Author, Integer> {

}