package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.LoginRequest;
import br.com.AllyWatch.server.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest request){
        return authService.login(request);
    }
}
