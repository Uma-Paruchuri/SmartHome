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
        return "AC is: " + thermostatState + ", Current Temperature is: " + ac_temp_F;
    }

    @Override
    public void turnOff() {
        thermostatState = "off";
    }

    @Override
    public String turnOn() {
        return turnOn(20);
    }

    @Override
    public String turnOn(int speed) {
        ac_temp_F = speed;
        thermostatState = "on";

        return this.getState();
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
