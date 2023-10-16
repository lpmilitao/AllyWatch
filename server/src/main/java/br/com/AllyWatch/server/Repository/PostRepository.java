package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByAuthor_IdOrderByPublicationTimeDesc(long authorId, Pageable pageable);
}
