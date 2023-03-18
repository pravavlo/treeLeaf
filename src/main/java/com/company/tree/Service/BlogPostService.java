package com.company.tree.Service;

import com.company.tree.Entity.BlogPost;
import com.company.tree.Entity.Request.BlogRequest;

import java.util.List;
import java.util.Optional;

public interface BlogPostService {

    List<BlogPost> getAllBlogPosts();

    BlogPost createBlogPost(BlogPost blogPost);

    Optional<BlogPost> getBlogPostById(Long id);

    BlogPost updateBlogPost(Long id, BlogRequest blogRequest);

    void deleteBlogPost(Long id);
}
