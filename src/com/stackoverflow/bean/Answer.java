package com.stackoverflow.bean;

public class Answer extends Post
{
	
	public Answer(int postId,int post_typeId,String post_title,String post_body,String post_tag,int post_comment_count,int parentId,int post_answer_count,int accepted_answerId)
	{
		super(postId, post_typeId, post_title, post_body, post_tag, post_comment_count, parentId, post_answer_count, accepted_answerId);
	}	
}