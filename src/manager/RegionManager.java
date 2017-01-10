package manager;

import location.Area;
import schedule.TimePair;
import schedule.Weekday;

public class RegionManager extends Base {

    private TimeManager timeManager;
    private TemperatureManager tempManager;

    /**
     * Default Constructor:
     */
    public RegionManager(Area location) {
        super(location);
        timeManager = new TimeManager();
        tempManager = new TemperatureManager();
    }

    /**
     * Getter Functions
     */
    public TimeManager getTimeManager() {
        return timeManager;
    }

    public TemperatureManager getTempManager() {
        return tempManager;
    }

    /**
     * Update Functions
     */
    public void setTemperatureRange(int lowTemp, int highTemp) {
        // update sprinklers
        // if temperature outside of new range, disable all
        tempManager.setLowTemp(lowTemp);
        tempManager.setHighTemp(highTemp);
        updateUsingTemperature();
    }

    // function to update region using indicator value
    public void updateUsingIndicator() {
        /**
         * serves as final check, checks with enabled indicator
         * called from updateUsingTemperature()
         * - lowest level
         */
        if (enabledIndicator) {
            // region is enabled
            // watering rate set to 10 for each sprinkler
            activateRegion();
            incrementTime();
        } else {
            // region is disabled
            deactivateRegion();
        }
    }

    // function to update region using temperature
    public void updateUsingTemperature() {
        /**
         * serves as second check, checks with specified temperature
         * called from updateUsingTime()
         * - middle level
         */
        if ((TemperatureManager.getTemperature()<tempManager.getLowTemp()) ||
                TemperatureManager.getTemperature()>tempManager.getHighTemp()) {
            // current temperature is outside range, not active
            deactivateRegion();
        } else {
            // in correct temperature range, check indicator
            updateUsingIndicator();
        }
    }

    // function to update region using time
    public void updateUsingTime(int hours, int minutes, Weekday day) {
        /**
         * returns true if has been active, else returns false
         *
         * serves as first check, checks with time
         * called from mainUpdate()
         * - highest level
         */
        if (timeManager.getOperationTimes().containsKey(day)) {
            // active on this day
            for (TimePair times : timeManager.getOperationTimes().get(day)) {

                if (hours==times.getStartTime().getHours() &&
                        hours==times.getEndTime().getHours()) {

                    // start and end time are at the same hour
                    if (minutes<times.getEndTime().getMinutes() && minutes>=times.getStartTime().getMinutes()) {
                        updateUsingTemperature();
                        return;
                    } else {
                        deactivateRegion();
                    }

                } else if (hours==times.getStartTime().getHours() &&
                        hours!=times.getEndTime().getHours()) {

                    // start and end at different hours, hours currently at start hour
                    if (minutes>=times.getStartTime().getMinutes()) {
                        updateUsingTemperature();
                        return;
                    }else{
                    	deactivateRegion();
                    }

                } else if (hours>times.getStartTime().getHours() &&
                        hours<times.getEndTime().getHours()) {

                    // inside hour range, exclusive
                    updateUsingTemperature();
                    return;

                } else if (hours!=times.getStartTime().getHours() &&
                        hours==times.getEndTime().getHours()) {
                    // hour is at end time hour
                    if (minutes<times.getEndTime().getMinutes()) {
                        updateUsingTemperature();
                        return;
                    }else{
                    	deactivateRegion();
                    }
                } else {
                    // not in range, deactivate it
                    deactivateRegion();
                }
            }
        } else {
            // not active on this day
            deactivateRegion();
        }
    }

    // main update function
    public void update(int hours, int minutes, Weekday day) {
        updateUsingTime(hours,minutes,day);
    }
}
