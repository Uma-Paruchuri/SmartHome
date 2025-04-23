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
import org.springframework.http.ResponseEntity;

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
}
