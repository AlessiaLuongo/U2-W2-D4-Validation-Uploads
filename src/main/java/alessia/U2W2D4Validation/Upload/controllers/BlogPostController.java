package alessia.U2W2D4Validation.Upload.controllers;


import alessia.U2W2D4Validation.Upload.entities.BlogPost;
import alessia.U2W2D4Validation.Upload.payloads.PayloadBlogPost;
import alessia.U2W2D4Validation.Upload.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog-posts")
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;

    @GetMapping
    private List<BlogPost> getAllBlogPosts(){
        return this.blogPostService.getBlogPostList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogPost(@RequestBody PayloadBlogPost payload){
        return blogPostService.saveBlogPost(payload);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost getSingleBlogPost(@PathVariable int blogPostId){
    return this.blogPostService.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BlogPost findSinglePostAndUpdate(@PathVariable int blogPostId, @RequestBody BlogPost body){
        return this.blogPostService.findByIdAndUpdate(blogPostId, body);
    }
    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable int blogPostId){
        this.blogPostService.findByIdAndDelete(blogPostId);
    }

}
