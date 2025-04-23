package com.app.home.service;

import com.app.home.model.AirConditioner;
import com.app.home.model.ApplianceInterface;
import com.app.home.model.Fan;
import com.app.home.model.Light;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplianceService {


    private final Map<String, ApplianceInterface> applianceMap = new HashMap<>();
    public ApplianceService() {
        applianceMap.put("light", new Light());
        applianceMap.put("fan", new Fan());
        applianceMap.put("ac", new AirConditioner());
    }

    @Autowired
    private Fan fan;

    @Autowired
    private AirConditioner airConditioner;

    //@Autowired
    //private ApplianceInterface applianceInterface;


    public void turnOffAll(){
        applianceMap.values().forEach(ApplianceInterface::turnOff);
    }

    public ApplianceInterface get(String type){
        return applianceMap.get(type.toLowerCase());
    }

    public void turnOff(String type){
        ApplianceInterface applianceInterface = get(type);
        if(applianceInterface != null){
            applianceInterface.turnOff();
        }
    }

    public String adjustFanSpeed(int speed){
        return fan.adjustFanSpeed(speed);
    }

    public String setACTemp(int temp){
        return airConditioner.setTemp(temp);
    }
}
