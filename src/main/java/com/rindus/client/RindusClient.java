package com.rindus.client;


import com.rindus.entities.Posts;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface RindusClient {
    ResponseEntity<String> getAllPosts();

    ResponseEntity<Posts> getPostsById(String id);

    ResponseEntity<String> getCommentsFromPost(String id);

    HttpEntity<String> getCommentsByPostId(String postId);

    ResponseEntity<String> insertPosts(Posts posts);

    void changePostsById(String id, Posts posts);

    String modifyPosts(Posts posts);

    void deletePostsById(String id);
}
