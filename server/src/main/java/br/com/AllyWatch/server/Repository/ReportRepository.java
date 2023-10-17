package br.com.AllyWatch.server.Repository;

import br.com.AllyWatch.server.Domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
