package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.LawyerRequest;
import br.com.AllyWatch.server.Service.LawyerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/specialist")
public class SpecialistController {

    @Autowired
    private LawyerService lawyerService;

    @PostMapping("/lawyer")
    @ResponseStatus(CREATED)
    public void addLawyer(@RequestBody @Valid LawyerRequest request){
        lawyerService.add(request);
    }
}
