package com.nhsd.a2si.capacity.reporting.service.dto.waittime;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * A JSON entity of Service Wait Times used by the REST API.
 */
public class WaitTime {

    @NotNull
    private Service service;

    @PositiveOrZero
    private long waitTimeInMinutes;

    @JsonFormat(locale = "en", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT")
    private Date updated;

    @NotNull
    private Provider provider;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public long getWaitTimeInMinutes() {
        return waitTimeInMinutes;
    }

    public void setWaitTimeInMinutes(long waitTimeInMinutes) {
        this.waitTimeInMinutes = waitTimeInMinutes;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    @Override
    public String toString()
    {
    	return "provider=" + this.provider.toString() + " : "
    			+ "service=" + this.service.toString() + " : "
    			+ "updated=" + this.updated.toString() + " : "
    			+ "waitTimeInMinutes=" + this.waitTimeInMinutes;
    }
}
