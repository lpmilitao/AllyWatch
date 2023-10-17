package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
