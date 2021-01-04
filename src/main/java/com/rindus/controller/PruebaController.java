package com.rindus.controller;

import com.rindus.entities.Comments;
import com.rindus.entities.Posts;
import com.rindus.service.RindusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/prueba")
@RestController
@Api(value = "Controller to consume rest API", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PruebaController {

    Logger logger = LoggerFactory.getLogger(PruebaController.class);

    @Autowired
    RindusService service;

    @ApiOperation(value="Get all resources", notes="Get all resources")
    @GetMapping
    public ResponseEntity getAll() {
        logger.info("Init get all");
        ResponseEntity<List<Posts>> allPosts = service.getAllPosts();
        return allPosts;
    }

    @ApiOperation(value="Get one resource by id", notes="Get one resource by id")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        logger.info("Init get by Id");
        ResponseEntity<Posts> postsById = service.getPostsById(id);
        return postsById;
    }

    @ApiOperation(value="Get all comments for one PostId", notes="Get all comments for one PostId")
    @GetMapping("/{id}/comments")
    public ResponseEntity getCommentsFromPost(@PathVariable("id") String id) {
        logger.info("Init get comments");
        ResponseEntity<List<Comments>> commentsFromPost = service.getCommentsFromPost(id);
        return commentsFromPost;
    }

    @ApiOperation(value="Get comments filtered", notes="Get comments filtered")
    @GetMapping("/comments/{postId}")
    public ResponseEntity getCommentsFilteredBPostId(@PathVariable("postId") String postId) {
        logger.info("Init get filtered comments");
        ResponseEntity<List<Comments>> commentsFromPost = service.getCommentsByPostId(postId);
        return commentsFromPost;
    }


    @ApiOperation(value="Create new resource", notes="Create new resource")
    @PostMapping
    public ResponseEntity insertNew(Posts post){
        logger.info("Init Insert");
        ResponseEntity<Posts> postsResponseEntity = service.insertPosts(post);
        return postsResponseEntity;
    }

    @ApiOperation(value="Modify resource", notes="Modify resource")
    @PostMapping("/{id}")
    public ResponseEntity changePost(@PathVariable("id") String id, @RequestBody Posts posts){
        logger.info("Init change");
        ResponseEntity<Posts> postsResponseEntity = service.changePostsById(id, posts);
        return postsResponseEntity;
    }

    @ApiOperation(value="Partial modify resource", notes="Partial modify resource")
    @PostMapping("/modify/{id}")
    public ResponseEntity modifyPost(@PathVariable("id") String id, @RequestBody Posts posts){
        logger.info("Init modify");
        ResponseEntity<Posts> responseEntity = service.modifyPost(posts);
        return responseEntity;
    }

    @ApiOperation(value="Delete resource", notes="Delete resource")
    @PostMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable("id") String id){
        logger.info("Init delete");
        ResponseEntity<Posts> postsResponseEntity = service.deletePostsById(id);
        return postsResponseEntity;
    }

}
