package com.rindus.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rindus.configuration.properties.PropertiesEnvConf;
import com.rindus.entities.Posts;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RindusClientImpl implements RindusClient{
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PropertiesEnvConf properties;

    @Autowired
    ObjectMapper objectMapper;

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

    @SneakyThrows
    public HttpEntity<String> modifyPosts(Posts posts) {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);
        restTemplate.setRequestFactory(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(properties.getBase()+"posts/"+posts.getId());

        HttpEntity<String> entity = new HttpEntity(objectMapper.writeValueAsString(posts), headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.PATCH,
                entity,
                String.class);

        return response;
    }

    public HttpEntity<String> deletePostsById(String id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(properties.getBase()+"posts/"+id);

        HttpEntity<String> entity = new HttpEntity(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.DELETE,
                entity,
                String.class);

        return response;
    }
}
