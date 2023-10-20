package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitationRepository extends JpaRepository<Solicitation, Long> {
}
