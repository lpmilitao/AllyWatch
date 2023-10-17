package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.DTO.Mapper.ReportMapper;
import br.com.AllyWatch.server.DTO.Request.ReportRequest;
import br.com.AllyWatch.server.DTO.Response.ReportResponse;
import br.com.AllyWatch.server.Domain.Post;
import br.com.AllyWatch.server.Domain.Report;
import br.com.AllyWatch.server.Domain.User;
import br.com.AllyWatch.server.Repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Transactional
    public void add(String authorization, long postId, ReportRequest request) {
        User user = userService.getAuthenticatedUser(authorization);
        Post post = postService.findById(postId);

        Report report = Report.builder()
                .body(request.getBody())
                .reportTime(LocalDateTime.now())
                .checked(false)
                .build();

        user.addReport(report);
        post.addReport(report);

        reportRepository.save(report);
    }

    public List<ReportResponse> listAll() {
        return reportRepository.findAll().stream().map(ReportMapper::toResponse).toList();
    }
}
