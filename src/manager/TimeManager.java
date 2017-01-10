package manager;

import schedule.Time;
import schedule.TimePair;
import schedule.Weekday;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeManager {
    // ArrayList of operating times
    private HashMap<Weekday,ArrayList<TimePair>> operationTimes;

    public TimeManager() {
        operationTimes = new HashMap<>();
    }

    public HashMap<Weekday,ArrayList<TimePair>> getOperationTimes() {
        return operationTimes;
    }

    public void addOperatingTime(Weekday day, Time startTime, Time endTime) {
        // use case: when start time and end time are on the same day
        // create arrayList if not yet created
        operationTimes.putIfAbsent(day, new ArrayList<>());
        operationTimes.get(day).add(new TimePair(startTime,endTime));
    }
}
