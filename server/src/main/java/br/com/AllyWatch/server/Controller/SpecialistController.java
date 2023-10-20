package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.LawyerRequest;
import br.com.AllyWatch.server.DTO.Response.SpecialistResponse;
import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Domain.Psychologist;
import br.com.AllyWatch.server.Service.LawyerService;
import br.com.AllyWatch.server.Service.PsychologistService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.USER;
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

    @GetMapping("/lawyer")
    @Secured(USER)
    public List<SpecialistResponse> listLawyersByStatus(@PathParam("status") Status status){
        return lawyerService.listByStatus(status);
    }

    @PostMapping("/psychologist")
    @ResponseStatus(CREATED)
    public void addPsychologist(@RequestBody @Valid Psychologist request){
        psychologistService.add(request);
    }

    @GetMapping("/psychologist")
    @Secured(USER)
    public List<SpecialistResponse> listPsychologistsByStatus(@PathParam("status") Status status){
        return psychologistService.listByStatus(status);
    }
}
