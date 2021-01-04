package com.rindus.cucumber.steps;

import com.rindus.configuration.TestConfiguration;
import com.rindus.entities.Posts;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@CucumberContextConfiguration
public class StepsDef extends TestConfiguration {
    private String status;
    private String url;
    private Boolean isEmpty;
    private String id;
    private String postId;
    private String title;
    private String body;

    private ResponseEntity<List> allResulst;
    private ResponseEntity<Posts> resultPosts;

    @Before
    public void setUp() {
        status = "";
        url = "";
    }

    @Given("nothing")
    public void giveNothing(){
    }

    @Given("post id {}")
    public void giveId(String id){
        this.id = id;
    }

    @Given("postId {}")
    public void givePostId(String postId){
        this.postId = postId;
    }

    @Given("title {}")
    public void givetitle(String title){
        this.title = title;
    }

    @Given("post body {}")
    public void giveBody(String body){
        this.body = body;
    }

    @When("i look for the posts")
    public void lookForAllPosts() {
        url = super.DEFAULT_URL+"prueba";
        allResulst = restTemplate.getForEntity(url, List.class);
        status = String.valueOf(allResulst.getStatusCode().value());
        isEmpty = allResulst.getBody().isEmpty();
    }

    @When("i look for the post")
    public void lookForPostsById() {
        url = super.DEFAULT_URL+"prueba/"+this.id;
        resultPosts = restTemplate.getForEntity(url, Posts.class);
        status = String.valueOf(resultPosts.getStatusCode().value());
    }

    @When("i look for the comments")
    public void lookForCommentsByPost() {
        url = super.DEFAULT_URL+"prueba/"+this.id+"/comments";
        allResulst = restTemplate.getForEntity(url, List.class);
        status = String.valueOf(allResulst.getStatusCode().value());
        isEmpty = allResulst.getBody().isEmpty();
    }

    @When("i look for the comments by PostId")
    public void lookForPostByPostId() {
        url = super.DEFAULT_URL+"prueba/comments/"+this.postId;
        allResulst = restTemplate.getForEntity(url, List.class);
        status = String.valueOf(allResulst.getStatusCode().value());
        isEmpty = allResulst.getBody().isEmpty();
    }

    @When("i insert the post")
    public void insertPost() {
        url = super.DEFAULT_URL+"prueba";
        Posts posts = new Posts();
        posts.setId(Integer.parseInt(id));
        posts.setUserId(Integer.parseInt(postId));
        posts.setTitle(title);
        posts.setBody(body);
        resultPosts = restTemplate.postForEntity(url, posts, Posts.class);
        status = String.valueOf(resultPosts.getStatusCode().value());
    }

    @When("i put the post")
    public void putPost() {
        url = super.DEFAULT_URL+"prueba/"+this.id;
        Posts posts = new Posts();
        posts.setId(Integer.parseInt(id));
        posts.setUserId(Integer.parseInt(postId));
        posts.setTitle(title);
        posts.setBody(body);
        resultPosts = restTemplate.postForEntity(url, posts, Posts.class);
        status = String.valueOf(resultPosts.getStatusCode().value());
    }

    @When("i patch the post")
    public void patcPost() {
        url = super.DEFAULT_URL+"prueba/modify/"+this.id;
        Posts posts = new Posts();
        posts.setId(Integer.parseInt(id));
        posts.setUserId(Integer.parseInt(postId));
        posts.setTitle(title);
        resultPosts = restTemplate.postForEntity(url, posts, Posts.class);
        status = String.valueOf(resultPosts.getStatusCode().value());
    }

    @When("i delete the post")
    public void deletePost() {
        url = super.DEFAULT_URL+"prueba/delete/"+this.id;
        resultPosts = restTemplate.postForEntity(url, null, Posts.class);
        status = String.valueOf(resultPosts.getStatusCode().value());
    }

    @Then("i should get status {}")
    public void getOkResult(String status) {
        assertThat(status, is(this.status));
    }

    @Then("body shouldn´t be empty")
    public void bodyIsNotEmpty() {
        assertThat(isEmpty, is(Boolean.FALSE));
    }
}
