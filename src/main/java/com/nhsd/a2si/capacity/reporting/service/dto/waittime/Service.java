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
}
