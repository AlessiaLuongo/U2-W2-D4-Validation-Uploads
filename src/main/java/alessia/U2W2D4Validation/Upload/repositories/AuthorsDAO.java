package alessia.U2W2D4Validation.Upload.repositories;

import alessia.U2W3D1.Spring.Web.and.Data.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsDAO extends JpaRepository<Author, Integer> {

}