package br.com.AllyWatch.server.Controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.USER;

@RestController
@RequestMapping("/hello")
public class TestController {

    @GetMapping
    @Secured(USER)
    public String tryAuth() {
        return "hello";
    }
}
