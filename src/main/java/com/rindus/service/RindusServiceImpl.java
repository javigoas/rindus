package com.rindus.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rindus.client.RindusClient;
import com.rindus.entities.Comments;
import com.rindus.entities.Posts;
import com.rindus.handlers.Rindus5Exception;
import javafx.geometry.Pos;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RindusServiceImpl implements RindusService {

    @Autowired
    RindusClient client;

    @Autowired
    ObjectMapper mapper;

    @SneakyThrows
    @Override
    public ResponseEntity<List<Posts>> getAllPosts() {
        ResponseEntity<String> allPosts = client.getAllPosts();
        List<Posts> postsList = mapper.readValue(allPosts.getBody(), ArrayList.class);
        return ResponseEntity.ok(postsList);
    }

    @Override
    public ResponseEntity<Posts> getPostsById(String id) {
        ResponseEntity<Posts> postsById = client.getPostsById(id);
        return ResponseEntity.ok(postsById.getBody());
    }

    @SneakyThrows
    @Override
    public ResponseEntity<List<Comments>> getCommentsFromPost(String id) {
        ResponseEntity<String> comments = client.getCommentsFromPost(id);
        List<Comments> commentsList = mapper.readValue(comments.getBody(), ArrayList.class);
        return ResponseEntity.ok(commentsList);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<List<Comments>> getCommentsByPostId(String postId) {
        HttpEntity<String> comments = client.getCommentsByPostId(postId);
        List<Comments> commentsList = mapper.readValue(comments.getBody(), ArrayList.class);
        return ResponseEntity.ok(commentsList);
    }


    @SneakyThrows
    @Override
    public ResponseEntity<Posts> insertPosts(Posts posts) {
        ResponseEntity<Posts> postsById = this.getPostsById(String.valueOf(posts.getId()));

        if (postsById.getBody().getId() == null) {
            ResponseEntity<String> entity = client.insertPosts(posts);
            Posts post = mapper.readValue(entity.getBody(), Posts.class);
            return ResponseEntity.ok(post);
        } else {
            throw new Rindus5Exception("The Post exist");
        }
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Posts> changePostsById(String id, Posts posts) {
        ResponseEntity<Posts> postsById = this.getPostsById(String.valueOf(id));

        if (postsById.getBody().getId() != null) {
            client.changePostsById(id, posts);
            return ResponseEntity.ok(postsById.getBody());
        } else {
            throw new Rindus5Exception("The Post doesn´t exist");
        }
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Posts> modifyPost(Posts posts) {
        ResponseEntity<Posts> postsById = this.getPostsById(String.valueOf(posts.getId()));

        if (postsById.getBody().getId() != null) {
            HttpEntity<String> comments = client.modifyPosts(posts);
            return ResponseEntity.ok(mapper.readValue(comments.getBody(), Posts.class));
        } else {
            throw new Rindus5Exception("The Post doesn´t exist");
        }
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Posts> deletePostsById(String id) {
        ResponseEntity<Posts> postsById = this.getPostsById(String.valueOf(id));

        if (postsById.getBody().getId() != null) {
            HttpEntity<String> comments = client.deletePostsById(id);
            return ResponseEntity.ok(mapper.readValue(comments.getBody(), Posts.class));
        } else {
            throw new Rindus5Exception("The Post doesn´t exist");
        }
    }
}
