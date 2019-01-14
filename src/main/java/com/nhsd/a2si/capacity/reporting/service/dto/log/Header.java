package com.nhsd.a2si.capacity.reporting.service.dto.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("action")
    private String action;

    @JsonProperty("component")
    private String component;

    @JsonProperty("user-id")
    private String userId;

    @JsonProperty("endpoint")
    private String endpoint;

    @JsonProperty("request-responce-hashcode")
    private String hashcode;

    @JsonProperty("timestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @JsonProperty("details")
    private List<Detail> details = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }
    
    @Override
    public String toString()
    {
    	return "action=" + this.action + " : "
    			+ "component=" + this.component + " : "
    			+ "endpoint=" + this.endpoint + " : "
    			+ "id=" + this.id + " : "
    			+ "timestamp=" + this.timestamp.toString() + " : "
    			+ "user=" + this.userId;
    }
}
