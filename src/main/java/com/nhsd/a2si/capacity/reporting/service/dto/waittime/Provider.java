package com.nhsd.a2si.capacity.reporting.service.dto.waittime;

import javax.validation.constraints.NotNull;

public class Provider {

    @NotNull
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
