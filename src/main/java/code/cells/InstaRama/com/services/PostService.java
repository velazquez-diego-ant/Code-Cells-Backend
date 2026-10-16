package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.dtos.PostRequest;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.models.Post;
import code.cells.InstaRama.com.models.User;
import code.cells.InstaRama.com.repository.PostRepository;
import code.cells.InstaRama.com.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
//    private KingdomRepository kingdomRepository;

    public List<Post> getAllPost(){
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) throw new ResourceNotFoundException("No hay publicaciones.");
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

    // Falta integración con kingdom
    public Post createPostByUser(Long userId, PostRequest postRequest){
        // "Verificar que el usuario exista"
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("El User con Id:"+userId+" NO existe")
        );
        //Creamos la publicacion
        Post post = new Post();
        post.setTitle(postRequest.title());
        post.setPhoto(postRequest.photo());
        post.setDescription(postRequest.description());
        post.setDatetime(postRequest.datetime());
        //Guardamos la publicacion
        postRepository.save(post);
        // Actualizar la lista de publicaciones del usuario
        user.getPost().add(post);
        userRepository.save(user);
        //Actualizar lista publicaciones en los reinos
        //FALTA
        return post;
    }

    public Post updatePostById(Long postId, PostRequest postRequest){
        Post previousPost = getPostById(postId);
        if(postRequest.title() != null) previousPost.setTitle(postRequest.title());
        if (postRequest.photo() != null) previousPost.setPhoto(postRequest.photo());
        if (postRequest.description() != null) previousPost.setDescription(postRequest.description());
        return previousPost;
    }

    public Post deletePostById(Long postId){
        Post post = getPostById(postId);
        postRepository.delete(post);
        return post;
    }
}
