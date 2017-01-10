package schedule;

public enum Weekday {
	sunday,
	monday,
    tuesday,
    wednesday,
    thursday,
    friday,
    saturday;

    public Weekday getNextDay() {
        switch (this) {
            case monday: return tuesday;
            case tuesday: return wednesday;
            case wednesday: return thursday;
            case thursday: return friday;
            case friday: return saturday;
            case saturday: return sunday;
            case sunday: return monday;
            default: return this.getNextDay();
        }
    }

    public static Weekday stringToWeekday(String day){
        switch (day){
            case "monday": return monday;
            case "tuesday": return tuesday;
            case "wednesday": return wednesday;
            case "thursday": return thursday;
            case "friday": return friday;
            case "saturday": return saturday;
            case "sunday": return sunday;
            default: return null;
        }
    }
}
