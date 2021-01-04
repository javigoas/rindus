package com.rindus.service;

import com.rindus.entities.Comments;
import com.rindus.entities.Posts;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RindusService {
    ResponseEntity<List<Posts>> getAllPosts();

    ResponseEntity<Posts> getPostsById(String id);

    ResponseEntity<List<Comments>> getCommentsFromPost(String id);

    ResponseEntity<List<Comments>> getCommentsByPostId(String postId);

    ResponseEntity<Posts> insertPosts(Posts posts);

    ResponseEntity<Posts> changePostsById(String id, Posts posts);

    ResponseEntity<Posts> deletePostsById(String id);

    ResponseEntity<Posts> modifyPost(Posts posts);
}
