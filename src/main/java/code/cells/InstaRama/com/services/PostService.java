package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.dtos.PostRequest;
import code.cells.InstaRama.com.exceptions.ResourceNotFoundException;
import code.cells.InstaRama.com.models.Post;
import code.cells.InstaRama.com.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import code.cells.InstaRama.com.models.Kingdom;
import code.cells.InstaRama.com.repository.KingdomRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;
//    private UserRepository userRepository;
private KingdomRepository kingdomRepository;


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

    // Falta integración con userId
    public Post createPostByUser(Long userId, PostRequest postRequest){
        // "Verificar que el usuario exista"
        //integración con kingdomId
        Kingdom kingdom = kingdomRepository
                .findById(postRequest.kingdomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Kingdom not found with id: "
                                        + postRequest.kingdomId()
                        )
                );

        Post post = new Post();
        post.setTitle(postRequest.title());
        post.setPhoto(postRequest.photo());
        post.setDescription(postRequest.description());
        post.setDatetime(postRequest.datetime());
        post.setKingdom(kingdom);
        postRepository.save(post);
        // Actualizar la lista de publicaciones del usuario y la lista de publiaciones al reino
        return post;
    }



    // Falta integración con kingdomId y userId
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
