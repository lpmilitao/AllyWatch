package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Domain.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    List<Psychologist> findAllByStatusLike(Status status);
}
