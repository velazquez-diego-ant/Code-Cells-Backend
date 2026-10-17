package code.cells.InstaRama.com.controllers;

import code.cells.InstaRama.com.dtos.CommentRequest;
import code.cells.InstaRama.com.models.Comment;
import code.cells.InstaRama.com.services.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //Consultar los comentarios de una publicación
    @GetMapping(path = "posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getAllByPostId(@PathVariable("postId")Long postId){
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    //Consultar un comentario por ID
    @GetMapping(path = "comments/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId")Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    //Crear un comentario
    @PostMapping(path = "posts/{postId}/comments")//http:localhost:8080/api/posts/id_post/comments?userId=id_user
    public ResponseEntity<Comment> createComment(@PathVariable("postId")Long postId,
                                                 @RequestParam Long userId,
                                                 @Valid @RequestBody CommentRequest commentRequest){
        Comment comment = commentService.createComment(postId, userId, commentRequest);
        return ResponseEntity.ok(comment);
    }

    //Actualizar un comentario
    @PutMapping(path = "comments/{commentId}")
    public ResponseEntity<Comment> updateCommentById(@PathVariable("commentId")Long commentId,
                                                     @RequestParam Long userId,
                                                     @Valid @RequestBody CommentRequest commentRequest){
        Comment comment = commentService.updateCommentById(commentId, userId, commentRequest);
        return ResponseEntity.ok(comment);
    }

    //Eliminar un comentario
    @DeleteMapping(path = "comments/{commentId}")
    public ResponseEntity<Comment> deleteCommentById(@PathVariable("commentId")Long commentId,
                                                     @RequestParam Long userId){
        Comment comment = commentService.deleteCommentById(commentId, userId);
        return ResponseEntity.ok(comment);
    }

}
