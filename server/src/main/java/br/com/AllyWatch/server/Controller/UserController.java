package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.IconRequest;
import br.com.AllyWatch.server.DTO.Request.MessageRequest;
import br.com.AllyWatch.server.DTO.Request.UserRequest;
import br.com.AllyWatch.server.DTO.Response.ChatDetailedResponse;
import br.com.AllyWatch.server.DTO.Response.ChatResponse;
import br.com.AllyWatch.server.DTO.Response.SolicitationResponse;
import br.com.AllyWatch.server.DTO.Response.UserResponse;
import br.com.AllyWatch.server.Service.ChatService;
import br.com.AllyWatch.server.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.USER;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
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

    @GetMapping
    @Secured(USER)
    public UserResponse getUserInfo(@RequestHeader String authorization){
        return userService.getUserInfo(authorization);
    }



    @GetMapping("/chats/solicitations")
    @Secured(USER)
    public List<SolicitationResponse> listChatSolicitations(@RequestHeader String authorization){
        return chatService.listSolicitations(authorization);
    }

    @PutMapping("/chats/solicitations/{solicitationId}")
    @Secured(USER)
    public void verifyChatSolicitation(@RequestHeader String authorization,
                                       @PathVariable long solicitationId,
                                       @PathParam("accepted") boolean accepted
    ){
        chatService.verifySolicitation(authorization, solicitationId, accepted);
    }

    @GetMapping("/chats")
    @Secured(USER)
    public List<ChatResponse> listChats(@RequestHeader String authorization){
        return chatService.listChats(authorization);
    }

    @GetMapping("/chats/{chatId}")
    @Secured(USER)
    public ChatDetailedResponse detailChat(@RequestHeader String authorization,
                                           @PathVariable long chatId){
        return chatService.detailChat(authorization, chatId);
    }

    @PostMapping("/chats/{chatId}")
    @Secured(USER)
    public void sendMessage(@RequestHeader String authorization,
                            @PathVariable long chatId,
                            @RequestBody @Valid MessageRequest request){
        chatService.sendMessage(authorization, chatId, request);
    }
}
