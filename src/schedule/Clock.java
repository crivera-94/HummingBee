package schedule;

import main.HummingBee;

public class Clock implements Runnable {
    private HummingBee mainSystem;

    private Thread t;
    private Weekday currentDay;
    private int hour;
    private int minutes;
    private int SCALE = 1000;

    // clock set to start at:
    // Tuesday 14:15 (2:15 pm)
    // SCALE = number of milliseconds per simulated minute
    public Clock (HummingBee mainSystem) {
        
        this.mainSystem = mainSystem;
        currentDay = Weekday.tuesday;
        hour = 23;
        minutes = 00;
        System.out.println("Creating clock...");
    }

    public void run() {
        System.out.println("Running clock...");
        try {
            while (true) {
                if (minutes==59) {
                    if (hour==23) {
                        hour = 0;
                        minutes = 0;
                        currentDay = currentDay.getNextDay();
                    } else {
                        hour++;
                        minutes = 0;
                    }
                } else {
                    minutes++;
                }

                System.out.print(hour + ":");
                String formatted = String.format("%02d", minutes);
                System.out.print(formatted);
                System.out.println(" " + currentDay);

                /**
                 * MAIN UPDATE FUNCTION
                 */

                // perform system check, and update backend
                mainSystem.updateAll();

                // update frontend
                mainSystem.updateVisuals();

                Thread.sleep(SCALE);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted...");
        }
        System.out.println("Thread exiting...");
    }

    public void start () {
        System.out.println("Starting clock...");
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    /**
     * GETTERS AND SETTERS
     */
    public Weekday getCurrentDay() {
        return currentDay;
    }
    public void setCurrentDay(Weekday currentDay) {
        this.currentDay = currentDay;
    }

    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
