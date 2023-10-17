package br.com.AllyWatch.server.Controller;

import br.com.AllyWatch.server.DTO.Response.ReportResponse;
import br.com.AllyWatch.server.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.AllyWatch.server.Domain.Enum.Role.Names.ADMIN;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    @Secured(ADMIN)
    public List<ReportResponse> listReports(){
        return reportService.listAll();
    }
}
