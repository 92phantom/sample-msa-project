package api.reserve.msa.dto;

import api.reserve.msa.domain.EnergyReservation;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class ReserveDto {

    private String device_id;
    private String server_id;
    private long reserve_amount;
    private String req_dt;
    private String reserve_status;
    private String responseStatus;

    @Builder
    public ReserveDto(String device_id, String server_id, long reserve_amount, String req_dt, String reserve_status){
        this.device_id = device_id;
        this.server_id = server_id;
        this.reserve_amount = reserve_amount;
        this.req_dt = req_dt;
        this.reserve_status = reserve_status;
    }

    public EnergyReservation toEntity(){

        return EnergyReservation.builder()
                .device_id(device_id)
                .server_id(server_id)
                .reserve_amount(reserve_amount)
                .req_dt(req_dt)
                .reserve_status(reserve_status)
                .build();
    }

    public String toJsonEntity(){
        JsonObject obj = new JsonObject();
        obj.addProperty("responseStatus", getResponseStatus());
        obj.addProperty("device_id", getDevice_id());
        obj.addProperty("server_id", getServer_id());
        obj.addProperty("reserve_amount", getReserve_amount());
        obj.addProperty("reserve_status", getReserve_status());
        obj.addProperty("req_dt", getReq_dt());
        obj.addProperty("timeStamp", new Date(System.currentTimeMillis())+"");

        return obj.toString();
    }


}
