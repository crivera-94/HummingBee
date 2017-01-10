package main;

import console.userInterface;
import location.Area;
import manager.RegionManager;
import manager.TemperatureManager;
import manager.WaterManager;
import schedule.Clock;
import schedule.Time;
import schedule.Weekday;

public class HummingBee {
    // UI this system is tethered to
    private userInterface UI;

    // MAIN CLOCK
    public Clock clock;

    // managers for the four regions
    private RegionManager north;
    private RegionManager east;
    private RegionManager south;
    private RegionManager west;

    // water manager for entire system
    private WaterManager waterManager;

    public HummingBee(userInterface UI) {
        // create reference to user interface object
        this.UI = UI;

        // create the four region managers
        north = new RegionManager(Area.north);
        east = new RegionManager(Area.east);
        south = new RegionManager(Area.south);
        west = new RegionManager(Area.west);

        // create water manager
        waterManager = new WaterManager();

        // start clock and send HummingBee as parameter
        clock = new Clock(this);
        clock.start();
    }

    // enable single region
    public void enableRegion(Area location) {
        switch (location) {
            case north:
                north.enableRegion();
                break;
            case east:
                east.enableRegion();
                break;
            case south:
                south.enableRegion();
                break;
            case west:
                west.enableRegion();
                break;
        }
    }

    // disable single region
    public void disableRegion(Area location) {
        switch (location) {
            case north:
                north.disableRegion();
                break;
            case east:
                east.disableRegion();
                break;
            case south:
                south.disableRegion();
                break;
            case west:
                west.disableRegion();
                break;
        }
    }

    // add an operating time for a region
    public void addOperatingTime(Area location, Weekday day, Time startTime, Time endTime) {
        switch (location) {
            case north:
                north.getTimeManager().addOperatingTime(day,startTime,endTime);
                break;
            case east:
                east.getTimeManager().addOperatingTime(day,startTime,endTime);
                break;
            case south:
                south.getTimeManager().addOperatingTime(day,startTime,endTime);
                break;
            case west:
                west.getTimeManager().addOperatingTime(day,startTime,endTime);
                break;
        }
    }

    // set temperature range for a region
    public void setTemperatureRange(Area location, int lowTemp, int highTemp) {
        switch (location) {
            case north: {
                north.getTempManager().setLowTemp(lowTemp);
                north.getTempManager().setHighTemp(highTemp);
                break;
            }
            case east: {
                east.getTempManager().setLowTemp(lowTemp);
                east.getTempManager().setHighTemp(highTemp);
                break;
            }
            case south: {
                south.getTempManager().setLowTemp(lowTemp);
                south.getTempManager().setHighTemp(highTemp);
                break;
            }
            case west: {
                west.getTempManager().setLowTemp(lowTemp);
                west.getTempManager().setHighTemp(highTemp);
                break;
            }
        }
    }

    // returns true if region is active at the moment else return false
    public boolean checkStatus(Area location) {
        switch (location) {
            case north: return north.getActiveIndicator();
            case east: return east.getActiveIndicator();
            case south: return south.getActiveIndicator();
            case west: return west.getActiveIndicator();
        }
        return false;
    }

    // increment temperature by one and return new value
    public int incrementTemp() {
        TemperatureManager.setTemperature(TemperatureManager.getTemperature()+1);
        return TemperatureManager.getTemperature();
    }

    // decrement temperature by one and return new value
    public int decrementTemp() {
        TemperatureManager.setTemperature(TemperatureManager.getTemperature()-1);
        return TemperatureManager.getTemperature();
    }

    // disables all regions
    public void disableAll() {
        disableRegion(Area.north);
        disableRegion(Area.east);
        disableRegion(Area.south);
        disableRegion(Area.west);
    }

    // enables all regions
    public void enableAll() {
        enableRegion(Area.north);
        enableRegion(Area.east);
        enableRegion(Area.south);
        enableRegion(Area.west);
    }

    // main logical update function
    public void updateAll() {
        north.update(clock.getHour(),clock.getMinutes(),clock.getCurrentDay());
        east.update(clock.getHour(),clock.getMinutes(),clock.getCurrentDay());
        south.update(clock.getHour(),clock.getMinutes(),clock.getCurrentDay());
        west.update(clock.getHour(),clock.getMinutes(),clock.getCurrentDay());

        if (clock.getHour()==23 && clock.getMinutes()==59) {
            int total = north.getTimeActive() + east.getTimeActive() + south.getTimeActive() + west.getTimeActive();
            waterManager.update(total,clock.getCurrentDay(),20);
            north.resetTimeActive();
            east.resetTimeActive();
            south.resetTimeActive();
            west.resetTimeActive();
        }
    }

    public WaterManager getWaterMan(){
    	return waterManager;
    }
    // main visual update function
    public void updateVisuals() {
        // ++ Place whatever function updates visuals here
        UI.setDisplay();
        UI.setDisplayClock();
    }
}
