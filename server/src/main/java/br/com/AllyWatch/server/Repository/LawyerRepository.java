package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
}
