package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Request.CreatePostRequest;
import br.com.AllyWatch.server.Domain.Post;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.AllyWatch.server.DTO.Mapper.PostMapper.toEntity;

@Service
public class PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    public void create(String auth, CreatePostRequest request) {
        User user = userService.getAuthenticatedUser(auth);

        Post post = toEntity(request);

        user.addPost(post);

        /*
        verificar se o nome do agressor bate com o de algum post já cadastrado,
        e aí criar solicitação de chat
         */

        postRepository.save(post);
    }
}
