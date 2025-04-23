package com.app.home.model;

import org.springframework.stereotype.Component;

@Component
public class Fan implements ApplianceInterface{

    private String fanState = "on";

    public int fanSpeed = 1;

    @Override
    public String getName() {
        return "Fan";
    }

    @Override
    public String getState() {
        return fanState;
    }

    @Override
    public void turnOff() {
        fanSpeed = 0;
        fanState = "off";
    }

    public String adjustFanSpeed(int speed){
        if(fanState.equals("on")) {
            fanSpeed = speed;
            return "Fan set to :" + fanSpeed;
        }else {
            return "Please turn on the fan";
        }
    }
}
