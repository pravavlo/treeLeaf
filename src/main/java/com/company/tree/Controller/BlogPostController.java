package com.company.tree.Controller;

import com.company.tree.Entity.BlogPost;
import com.company.tree.Entity.Request.BlogRequest;
import com.company.tree.Service.BlogPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/blog-posts")
public class BlogPostController {
    @Autowired
   private BlogPostService blogPostService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/fetchAllBlogs")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @PostMapping("/createBlogs")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        return blogPostService.createBlogPost(blogPost);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        BlogPost blogPost = restTemplate.getForObject("http://blog-service/blog-posts/" + id, BlogPost.class);
        return ResponseEntity.ok(blogPost);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')or hasRole('ADMIN')")
    public BlogPost updateBlogPost(@PathVariable Long id, @Valid @RequestBody BlogRequest blogRequest) {
        return blogPostService.updateBlogPost(id, blogRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
    }
}

