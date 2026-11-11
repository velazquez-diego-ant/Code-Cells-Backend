package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.dtos.PostRequest;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.models.Kingdom;
import code.cells.InstaRama.com.models.Post;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.repository.KingdomRepository;
import code.cells.InstaRama.com.repository.PostRepository;
import code.cells.InstaRama.com.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private KingdomRepository kingdomRepository;

    //Si no hay publicaciones se regresa una lista vacía (no es un error)
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post getPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la publicación con el id: " + postId)
        );
    }

    public List<Post> getAllByUserId(Long userId){
        //ver si existe el usuario
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("Usuario con el id: " + userId + " no encontrado");
        return postRepository.findAllByUser_Id(userId);
    }

    public Post createPostByUser(Long userId, PostRequest postRequest){
        // "Verificar que el usuario exista"
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("El User con Id:"+userId+" NO existe")
        );
        //Creamos la publicación
        Post post = new Post();
        post.setTitle(postRequest.title());
        post.setPhoto(postRequest.photo());
        post.setDescription(postRequest.description());
        //Si no mandan la fecha se usa la actual (la columna date no acepta nulos)
        post.setDatetime(postRequest.datetime() != null ? postRequest.datetime() : new Date());
        // El lado @ManyToOne (Post.user) es el que guarda la FK user_iduser
        post.setUser(user);
        post.setKingdom(resolveKingdom(postRequest));
        //Guardamos la publicación
        postRepository.save(post);
        return post;
    }

    // Si no mandan reino, se usa "Sin Clasificar / General"
    private Kingdom resolveKingdom(PostRequest request) {
        String kingdom = (request.kingdom() == null || request.kingdom().isBlank())
                ? "Sin Clasificar" : request.kingdom();
        String subKingdom = (request.subKingdom() == null || request.subKingdom().isBlank())
                ? "General" : request.subKingdom();
        return kingdomRepository.findByKingdomAndSubKingdom(kingdom, subKingdom)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el reino: " + kingdom + " / " + subKingdom));
    }

    public Post updatePostById(Long postId, PostRequest postRequest){
        Post previousPost = getPostById(postId);
        //Solo se cambian los campos que llegan con valor (no se permite dejar título o descripción en blanco)
        if (postRequest.title() != null && !postRequest.title().isBlank()) previousPost.setTitle(postRequest.title());
        if (postRequest.photo() != null && postRequest.photo().length > 0) previousPost.setPhoto(postRequest.photo());
        if (postRequest.description() != null && !postRequest.description().isBlank()) previousPost.setDescription(postRequest.description());
        if (postRequest.datetime() != null) previousPost.setDatetime(postRequest.datetime());
        if (postRequest.kingdom() != null && !postRequest.kingdom().isBlank()) {
            previousPost.setKingdom(resolveKingdom(postRequest));
        }
        return postRepository.save(previousPost);
    }

    public Post deletePostById(Long postId){
        Post post = getPostById(postId);
        postRepository.delete(post);
        return post;
    }
}