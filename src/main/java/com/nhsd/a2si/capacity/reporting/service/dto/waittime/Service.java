package com.nhsd.a2si.capacity.reporting.service.dto.waittime;

import javax.validation.constraints.NotNull;

/**
 * A JSON/XML version of the service entity seen from the API.
 *
 */
public class Service {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String region;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    @Override
    public String toString()
    {
    	return "serviceId=" + this.id + " : "
    			+ "serviceName=" + this.name + " : "
    			+ "serviceRegion=" + this.region;
    }
}
