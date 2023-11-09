package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Request.PostRequest;
import br.com.AllyWatch.server.DTO.Response.PostResponse;
import br.com.AllyWatch.server.Domain.Chat;
import br.com.AllyWatch.server.Domain.Post;
import br.com.AllyWatch.server.Domain.Solicitation;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.ChatRepository;
import br.com.AllyWatch.server.Repository.PostRepository;
import br.com.AllyWatch.server.Repository.SolicitationRepository;
import br.com.AllyWatch.server.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.AllyWatch.server.DTO.Mapper.PostMapper.*;
import static br.com.AllyWatch.server.Domain.Enum.Status.UNDER_REVIEW;
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

    @Autowired
    private SolicitationRepository solicitationRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    public void create(String auth, PostRequest request) {
        User user = userService.getAuthenticatedUser(auth);

        Post post = toEntity(request);

        user.addPost(post);

        if (!request.getAggressor().isEmpty()) {
            post.setAggressor(request.getAggressor());
            verifyAgressor(post);
        }

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
                .map(post -> toMyResponse(post, post.getLikes().contains(user)));
    }

    public Page<PostResponse> listAllPosts(String authorization, Pageable pageable) {
        User user = userService.getAuthenticatedUser(authorization);

        return postRepository.findAll(pageable)
                .map(post -> {
                    if (post.getAuthor().getId() == user.getId()) {
                        return toMyResponse(post,
                                post.getLikes().contains(user)
                        );
                    } else if (post.isAnonymous()) {
                        return toAnonymousResponse(post,
                                post.getLikes().contains(user)

                        );
                    }
                    return toPublicResponse(post,
                            post.getLikes().contains(user)

                    );
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

    private void verifyAgressor(Post post) {

        List<Post> posts = postRepository.findAll();

        List<Post> matching = posts.stream().filter(p ->
                Objects.equals(p.getAggressor(), post.getAggressor())
                        && p.getAuthor().getId() != post.getAuthor().getId()
        ).toList();

        if (matching.isEmpty()) {
            return;
        }

        matching.forEach(p -> {
            if (verifyIfUsersAreAlreadyInChat(post.getAuthor(), p.getAuthor())) {
                return;
            }
            Chat chat = Chat.builder()
                    .open(false)
                    .solicitations(new ArrayList<>())
                    .messages(new ArrayList<>())
                    .users(new ArrayList<>())
                    .build();

            Solicitation solicitationUser1 = Solicitation.builder()
                    .status(UNDER_REVIEW)
                    .user(post.getAuthor())
                    .build();

            Solicitation solicitationUser2 = Solicitation.builder()
                    .status(UNDER_REVIEW)
                    .user(p.getAuthor())
                    .build();

            chat.addSolicitation(solicitationUser1);
            chat.addSolicitation(solicitationUser2);
            chat.addUser(p.getAuthor());
            chat.addUser(post.getAuthor());
            p.getAuthor().addChat(chat);
            post.getAuthor().addChat(chat);

            chatRepository.save(chat);
            userRepository.save(p.getAuthor());
            userRepository.save(post.getAuthor());
            solicitationRepository.save(solicitationUser1);
            solicitationRepository.save(solicitationUser2);
        });
    }

    private boolean verifyIfUsersAreAlreadyInChat(User user1, User user2) {
        List<Chat> calica = chatRepository.findAll().stream().filter(chat ->
                chat.getUsers().contains(user1) &&
                        chat.getUsers().contains(user2)
        ).toList();
        return !calica.isEmpty();
    }
}
