package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.CommentRequest;
import br.com.AllyWatch.server.DTO.Request.PostEditResponse;
import br.com.AllyWatch.server.DTO.Request.PostRequest;
import br.com.AllyWatch.server.DTO.Request.ReportRequest;
import br.com.AllyWatch.server.DTO.Response.CommentResponse;
import br.com.AllyWatch.server.DTO.Response.PostResponse;
import br.com.AllyWatch.server.Service.CommentService;
import br.com.AllyWatch.server.Service.PostService;
import br.com.AllyWatch.server.Service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.USER;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReportService reportService;

    @PostMapping
    @Secured(USER)
    @ResponseStatus(CREATED)
    public void createPost(@RequestHeader String authorization,
                           @RequestBody @Valid PostRequest request){
        postService.create(authorization, request);
    }

    @PutMapping("/{postId}")
    @Secured(USER)
    public void editPost(@RequestHeader String authorization,
                           @RequestBody @Valid PostRequest request,
                         @PathVariable long postId){
        postService.edit(authorization, request, postId);
    }

    @DeleteMapping("/{postId}")
    @Secured(USER)
    public void deletePost(@RequestHeader String authorization,
                         @PathVariable long postId){
        postService.delete(authorization, postId);
    }

    @GetMapping("/{postId}")
    @Secured(USER)
    public PostEditResponse getPost(@PathVariable long postId){
        return postService.getById(postId);
    }

    @GetMapping("/mine")
    @Secured(USER)
    public Page<PostResponse> listMyPosts(@RequestHeader String authorization,
                                          Pageable pageable){
        return postService.listMyPosts(authorization, pageable);
    }

    @GetMapping
    @Secured(USER)
    public Page<PostResponse> listPosts(@RequestHeader String authorization,
                                          Pageable pageable){
        return postService.listAllPosts(authorization, pageable);
    }

    @PostMapping("{postId}/comments")
    @Secured(USER)
    @ResponseStatus(CREATED)
    public void addComment(@RequestHeader String authorization,
                           @PathVariable long postId,
                           @RequestBody @Valid CommentRequest request){
        commentService.create(authorization, postId, request);
    }

    @GetMapping("{postId}/comments")
    @Secured(USER)
    @ResponseStatus(CREATED)
    public List<CommentResponse> listPostComments(@RequestHeader String authorization,
                                                  @PathVariable long postId){
        return commentService.list(authorization, postId);
    }

    @DeleteMapping("/comments/{commentId}")
    @Secured(USER)
    public void deleteComment(@RequestHeader String authorization,
                           @PathVariable long commentId){
        commentService.delete(authorization, commentId);
    }

    @PutMapping("/{postId}/likes")
    @Secured(USER)
    public void likePost(@RequestHeader String authorization,
                           @PathVariable long postId){
        postService.like(authorization, postId);
    }

    @PostMapping("/{postId}/reports")
    @Secured(USER)
    public void reportPost(@RequestHeader String authorization,
                           @PathVariable long postId,
                           @RequestBody @Valid ReportRequest request){
        reportService.add(authorization, postId, request);
    }
}
