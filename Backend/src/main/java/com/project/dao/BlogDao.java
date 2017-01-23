package com.project.dao;

import java.util.List;

import com.project.model.BlogComment;
import com.project.model.BlogPost;
import com.project.model.User;



public interface BlogDao {
	List<BlogPost> getBlogPosts();
	BlogPost getBlogPost(int id);
	BlogPost addBlogPost(User user,BlogPost blogPost);
	List<BlogComment> getBlogComments(int blogId);
	BlogPost addBlogPostComment(User user,BlogComment blogComment);
}
