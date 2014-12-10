package com.stackoverflow.bean;

public class Comment {
	public int id;
	public int postId;
	public String comment_text;
	
	public Comment (int id, int postId,String comment_text)
	{
		this.id= id;
		this.postId = postId;
		this.comment_text = comment_text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
}
