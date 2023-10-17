package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Mapper.PostMapper;
import br.com.AllyWatch.server.DTO.Request.PostRequest;
import br.com.AllyWatch.server.DTO.Response.PostResponse;
import br.com.AllyWatch.server.Domain.Post;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.PostRepository;
import br.com.AllyWatch.server.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static br.com.AllyWatch.server.DTO.Mapper.PostMapper.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void create(String auth, PostRequest request) {
        User user = userService.getAuthenticatedUser(auth);

        Post post = toEntity(request);

        user.addPost(post);

        /*
        verificar se o nome do agressor bate com o de algum post já cadastrado,
        e aí criar solicitação de chat
         */

        postRepository.save(post);
    }

    public Post findById(long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Post not found."));
    }

    public void edit(String authorization, PostRequest request, long postId) {
        User user = userService.getAuthenticatedUser(authorization);
        Post post = findById(postId);

        if (post.getAuthor().getId() != user.getId()) {
            throw new ResponseStatusException(FORBIDDEN, "You can not edit a post that is not yours.");
        }

        post.setTitle(request.getTitle());
        post.setBody(request.getBody());

        if (!Objects.equals(post.getAggressor(), request.getAggressor())) {
            //verificar se o agressor mudou, se mudou, fazer a verificação de outros posts com o mesmo agressor
            post.setAggressor(request.getAggressor());
        }

        post.setAnonymous(request.isAnonymous());

        postRepository.save(post);
    }

    public void delete(String authorization, long postId) {
        User user = userService.getAuthenticatedUser(authorization);

        Post post = findById(postId);

        if (user.getId() != post.getAuthor().getId()) {
            throw new ResponseStatusException(FORBIDDEN, "You can not delete a post that is not yours.");
        }

        postRepository.delete(post);
    }

    public Page<PostResponse> listMyPosts(String authorization, Pageable pageable) {
        User user = userService.getAuthenticatedUser(authorization);

        return postRepository.findAllByAuthor_IdOrderByPublicationTimeDesc(user.getId(), pageable)
                .map(PostMapper::toMyResponse);
    }

    public Page<PostResponse> listAllPosts(String authorization, Pageable pageable) {
        User user = userService.getAuthenticatedUser(authorization);

        return postRepository.findAll(pageable)
                .map(post -> {
                    if (post.getAuthor().getId() == user.getId()) {
                        return toMyResponse(post);
                    } else if (post.isAnonymous()) {
                        return toAnonymousResponse(post);
                    }
                    return toPublicResponse(post);
                });
    }

    public void like(String authorization, long postId) {
        User user = userService.getAuthenticatedUser(authorization);
        Post post = findById(postId);

        if (post.getLikes().contains(user)) {
            post.removeLike(user);
            user.removePostsLiked(post);
        } else {
            user.addPostsLiked(post);
            post.addLike(user);
        }

        postRepository.save(post);
        userRepository.save(user);
    }
}
