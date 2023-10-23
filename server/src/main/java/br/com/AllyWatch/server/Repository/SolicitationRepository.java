package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Domain.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitationRepository extends JpaRepository<Solicitation, Long> {
    List<Solicitation> findAllByUser_IdAndStatusEquals(long id, Status status);

    List<Solicitation> findAllByStatusLike(Status status);
}
