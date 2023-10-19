package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.LawyerRequest;
import br.com.AllyWatch.server.Domain.Psychologist;
import br.com.AllyWatch.server.Service.LawyerService;
import br.com.AllyWatch.server.Service.PsychologistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/specialist")
public class SpecialistController {

    @Autowired
    private LawyerService lawyerService;

    @Autowired
    private PsychologistService psychologistService;

    @PostMapping("/lawyer")
    @ResponseStatus(CREATED)
    public void addLawyer(@RequestBody @Valid LawyerRequest request){
        lawyerService.add(request);
    }

    @PostMapping("/psychologist")
    @ResponseStatus(CREATED)
    public void addPsychologist(@RequestBody @Valid Psychologist request){
        psychologistService.add(request);
    }
}
