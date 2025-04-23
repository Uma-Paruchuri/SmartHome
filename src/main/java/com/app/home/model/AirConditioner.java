package com.app.home.model;

import org.springframework.stereotype.Component;

@Component
public class AirConditioner implements ApplianceInterface{

    private String thermostatState = "on";
    private int ac_temp_F = 20;
    @Override
    public String getName() {
        return "Air Conditioner";
    }

    @Override
    public String getState() {
        return "AC is : " + thermostatState;
    }

    @Override
    public void turnOff() {
        thermostatState = "off";
    }

    public String setTemp(int temp){
        if(thermostatState.equals("on")){
            ac_temp_F = temp;
            return "Air Conditioner set to : " + ac_temp_F;
        }else {
            return "Thermostat is off, please turn on the AC";
        }

    }
}
