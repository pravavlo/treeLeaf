package com.company.tree.Service.BlogPostServiceImpl;

import com.company.tree.Entity.BlogPost;
import com.company.tree.Entity.Request.BlogRequest;
import com.company.tree.Repository.BlogPostRepository;
import com.company.tree.Service.BlogPostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;




    @Override
    public List<BlogPost> getAllBlogPosts() {
        try {
            return blogPostRepository.findAll();
        }catch (Exception e){
            log.error(e);
            throw new RuntimeException("there is nothing to fetch");
        }

    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost)  {
        blogPost.setCreatedAt(new Date());
        return blogPostRepository.save(blogPost);
    }

    @Override
    public Optional<BlogPost> getBlogPostById(Long id) {
        try {
            return blogPostRepository.findById(id);
        }catch(Exception e ){
         throw new RuntimeException("Blog post not found with id: " + id);
    }}

    @Override
    public BlogPost updateBlogPost(Long id, BlogRequest blogRequest) {
        BlogPost existingBlogPost = blogPostRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Blog post not found with id: " + id));
        existingBlogPost.setTitle(blogRequest.getTitle());
        existingBlogPost.setContent(blogRequest.getContent());
        existingBlogPost.setUpdatedAt(new Date());
        return blogPostRepository.save(existingBlogPost);
    }

    @Override
    public void deleteBlogPost(Long id) {
        BlogPost existingBlogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog post not found with id: " + id));
        blogPostRepository.delete(existingBlogPost);
    }
}

