package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
//@Query("select distinct u from Usuario u join u.aulasMinistradas a where a.curso = :curso")
    @Query("select c from Chat c where c.open = :open")
    List<Chat> findAllByOpenLike (boolean open);
}
