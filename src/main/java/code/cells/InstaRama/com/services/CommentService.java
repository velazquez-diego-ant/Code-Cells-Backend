package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.dtos.CommentRequest;
import code.cells.InstaRama.com.exceptions.ForbiddenActionException;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.models.Comment;
import code.cells.InstaRama.com.models.Post;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.repository.CommentRepository;
import code.cells.InstaRama.com.repository.PostRepository;
import code.cells.InstaRama.com.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    //inyección de dependencias
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private void verifyCommentOwner(Comment comment, Long userId){
        if(!comment.getUser().getId().equals(userId)){
            throw new ForbiddenActionException(
                    "No tienes permiso para realizar esta acción."
            );
        }
    }

    //metodo para obtener todos los comentarios por el id de post
    public List<Comment> getCommentsByPostId(Long postId){
        if (!postRepository.existsById(postId)) throw new ResourceNotFoundException("Post con el id " + postId + " no encontrado.");
        return commentRepository.findByPostId(postId);
    }

    //Obtener el comentario por su ib
    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comentario con el id " + commentId + " no encontrado")
        );
    }

    //metodo para crear un comentario
    public Comment createComment (Long postId, Long userId, CommentRequest commentRequest) {
        //comprobar si el usuario existe
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + userId + " no encontrado.")
        );

        //comprobar si existe el post
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post con el id " + postId + " no encontrado.")
        );

        //creamos el objeto
        Comment comment = new Comment();
        //establecemos el texto enviado
        comment.setMessage(commentRequest.message());
        //se asigna el usuario encontrado
        comment.setUser(user);
        //se asigna el post
        comment.setPost(post);
        //guardamos el comentario
        return commentRepository.save(comment);
    }

    //metodo para actualizar un comentario
    public Comment updateCommentById(Long commentId, Long userId,CommentRequest commentRequest){
        Comment comment = getCommentById(commentId);

        verifyCommentOwner(comment, userId);

        comment.setMessage(commentRequest.message());
        return commentRepository.save(comment);
    }

    //metodo para eliminar un comentario
    public Comment deleteCommentById(Long commentId, Long userId){
        Comment comment = getCommentById(commentId);

        verifyCommentOwner(comment, userId);

        commentRepository.delete(comment);
        return comment;
    }
}
