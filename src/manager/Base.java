package manager;

import location.Area;
import sprinkler.Sprinkler;

import java.util.ArrayList;

public abstract class Base {
    // region identifier
    protected Area region;

    // time region has been active on a particular day
    protected int timeActive;

    // indicate if region is currently watering
    protected boolean activeIndicator;
    // indicate if region is enabled
    protected boolean enabledIndicator;

    // ArrayList of sprinklers
    protected ArrayList<Sprinkler> sprinklers;

    public Base(Area location) {
        // system is enabled, but inactive on creation

        // create sprinkler array
        sprinklers = new ArrayList<>();

        // create 4 sprinklers all with the same watering rate
        // 5 gallons per minute
        sprinklers.add(new Sprinkler(location,5));
        sprinklers.add(new Sprinkler(location,5));
        sprinklers.add(new Sprinkler(location,5));
        sprinklers.add(new Sprinkler(location,5));

        region = location;
        enabledIndicator = true;
        activeIndicator = false;

        // system has not been running, set to zero
        timeActive = 0;
    }

    public int getTimeActive() { return timeActive;}

    public void resetTimeActive() { timeActive = 0; }
    
    public void incrementTime(){ timeActive++; }

    public boolean getActiveIndicator() {
        return activeIndicator;
    }

    public boolean getEnabledIndicator() {
        return enabledIndicator;
    }

    // activate the sprinklers
    public void activateRegion() {
        activeIndicator = true;
    }

    // deactivate the sprinklers
    public void deactivateRegion() {
        activeIndicator = false;
    }

    // enable all sprinklers in region
    public void enableRegion() {
        enabledIndicator = true;
    }

    // disable all sprinklers in region
    public void disableRegion() {
        enabledIndicator = false;
    }

}
