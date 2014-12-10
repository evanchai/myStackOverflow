package com.stackoverflow.dao;

import java.util.List;

import com.stackoverflow.bean.Post;
public interface PostDAO {
	public List<Post> findPosts() throws Exception;
	public List<Post> findPosts(String keyword) throws Exception;
}
