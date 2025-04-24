package com.app.home.controller;

import com.app.home.service.ApplianceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /**
        Added:  ---Turn ON All ,
                ---turn on based on type,
                ---get state based on type,
                ---get all appliances status
     */
    @PostMapping("/turnOnAll")
    public ResponseEntity<String> turnOnAllAppliances(){
        applianceService.turnOnAll();
        return ResponseEntity.ok("All appliances are turned on");
    }

    @PostMapping("/{type}/turnOn")
    public ResponseEntity<String> turnOnAppliance(@PathVariable String type,
                                                  @RequestParam(required = false) Integer setting){
        try {
            String response = applianceService.turnOnBasedOnType(type,setting);
            return ResponseEntity.ok(response);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{type}/state")
    public String getApplianceState(@PathVariable String type){
        return applianceService.getState(type);
    }

    @GetMapping("/getAllApplianceStatus")
    public Map<String,String> smartHomeStatus(){
        return applianceService.getstatus();
    }


}
