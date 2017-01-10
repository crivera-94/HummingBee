package schedule;

public class Time {
    private int hours;
    private int minutes;

    // default constructor, set to 00:00:00
    public Time() {
        this.hours = 0;
        this.minutes = 0;
    }

    // constructor, seconds parameter default to 0
    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
