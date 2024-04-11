package alessia.U2W2D4Validation.Upload.controllers;

import alessia.U2W2D4Validation.Upload.entities.Author;

import alessia.U2W2D4Validation.Upload.exceptions.BadRequestException;
import alessia.U2W2D4Validation.Upload.payloads.NewAuthorResponse;
import alessia.U2W2D4Validation.Upload.payloads.PayloadAuthor;
import alessia.U2W2D4Validation.Upload.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {


    @Autowired
    AuthorService authorService;


    @GetMapping
        public List<Author> getAllAuthors(){
            return this.authorService.getAuthorList();
        };

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private NewAuthorResponse saveAuthor(@RequestBody @Validated PayloadAuthor body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors().toString());
        }

        return new NewAuthorResponse(this.authorService.saveAuthor(body).getId());
    }

    @GetMapping("/{authorId}")
    private Author getSingleAuhtor(@PathVariable int authorId){
        return this.authorService.findAuthorById(authorId);
    }

    @PutMapping("/{authorId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private Author findSingleAuthorAndUpdate(@PathVariable int authorId, @RequestBody Author body){
        return this.authorService.findAuthorByIdAndUpdate(authorId, body);
    }
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteAuthor(@PathVariable int authorId){
        this.authorService.findAuthorByIdAndDelete(authorId);
    }


    @PostMapping("/upload")
    public String uploadAvatar(@RequestParam("avatar")MultipartFile image) throws IOException {
      return this.authorService.uploadImage(image);
    }
}
