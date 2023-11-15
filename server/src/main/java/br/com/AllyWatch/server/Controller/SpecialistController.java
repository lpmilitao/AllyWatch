package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.LawyerRequest;
import br.com.AllyWatch.server.DTO.Request.PsychologistRequest;
import br.com.AllyWatch.server.DTO.Response.SpecialistResponse;
import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Service.LawyerService;
import br.com.AllyWatch.server.Service.PsychologistService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.USER;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/specialists")
public class SpecialistController {

    @Autowired
    private LawyerService lawyerService;

    @Autowired
    private PsychologistService psychologistService;

    @PostMapping("/lawyers")
    @ResponseStatus(CREATED)
    public void addLawyer(@RequestBody @Valid LawyerRequest request){
        lawyerService.add(request);
    }

    @GetMapping("/lawyers")
    @Secured(USER)
    public Page<SpecialistResponse> listLawyersByStatus(@PathParam("status") Status status,
                                                        Pageable pageable){
        return lawyerService.listByStatus(status, pageable);
    }

    @PostMapping("/psychologists")
    @ResponseStatus(CREATED)
    public void addPsychologist(@RequestBody @Valid PsychologistRequest request){
        psychologistService.add(request);
    }

    @GetMapping("/psychologists")
    @Secured(USER)
    public Page<SpecialistResponse> listPsychologistsByStatus(@PathParam("status") Status status,
                                                              Pageable pageable){
        return psychologistService.listByStatus(status, pageable);
    }
}
