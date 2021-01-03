package com.rindus.client;

import com.rindus.configuration.properties.PropertiesEnvConf;
import com.rindus.entities.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RindusClientImpl implements RindusClient{
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PropertiesEnvConf properties;

    public ResponseEntity<String> getAllPosts() {
        return restTemplate.getForEntity(properties.getBase()+"posts", String.class);
    }

    public ResponseEntity<Posts> getPostsById(String id) {
        return restTemplate.getForEntity(properties.getBase() + "posts/" + id, Posts.class);
    }

    public ResponseEntity<String> getCommentsFromPost(String id) {
        return restTemplate.getForEntity(properties.getBase()+"posts/"+id+"/comments", String.class);
    }

    public HttpEntity<String> getCommentsByPostId(String postId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(properties.getBase()+"comments/")
                .queryParam("postId", postId);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        return response;
    }

    public ResponseEntity<String> insertPosts(Posts posts) {
        return restTemplate.postForEntity(properties.getBase()+"posts", posts, String.class);
    }

    public void changePostsById(String id, Posts posts) {
        restTemplate.put(properties.getBase()+"posts/"+id, posts);
    }

    public String modifyPosts(Posts posts) {
        return restTemplate.patchForObject(properties.getBase()+"posts", posts, String.class);
    }

    public void deletePostsById(String id) {
        restTemplate.delete(properties.getBase()+"posts/"+id, Posts.class);
    }
}
