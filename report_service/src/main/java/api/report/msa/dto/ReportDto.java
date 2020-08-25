package api.report.msa.dto;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class ReportDto {

    private String device_id;
    private long interval;
    private String interval_unit;
    private long current_usage ;
    private long threshold;
    private String unit;
    private Date timeStamp;
    private String server_id;
    private String responseStatus;

    public String toEntity(){
        JsonObject obj = new JsonObject();
        obj.addProperty("responseStatus", getResponseStatus());
        obj.addProperty("device_id", getDevice_id());
        obj.addProperty("interval", getInterval());
        obj.addProperty("interval_unit", getInterval_unit());
        obj.addProperty("current_usage", getCurrent_usage());
        obj.addProperty("threshold", getThreshold());
        obj.addProperty("unit", getUnit());
        obj.addProperty("timeStamp", new Date(System.currentTimeMillis())+"");
        obj.addProperty("server_id", getServer_id());

        return obj.toString();
    }
}
