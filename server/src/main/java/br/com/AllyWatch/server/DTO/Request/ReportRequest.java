package br.com.AllyWatch.server.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportRequest {

    @NotBlank
    private String body;
}