package manager;

public class TemperatureManager {
    private static int temperature;
    private int lowTemp;
    private int highTemp;

    public TemperatureManager() {
        TemperatureManager.temperature = 70;
        this.lowTemp = 60;
        this.highTemp = 80;
    }

    public double getLowTemp() {
        return lowTemp;
    }
    public void setLowTemp(int lowTemp) {
        this.lowTemp = lowTemp;
    }

    public double getHighTemp() {
        return highTemp;
    }
    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    public static void setTemperature(int temperature) {
        TemperatureManager.temperature = temperature;
    }
    public static int getTemperature() {
        return TemperatureManager.temperature;
    }
}
