package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.AddUserRequest;
import br.com.AllyWatch.server.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void addNewUser(@RequestBody @Valid AddUserRequest request){
        userService.add(request);
    }

}
