package br.com.AllyWatch.server.DTO.Response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ReportResponse {

    private long id;

    private String body;

    private boolean checked;

    private String reportTime;

    private long postId;

    private String postTitle;

    private String author;

    private String authorEmail;
}
