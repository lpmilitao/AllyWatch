package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Enum.Status;
import br.com.AllyWatch.server.Domain.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    List<Lawyer> findAllByStatusLike(Status status);
}
