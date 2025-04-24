package com.app.home.model;

import java.util.Optional;

public interface ApplianceInterface {
    public String getName();
    public String getState();
    public void turnOff();

    public String turnOn();

    public String turnOn(int speed);



}
