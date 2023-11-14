package br.com.AllyWatch.server.DTO.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class PostEditResponse {

    private String body;

    private String aggressor;

    private boolean anonymous;
}
