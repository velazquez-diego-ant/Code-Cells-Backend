package code.cells.InstaRama.com.controllers;

import code.cells.InstaRama.com.dtos.PostRequest;
import code.cells.InstaRama.com.models.Post;
import code.cells.InstaRama.com.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping(path = "post") // http://localhost:8080/api/post
    public ResponseEntity<List<Post>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping(path = "post/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId){
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    // Falta obtener publicaciones por medio de usuarios y obtener publicaciones por medio de kingdoms

    @GetMapping(path = "post/user/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable("userId") Long userId, @RequestBody PostRequest postRequest){
        Post post = postService.createPostByUser(userId, postRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).body(post);
    }

    @GetMapping(path = "post/{postId}")
    public ResponseEntity<Post> updatePostById(@PathVariable("postId") Long userId, @RequestBody PostRequest postRequest){
        Post updatedPost = postService.updatePostById(userId, postRequest);
        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping(path = "post/{postId}")
    public ResponseEntity<Post> deletePostById(@PathVariable("postId")Long postId){
        Post deletedPost = postService.deletePostById(postId);
        return ResponseEntity.ok(deletedPost);
    }

}
