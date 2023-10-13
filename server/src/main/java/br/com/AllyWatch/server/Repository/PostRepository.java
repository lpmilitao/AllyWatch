package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
