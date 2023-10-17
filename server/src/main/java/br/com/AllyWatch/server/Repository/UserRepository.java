package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
