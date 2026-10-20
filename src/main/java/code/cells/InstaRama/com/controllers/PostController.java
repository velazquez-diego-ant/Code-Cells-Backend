package code.cells.InstaRama.com.controllers;

import code.cells.InstaRama.com.dtos.PostRequest;
import code.cells.InstaRama.com.models.Post;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.services.PostService;
import code.cells.InstaRama.com.services.UserService;
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
@CrossOrigin (origins = "*")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping(path = "posts") // http://localhost:8080/api/posts
    public ResponseEntity<List<Post>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping(path = "posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId){
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    // Falta obtener publicaciones por medio de usuarios y obtener publicaciones por medio de kingdoms
    @GetMapping (path = "user/{userId}/posts") // http://localhost:8080/api/user/userid/posts
    public ResponseEntity<List<Post>> getAllPostByUserId(@PathVariable ("userId") Long userId){
        List<Post> posts = postService.getAllByUserId(userId);
        return  ResponseEntity.ok(posts);
    }

    @PostMapping(path = "user/{userId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable("userId") Long userId, @RequestBody PostRequest postRequest){
        Post post = postService.createPostByUser(userId, postRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).body(post);
    }

    @PutMapping(path = "posts/{postId}")
    public ResponseEntity<Post> updatePostById(@PathVariable("postId") Long postId, @RequestBody PostRequest postRequest){
        Post updatedPost = postService.updatePostById(postId, postRequest);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping(path = "posts/{postId}")
    public ResponseEntity<Post> deletePostById(@PathVariable("postId")Long postId){
        Post deletedPost = postService.deletePostById(postId);
        return ResponseEntity.ok(deletedPost);
    }

}
