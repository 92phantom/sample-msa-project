package api.report.msa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Entity
@Table
public class EnergyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "device_id", nullable = false, length = 20)
    private String device_id;

    @Column(name = "server_id", nullable = false, length = 20)
    private String server_id;

    @Column(name = "current_usage", nullable = false)
    private long current_usage;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "unit", nullable = false, length = 10)
    private String unit;

    @Column(name = "interval", nullable = false)
    private long interval;

    @Column(name = "interval_unit", nullable = false, length = 10)
    private String interval_unit;

    @Column(name = "threshold", nullable = true)
    private long threshold;

    @Column (name = "audit_id", length = 20, nullable = false)
    private String audit_id;

    @Column (name = "audit_dtm", nullable = false)
    private Date audit_dtm;

    @Builder
    public EnergyReport(String device_id, String server_id, long current_usage, long threshold, Date timestamp, String unit, long interval, String interval_unit){

        this.device_id = device_id;
        this.server_id = server_id;
        this.current_usage = current_usage;
        this.timestamp = timestamp;
        this.threshold = threshold;
        this.unit = unit;
        this.interval  = interval;
        this.interval_unit = interval_unit;
        this.audit_id = "MSA_REPORT";
        this.audit_dtm = new Date(System.currentTimeMillis());

    }

}
