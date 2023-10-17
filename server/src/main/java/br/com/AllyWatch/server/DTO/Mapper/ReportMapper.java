package br.com.AllyWatch.server.DTO.Mapper;

import br.com.AllyWatch.server.DTO.Response.ReportResponse;
import br.com.AllyWatch.server.Domain.Report;

import static br.com.AllyWatch.server.Security.Cryptography.decrypt;

public class ReportMapper {
    public static ReportResponse toResponse(Report entity) {
        return ReportResponse.builder()
                .id(entity.getId())
                .body(entity.getBody())
                .reportTime(entity.getReportTime())
                .postId(entity.getPost().getId())
                .postTitle(entity.getPost().getTitle())
                .author(
                        decrypt(entity.getAuthor().getFullname(),
                                entity.getAuthor().getKeys().getPrivateKey())
                )
                .authorEmail(
                        decrypt(entity.getAuthor().getEmail(),
                                entity.getAuthor().getKeys().getPrivateKey())
                )
                .build();
    }
}
