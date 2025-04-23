package com.app.home.model;

import org.springframework.stereotype.Component;

@Component
public class Light implements ApplianceInterface{

    private String lightState = "on";
    @Override
    public String getName() {
        return "Light";
    }

    @Override
    public String getState() {
        return "Light is : " + lightState;
    }

    @Override
    public void turnOff() {
        lightState = "off";
    }
}
