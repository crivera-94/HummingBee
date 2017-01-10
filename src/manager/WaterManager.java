package manager;

import schedule.Weekday;

import java.io.*;
import java.util.HashMap;

public class WaterManager {
    // used to read from file
    private String fileName;
    private HashMap<Weekday,Integer> schedule;

    // constructor
    public WaterManager() {
        schedule = new HashMap<>();
        for(Weekday day : Weekday.values()){
        	schedule.put(day, 0);
        }
        fileName = "waterUsage.txt";
    }

    public void update(int timeActive, Weekday day, int regionRate) {
    	schedule.clear();
        for(Weekday days : Weekday.values()){
        	schedule.put(days, 0);
        }
    	readData();
    	System.out.println(schedule.get(day));
        writeData(timeActive,day,regionRate);
       
    }

    public HashMap<Weekday,Integer> getHash(){
    	return schedule;
    }
    
    private void readData() {
        BufferedReader reader;
        try {
            File inFile = new File(fileName);
            reader = new BufferedReader(new FileReader(inFile));

            // ... Loop as long as there are input lines.
            String line;

            try {
                while ((line = reader.readLine()) != null) {

                    // split each line into tokens
                    String[] fields = line.split(":");

                    // the String to int conversion happens here
                    String day = fields[0].trim();
                    int waterAmount = Integer.parseInt(fields[1].trim());

                    switch (day) {
                        case "Sunday":
                            schedule.put(Weekday.sunday,waterAmount);
                            break;
                        case "Monday":
                            schedule.put(Weekday.monday,waterAmount);
                            break;
                        case "Tuesday":
                            schedule.put(Weekday.tuesday,waterAmount);
                            break;
                        case "Wednesday":
                            schedule.put(Weekday.wednesday,waterAmount);
                            break;
                        case "Thursday":
                            schedule.put(Weekday.thursday,waterAmount);
                            break;
                        case "Friday":
                            schedule.put(Weekday.friday,waterAmount);
                            break;
                        case "Saturday":
                            schedule.put(Weekday.saturday,waterAmount);
                            break;
                    }
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: ");
            System.exit(1);
        }
    }

    private void writeData(int timeActive, Weekday currentDay, int regionRate) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);

            schedule.put(currentDay,timeActive*regionRate);

            // loop through days
            for (Weekday day : Weekday.values()) {
                switch (day) {
                    case sunday:
                        bw.write("Sunday:"+schedule.get(Weekday.sunday));
                        bw.newLine();
                        break;
                    case monday:
                        bw.write("Monday:"+schedule.get(Weekday.monday));
                        bw.newLine();
                        break;
                    case tuesday:
                        bw.write("Tuesday:"+schedule.get(Weekday.tuesday));
                        bw.newLine();
                        break;
                    case wednesday:
                        bw.write("Wednesday:"+schedule.get(Weekday.wednesday));
                        bw.newLine();
                        break;
                    case thursday:
                        bw.write("Thursday:"+schedule.get(Weekday.thursday));
                        bw.newLine();
                        break;
                    case friday:
                        bw.write("Friday:"+schedule.get(Weekday.friday));
                        bw.newLine();
                        break;
                    case saturday:
                        bw.write("Saturday:"+schedule.get(Weekday.saturday));
                        bw.newLine();
                        break;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}