package api.reserve.msa.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<EnergyReservation, String> {
}
