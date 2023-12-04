package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Mapper.CommentMapper;
import br.com.AllyWatch.server.DTO.Request.CommentRequest;
import br.com.AllyWatch.server.DTO.Response.CommentResponse;
import br.com.AllyWatch.server.Domain.Comment;
import br.com.AllyWatch.server.Domain.Post;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Transactional
    public void create(String authorization, long postId, CommentRequest request) {
        User user = userService.getAuthenticatedUser(authorization);
        Post post = postService.findById(postId);

        Comment comment = Comment.builder()
                .comment(request.getComment())
                .publicationTime(LocalDateTime.now())
                .build();

        user.addCommentMade(comment);
        post.addComment(comment);

        commentRepository.save(comment);
    }

    public void delete(String authorization, long commentId) {
        User user = userService.getAuthenticatedUser(authorization);

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Comment not found."));

        if (user.getId() != comment.getAuthor().getId()){
            throw new ResponseStatusException(FORBIDDEN, "You can not delete a comment that is not yours.");
        }

        commentRepository.delete(comment);
    }

    public List<CommentResponse> list(String authorization, long postId) {
        User user = userService.getAuthenticatedUser(authorization);
        Post post = postService.findById(postId);

        return commentRepository.findAllByPost_IdOrderByPublicationTimeDesc(postId)
                .stream().map(comment -> CommentMapper.toResponse(
                        comment, user.getId(), post.getAuthor().getId(), post.isAnonymous())
                )
                .toList();
    }
}
