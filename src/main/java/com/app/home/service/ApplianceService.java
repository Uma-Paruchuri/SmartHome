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

    public void turnOnAll(){
        applianceMap.values().forEach(ApplianceInterface::turnOn);
    }

    public String turnOnBasedOnType(String type, Integer setting){
        ApplianceInterface applianceInterface = get(type);
        if(applianceInterface == null){
            throw new IllegalArgumentException("Unknown Appliance Name : " + type);
        }
        if(setting != null){
           return applianceInterface.turnOn(setting);
        }else {
            return applianceInterface.turnOn();
        }
    }

    public String getState(String type){
        ApplianceInterface applianceInterface = get(type);
        if(applianceInterface != null){
           return applianceInterface.getState();
        }else{
            throw new IllegalArgumentException("Unknown Appliance Name : " + type);
        }
    }

    public Map<String,String> getstatus(){
        Map<String,String> responseStatus = new HashMap<>();
        for(String type : applianceMap.keySet()){
            responseStatus.put(type,applianceMap.get(type).getState());
        }
        return responseStatus;
    }
}
