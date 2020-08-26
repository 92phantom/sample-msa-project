package api.report.msa.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<EnergyReport, String> {


}
