package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.CreatePostRequest;
import br.com.AllyWatch.server.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.USER;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    @Secured(USER)
    @ResponseStatus(CREATED)
    public void createPost(@RequestHeader String authorization,
                           @RequestBody @Valid CreatePostRequest request){
        postService.create(authorization, request);
    }
}
