package com.nhsd.a2si.capacity.reporting.service.dto.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Detail {

    @JsonProperty("id")
    private String id;

    @JsonProperty("service-id")
    private String serviceId;

    @JsonProperty("timestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @JsonProperty("wait-time-in-minutes")
    private Integer waitTimeInMinutes;

    @JsonProperty("age-in-minutes")
    private Integer ageInMinutes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getWaitTimeInMinutes() {
        return waitTimeInMinutes;
    }

    public void setWaitTimeInMinutes(Integer waitTimeInMinutes) {
        this.waitTimeInMinutes = waitTimeInMinutes;
    }

    public Integer getAgeInMinutes() {
        return ageInMinutes;
    }

    public void setAgeInMinutes(Integer ageInMinutes) {
        this.ageInMinutes = ageInMinutes;
    }
}