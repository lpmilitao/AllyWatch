package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost_IdOrderByPublicationTimeDesc(long postId);
}
