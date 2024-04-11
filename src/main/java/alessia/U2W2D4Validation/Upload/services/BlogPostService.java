package alessia.U2W2D4Validation.Upload.services;

import alessia.U2W3D1.Spring.Web.and.Data.entities.Author;
import alessia.U2W3D1.Spring.Web.and.Data.entities.BlogPost;
import alessia.U2W3D1.Spring.Web.and.Data.exceptions.NotFoundException;
import alessia.U2W3D1.Spring.Web.and.Data.payloads.PayloadBlogPost;
import alessia.U2W3D1.Spring.Web.and.Data.repositories.AuthorsDAO;
import alessia.U2W3D1.Spring.Web.and.Data.repositories.BlogPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private AuthorsDAO authorsDAO;
    @Autowired
    BlogPostDAO blogPostDAO;



    public List<BlogPost> getBlogPostList(){
        return this.blogPostDAO.findAll();
    }

    public BlogPost saveBlogPost(PayloadBlogPost body) {
        Author author = authorsDAO.findById(body.getAuthorId())
                .orElseThrow(() -> new NotFoundException(body.getAuthorId()));

        BlogPost newPost = new BlogPost(body.getCategory(), body.getTitle(), body.getCover(), body.getContent(), body.getReadingTime());
        newPost.setAuthor(author);

        return this.blogPostDAO.save(newPost);
    }
    public BlogPost findById(int id){
        return blogPostDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }
    public BlogPost findByIdAndUpdate(int id, BlogPost updatedBlogPost){
        Optional<BlogPost> optionalBlogPost = blogPostDAO.findById(id);

        if (optionalBlogPost.isPresent()) {
            BlogPost found = optionalBlogPost.get();
            found.setAuthor(optionalBlogPost.get().getAuthor());
            found.setTitle(optionalBlogPost.get().getTitle());
            found.setCategory(optionalBlogPost.get().getCategory());
            found.setContent(optionalBlogPost.get().getContent());
            return this.blogPostDAO.save(found);
        } else {
            throw new NotFoundException(id);
        }
    }

    public void findByIdAndDelete(int id){
        Optional<BlogPost> optionalBlogPost = blogPostDAO.findById(id);
        if (optionalBlogPost.isPresent()) {
            BlogPost found = optionalBlogPost.get();
            this.blogPostDAO.delete(found);
        } else {
            throw new NotFoundException(id);
        }

    }

}
