package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
