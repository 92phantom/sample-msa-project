package api.enroll.msa.domain;


import api.enroll.msa.dto.EnrollDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "device_enroll")
public class DeviceEnroll {

    @Id
    @Column (name = "device_id", length = 20, nullable = false)
    private String device_id;

    @Column (name = "server_id", length = 20, nullable = false)
    private String server_id;

    @Column (name = "audit_id", length = 20, nullable = false)
    private String audit_id;

    @Column (name = "audit_dtm", nullable = false)
    private Date audit_dtm;

    @Column (name = "sub_dev_number", nullable = false)
    private long sub_dev_number;

    @Builder
    public DeviceEnroll(String device_id, String server_id, long sub_dev_number){
        this.device_id = device_id;
        this.server_id = server_id;
        this.sub_dev_number = sub_dev_number;
        this.audit_id = "MSA_ENROLL";
        this.audit_dtm = new Date(System.currentTimeMillis());
    }

    public void update(String server_id, long sub_dev_number){
        this.server_id = server_id;
        this.sub_dev_number = sub_dev_number;
    }


}
