package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
}
