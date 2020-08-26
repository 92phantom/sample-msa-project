package api.report.msa.dto;

import api.report.msa.domain.EnergyReport;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import lombok.*;

import java.sql.Date;


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

    @Builder
    public ReportDto(String device_id, String server_id, long current_usage, long threshold, String unit, long interval, String interval_unit, String responseStatus){
        this.device_id = device_id;
        this.server_id = server_id;
        this.current_usage = current_usage;
        this.threshold = threshold;
        this.unit = unit;
        this.interval = interval;
        this.interval_unit = interval_unit;
        this.timeStamp = new Date(System.currentTimeMillis());
        this.responseStatus = responseStatus;
    }

    public EnergyReport toEntity(){
        return EnergyReport.builder()
                .device_id(device_id)
                .server_id(server_id)
                .current_usage(current_usage)
                .timestamp(timeStamp)
                .threshold(threshold)
                .unit(unit)
                .interval(interval)
                .interval_unit(interval_unit)
                .build();
    }

    public String toJsonEntity(){
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
