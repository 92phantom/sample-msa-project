package api.enroll.msa.dto;

import api.enroll.msa.domain.DeviceEnroll;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollDto {

    private String device_id;
    private String server_id;
    private long sub_dev_number;
    private String responseStatus;

    @Builder
    public EnrollDto(String device_id, String server_id, long sub_dev_number, String responseStatus){
        this.device_id = device_id;
        this.server_id = server_id;
        this.sub_dev_number = sub_dev_number;
        this.responseStatus = responseStatus;
    }

    public DeviceEnroll toEntity(){

        return DeviceEnroll.builder()
                .device_id(device_id)
                .server_id(server_id)
                .sub_dev_number(sub_dev_number)
                .build();
    }

    public String toJsonEntity(){

        JsonObject obj = new JsonObject();
        obj.addProperty("responseStatus", getResponseStatus());
        obj.addProperty("device_id", getDevice_id());
        obj.addProperty("sub_dev_number", getSub_dev_number());
        obj.addProperty("timeStamp", new java.util.Date(System.currentTimeMillis())+"");
        obj.addProperty("server_id", getServer_id());

        return obj.toString();
    }

}
