package alessia.U2W2D4Validation.Upload.services;

import alessia.U2W3D1.Spring.Web.and.Data.entities.Author;
import alessia.U2W3D1.Spring.Web.and.Data.exceptions.NotFoundException;
import alessia.U2W3D1.Spring.Web.and.Data.repositories.AuthorsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {
    @Autowired
    AuthorsDAO authorsDAO;


    public List<Author> getAuthorList() {
        return this.authorsDAO.findAll();
    }

    public Author saveAuthor(Author newAuthor) {

        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + newAuthor.getName() + "+" + newAuthor.getSurname());

        return authorsDAO.save(newAuthor);
    }


    public Author findAuthorById(int id){
      return authorsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public Author findAuthorByIdAndUpdate(int id, Author updatedBody) {
        Optional<Author> optionalAuthor = authorsDAO.findById(id);

        if (optionalAuthor.isPresent()) {
            Author found = optionalAuthor.get();
            found.setName(updatedBody.getName());
            found.setSurname(updatedBody.getSurname());
            found.setBirthdayYear(updatedBody.getBirthdayYear());
            return this.authorsDAO.save(found);
        } else {
            throw new NotFoundException(id);
        }
    }

    public void findAuthorByIdAndDelete(int id){
        Optional<Author> optionalAuthor = authorsDAO.findById(id);
        if (optionalAuthor.isPresent()) {
            Author found = optionalAuthor.get();
           this.authorsDAO.delete(found);
        } else {
            throw new NotFoundException(id);
        }
    }


}



