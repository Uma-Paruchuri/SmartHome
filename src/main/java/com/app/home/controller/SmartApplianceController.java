package com.app.home.controller;

import com.app.home.service.ApplianceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appliances")
public class SmartApplianceController {

    private final ApplianceService applianceService;

    public SmartApplianceController(ApplianceService mockService, ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    @PostMapping("/{type}/off")
    public ResponseEntity<String> turnOff(@PathVariable String type){
        applianceService.turnOff(type);
        return ResponseEntity.ok(type + " is turned off");
    }

    @PostMapping("/turnOffAll")
    public ResponseEntity<String> turnOffAllAppliances(){
        applianceService.turnOffAll();
        return ResponseEntity.ok("All appliances are turned off");
    }

    @PutMapping("/setFanSpeed/{speed}")
    public String adjustFanSpeed(@PathVariable int speed){
        return applianceService.adjustFanSpeed(speed);
    }

    @PutMapping("/setACTemp/{temp}")
    public String setACTemp(@PathVariable int temp){
        return applianceService.setACTemp(temp);
    }


}
