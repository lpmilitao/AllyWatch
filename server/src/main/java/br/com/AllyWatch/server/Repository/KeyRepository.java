package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.KeyCrypt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<KeyCrypt, Long> {
}
