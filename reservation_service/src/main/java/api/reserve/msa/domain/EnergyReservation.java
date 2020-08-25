package api.reserve.msa.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "energy_reservation")
public class EnergyReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_num")
    private Long id;

    @Column(name = "device_id", nullable = false, length = 20)
    private String device_id;

    @Column(name = "server_id", nullable = false, length = 20)
    private String server_id;

    @Column(name = "reserve_amount", nullable = false)
    private long reserve_amount;

    @Column(name = "req_dt", nullable = false, length = 8)
    private String req_dt;

    @Column(name = "reserve_status", nullable = true, length = 2)
    private String reserve_status;

    @Column (name = "audit_id", length = 20, nullable = false)
    private String audit_id;

    @Column (name = "audit_dtm", nullable = false)
    private Date audit_dtm;

    @Builder
    public EnergyReservation(String device_id, String server_id, long reserve_amount, String req_dt, String reserve_status){
        this.device_id = device_id;
        this.server_id = server_id;
        this.reserve_amount = reserve_amount;
        this.req_dt = req_dt;
        this.audit_id = "MSA_RESERVATION";
        this.reserve_status = reserve_status;
        this.audit_dtm = new Date(System.currentTimeMillis());
    }

    public void update(String server_id, long reserve_amount, String req_dt, String reserve_status){
        this.server_id = server_id;
        this.reserve_status = reserve_status;
        this.reserve_amount = reserve_amount;
        this.req_dt = req_dt;
    }





}
