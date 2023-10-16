package br.com.AllyWatch.server.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    private String aggressor;

    @NotNull
    private boolean anonymous;
}
