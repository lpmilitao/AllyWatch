package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Request.VerifySpecialistRequest;
import br.com.AllyWatch.server.DTO.Response.ReportResponse;
import br.com.AllyWatch.server.Service.LawyerService;
import br.com.AllyWatch.server.Service.PsychologistService;
import br.com.AllyWatch.server.Service.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.ADMIN;
import static br.com.AllyWatch.server.Domain.Enum.SpecialistType.LAWYER;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private LawyerService lawyerService;

    @Autowired
    private PsychologistService psychologistService;

    @GetMapping("/reports")
    @Secured(ADMIN)
    public List<ReportResponse> listReports() {
        return reportService.listAll();
    }

    @PutMapping("/verify-specialist/{specialistId}")
    @Secured(ADMIN)
    public void verifySpecialist(@RequestBody @Valid VerifySpecialistRequest request,
                                 @PathVariable long specialistId) {
        if (request.getType() == LAWYER) {
            lawyerService.verify(request, specialistId);
        } else {
            psychologistService.verify(request, specialistId);
        }
    }
}
