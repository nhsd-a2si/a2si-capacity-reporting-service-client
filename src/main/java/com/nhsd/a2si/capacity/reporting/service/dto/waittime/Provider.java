package com.nhsd.a2si.capacity.reporting.service.dto.waittime;

import javax.validation.constraints.NotNull;

public class Provider {

    @NotNull
    private String name;

    @NotNull
    private String region;

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
}
