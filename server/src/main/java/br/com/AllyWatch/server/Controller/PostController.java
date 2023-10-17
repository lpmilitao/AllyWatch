package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.CommentRequest;
import br.com.AllyWatch.server.DTO.Request.PostRequest;
import br.com.AllyWatch.server.DTO.Response.PostResponse;
import br.com.AllyWatch.server.Service.CommentService;
import br.com.AllyWatch.server.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.USER;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

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

    @PostMapping("{postId}/comment")
    @Secured(USER)
    @ResponseStatus(CREATED)
    public void addComment(@RequestHeader String authorization,
                           @PathVariable long postId,
                           @RequestBody @Valid CommentRequest request){
        commentService.create(authorization, postId, request);
    }

    @DeleteMapping("/comment/{commentId}")
    @Secured(USER)
    public void deleteComment(@RequestHeader String authorization,
                           @PathVariable long commentId){
        commentService.delete(authorization, commentId);
    }
}
