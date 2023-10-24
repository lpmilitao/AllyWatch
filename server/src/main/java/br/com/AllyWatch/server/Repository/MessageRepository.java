package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
