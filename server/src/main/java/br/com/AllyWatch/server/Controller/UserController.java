package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.IconRequest;
import br.com.AllyWatch.server.DTO.Request.UserRequest;
import br.com.AllyWatch.server.DTO.Response.SolicitationResponse;
import br.com.AllyWatch.server.Service.ChatService;
import br.com.AllyWatch.server.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.USER;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void addNewUser(@RequestBody @Valid UserRequest request){
        userService.add(request);
    }

    @PutMapping
    @Secured(USER)
    public void editIcon(@RequestHeader String authorization,
                           @RequestBody @Valid IconRequest request){
        userService.editIcon(authorization, request);
    }

    @DeleteMapping
    @Secured(USER)
    public void deleteAccount(@RequestHeader String authorization){
        userService.delete(authorization);
    }

    @PostMapping("/logout")
    @Secured(USER)
    public void logout(@RequestHeader String authorization){
        userService.logout(authorization);
    }

    @GetMapping("/chat/solicitation")
    @Secured(USER)
    public List<SolicitationResponse> listChatSolicitations(@RequestHeader String authorization){
        return chatService.listSolicitations(authorization);
    }
}
