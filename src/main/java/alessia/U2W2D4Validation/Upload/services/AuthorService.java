package alessia.U2W2D4Validation.Upload.services;

import alessia.U2W2D4Validation.Upload.entities.Author;
import alessia.U2W2D4Validation.Upload.exceptions.NotFoundException;
import alessia.U2W2D4Validation.Upload.payloads.NewAuthorResponse;
import alessia.U2W2D4Validation.Upload.payloads.PayloadAuthor;
import alessia.U2W2D4Validation.Upload.repositories.AuthorsDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Service
public class AuthorService {
    @Autowired
    AuthorsDAO authorsDAO;
    @Autowired
    Cloudinary cloudinaryUploader;


    public Page<Author> getAuthorList(int page, int size, String sortBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorsDAO.findAll(pageable);
    }

    public Author saveAuthor(PayloadAuthor body) {
        Author newAuthor = new Author(body.name(), body.surname(), body.eMail(), body.birthdayYear(), "https://ui-avatars.com/api/?name="+ body.name() + "+" + body.surname());
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

    public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }

}



