package com.app.home;

import com.app.home.controller.SmartApplianceController;
import com.app.home.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ApplianceControllerTest {

    @Mock
    private ApplianceService applianceService;

    @InjectMocks
    private SmartApplianceController controller;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testAdjustFanSpeed() {
        int speed = 2;
        String expectedResponse = "Fan speed set to 2";

        when(applianceService.adjustFanSpeed(speed)).thenReturn(expectedResponse);

        String result = controller.adjustFanSpeed(speed);
        assertEquals(expectedResponse, result);

        verify(applianceService, times(1)).adjustFanSpeed(speed);
    }

    @Test
    void testTurnOffAllAppliances() {

        doNothing().when(applianceService).turnOffAll();

        ResponseEntity<String> response = controller.turnOffAllAppliances();

        assertEquals("All appliances are turned off", response.getBody());

        verify(applianceService, times(1)).turnOffAll();
    }
    /**
        Adding:
        --AC set temp test.
        --Turn on all
        --Turn on based on type bad request
        --Get All appliances status
        --Get Status based on type
     */

    @Test
    void testSetACTemp(){
        int temp = 26;
        String expectedResponse = "Air Conditioner set to : 26";

        when(applianceService.setACTemp(temp)).thenReturn(expectedResponse);

        String result = controller.setACTemp(temp);

        assertEquals(expectedResponse,result);

        verify(applianceService, times(1)).setACTemp(temp);
    }

    @Test
    void testTurnOnAllAppliances(){
        doNothing().when(applianceService).turnOnAll();

        ResponseEntity<String> response = controller.turnOnAllAppliances();

        assertEquals("All appliances are turned on", response.getBody());

        verify(applianceService, times(1)).turnOnAll();
    }

    @Test
    void testTurnOnBasedOnType_badRequest() throws Exception {
        when(applianceService.turnOnBasedOnType("TV",null))
                .thenThrow(new IllegalArgumentException("Unknown Appliance Name : TV"));

        ResponseEntity<String> response = controller.turnOnAppliance("TV",null);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals("Unknown Appliance Name : TV",response.getBody());

        verify(applianceService, times(1)).turnOnBasedOnType("TV",null);
    }

    @Test
    void testGetHomeAppliancesStatus(){

        Map<String, String> mockStatus = Map.of(
                "light", "Light is on",
                "fan","Fan is: on, Fan speed is: 1",
                "ac", "AC is: on, Current Temperature is: 20"
        );
        when(applianceService.getstatus()).thenReturn(mockStatus);

        Map<String,String> response = controller.smartHomeStatus();

        assertNotNull(response);
        assertEquals(3,response.size());

        verify(applianceService,times(1)).getstatus();

    }

}
