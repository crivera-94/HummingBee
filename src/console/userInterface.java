package console;

import main.*;
import schedule.Clock;

import schedule.Time;
import schedule.Weekday;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import location.Area;
import console.BarChart;

public class userInterface extends JFrame implements ActionListener {
	HummingBee hb;
	JButton systemPower, tempUp, tempDown, update, changeStatus;
	JPanel displayArea, settingArea, tempArea, sprinklerArea,graphArea;
	JPanel generalArea = new JPanel();
	JPanel timeArea = new JPanel();
	JLabel systemConLabel, tempConLabel, temperature, waterGraph;
	JLabel dispDay = new JLabel();
	JLabel dispHour = new JLabel();
	JLabel dispMin = new JLabel();
	JComboBox sprinklerList, dayList;
	JTextField startTime, stopTime, tempLow, tempHigh;
	// Declare the HummingBee instance so that it is global to the userInterface
	// class
	
	Time start;
	Time stop;
	Border raisedBevel, line;
	boolean systemStatus = true;
	Container container = getContentPane();

	// --------------------CLASS
	// CONSTRUCTOR----------------------------------------------------------------------------------
	public userInterface() {
		super("Control console");
		setSize(700, 500);
		systemStatus = true;

		container.setLayout(new BorderLayout());
		raisedBevel = BorderFactory.createRaisedBevelBorder();
		line = BorderFactory.createLineBorder(Color.black);

		// Create an instance of the HummingBee class
		hb = new HummingBee(this);

		// Call the functions that specify the Display, Settings and General
		// Area panels
		setDisplay();
		setSettings();
		setGeneral();

		// show the frame in the center of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	// End of constructor

	// ---------------------DISPLAY AREA
	// PANEL-------------------------------------------------------------------------------

	public final void setDisplay() {
		displayArea = new JPanel();
		container.add(displayArea, BorderLayout.CENTER);
		displayArea.setPreferredSize(new Dimension(500, 300));
		displayArea.setBorder(raisedBevel);
		displayArea.setLayout(new GridLayout(1,2));

		sprinklerArea = new JPanel();
		displayArea.add(sprinklerArea);
		sprinklerArea.setPreferredSize(new Dimension(240, 270));
		// sprinklerArea.setBorder(line);

		Image img = getImage();
		//img = img.getScaledInstance( 30, 15, java.awt.Image.SCALE_SMOOTH ) ;
		JLabel picLabel = new JLabel(new ImageIcon(img));
		sprinklerArea.add(picLabel);
		
		graphArea = new JPanel();
		displayArea.add(graphArea);
		displayArea.setPreferredSize(new Dimension(240, 270));
		BarChart chart = new BarChart(hb.getWaterMan().getHash());
		
		
		chart.setPreferredSize(new Dimension(200, 200));
		graphArea.add(chart, BorderLayout.CENTER);
		waterGraph = new JLabel("Water Usage");
		graphArea.add(waterGraph, BorderLayout.NORTH);
		//waterGraph.setAlignmentX(CENTER_ALIGNMENT);
		JPanel xlabels = new JPanel();
		xlabels.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 8));
		JLabel sunday = new JLabel("Sunday");
		sunday.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel monday = new JLabel("Monday");
		monday.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel tuesday = new JLabel("Tuesday");
		tuesday.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel wednesday = new JLabel("Wednesday");
		wednesday.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel thursday = new JLabel("Thursday");
		thursday.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel friday = new JLabel("Friday");
		friday.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel saturday = new JLabel("Saturday");
		saturday.setFont(new Font("Arial", Font.BOLD, 6));
		xlabels.add(sunday);
		xlabels.add(monday);
		xlabels.add(tuesday);
		xlabels.add(wednesday);
		xlabels.add(thursday);
		xlabels.add(friday);
		xlabels.add(saturday);
		graphArea.add(xlabels, BorderLayout.SOUTH);
		
		JPanel valLabels = new JPanel();
		valLabels.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 0));
		JLabel sun = new JLabel(Integer.toString(chart.array.get(0)));
		sun.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel mon = new JLabel(Integer.toString(chart.array.get(1)));
		mon.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel tues = new JLabel(Integer.toString(chart.array.get(2)));
		tues.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel wednes = new JLabel(Integer.toString(chart.array.get(3)));
		wednes.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel thurs = new JLabel(Integer.toString(chart.array.get(4)));
		thurs.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel fri = new JLabel(Integer.toString(chart.array.get(5)));
		fri.setFont(new Font("Arial", Font.BOLD, 6));
		JLabel satur = new JLabel(Integer.toString(chart.array.get(6)));
		satur.setFont(new Font("Arial", Font.BOLD, 6));
		valLabels.add(sun);
		valLabels.add(mon);
		valLabels.add(tues);
		valLabels.add(wednes);
		valLabels.add(thurs);
		valLabels.add(fri);
		valLabels.add(satur);
		graphArea.add(valLabels, BorderLayout.AFTER_LAST_LINE);

	}
	

	public Image getImage() {
		System.out.println((hb.checkStatus(Area.north)));
		System.out.println((hb.checkStatus(Area.south)));
		System.out.println((hb.checkStatus(Area.east)));
		System.out.println((hb.checkStatus(Area.west)));

		if ((hb.checkStatus(Area.north)) && (hb.checkStatus(Area.south)) && (hb.checkStatus(Area.east))
				&& (hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("allOn.jpg"));
			} catch (IOException ex) {
			}
		} else if (!(hb.checkStatus(Area.north)) && !(hb.checkStatus(Area.south)) && !(hb.checkStatus(Area.east))
				&& !(hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("allOff.jpg"));
			} catch (IOException ex) {
			}
		} else if ((hb.checkStatus(Area.north)) && (hb.checkStatus(Area.south)) && !(hb.checkStatus(Area.east))
				&& (hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("eastOff.jpg"));
			} catch (IOException ex) {
			}
		} else if (!(hb.checkStatus(Area.north)) && !(hb.checkStatus(Area.south)) && (hb.checkStatus(Area.east))
				&& !(hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("eastOn.jpg"));
			} catch (IOException ex) {
			}
		} else if ((hb.checkStatus(Area.north)) && !(hb.checkStatus(Area.south)) && (hb.checkStatus(Area.east))
				&& !(hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("northEastOn.jpg"));
			} catch (IOException ex) {
			}
		} else if (!(hb.checkStatus(Area.north)) && (hb.checkStatus(Area.south)) && (hb.checkStatus(Area.east))
				&& (hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("northOff.jpg"));
			} catch (IOException ex) {
			}
		} else if ((hb.checkStatus(Area.north)) && !(hb.checkStatus(Area.south)) && !(hb.checkStatus(Area.east))
				&& !(hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("northOn.jpg"));
			} catch (IOException ex) {
			}
		} else if ((hb.checkStatus(Area.north)) && !(hb.checkStatus(Area.south)) && !(hb.checkStatus(Area.east))
				&& (hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("northWestOn.jpg"));
			} catch (IOException ex) {
			}
		} else if (!(hb.checkStatus(Area.north)) && (hb.checkStatus(Area.south)) && (hb.checkStatus(Area.east))
				&& !(hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("southEastOn.jpg"));
			} catch (IOException ex) {
			}
		} else if ((hb.checkStatus(Area.north)) && !(hb.checkStatus(Area.south)) && (hb.checkStatus(Area.east))
				&& (hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("southOff.jpg"));
			} catch (IOException ex) {
			}
		} else if (!(hb.checkStatus(Area.north)) && (hb.checkStatus(Area.south)) && !(hb.checkStatus(Area.east))
				&& !(hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("southOn.jpg"));
			} catch (IOException ex) {
			}
		} else if (!(hb.checkStatus(Area.north)) && (hb.checkStatus(Area.south)) && !(hb.checkStatus(Area.east))
				&& (hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("southWestOn.jpg"));
			} catch (IOException ex) {
			}
		} else if (!(hb.checkStatus(Area.north)) && !(hb.checkStatus(Area.south)) && (hb.checkStatus(Area.east))
				&& (hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("splitOn.jpg"));
			} catch (IOException ex) {
			}
		} else if ((hb.checkStatus(Area.north)) && (hb.checkStatus(Area.south)) && !(hb.checkStatus(Area.east))
				&& !(hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("splitOn1.jpg"));
			} catch (IOException ex) {
			}
		} else if ((hb.checkStatus(Area.north)) && (hb.checkStatus(Area.south)) && (hb.checkStatus(Area.east))
				&& !(hb.checkStatus(Area.west))) {
			try {
				return ImageIO.read(getClass().getResource("westOff.jpg"));
			} catch (IOException ex) {
			}
		} else {
			try {
				return ImageIO.read(getClass().getResource("westOn.jpg"));
			} catch (IOException ex) {
			}
		}
		return null;
	}


	// ---------------------SETTINGS AREA
	// PANEL------------------------------------------------------------------------------

	public final void setSettings() {

		settingArea = new JPanel();
		container.add(settingArea, BorderLayout.SOUTH);
		settingArea.setPreferredSize(new Dimension(700, 200));
		settingArea.setBorder(raisedBevel);
		settingArea.setLayout(new GridLayout(3, 2, 25, 25));

		String[] dayStrings = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
		String[] sprinklerStrings = { "north", "south", "east", "west" };
		sprinklerList = new JComboBox(sprinklerStrings);
		settingArea.add(sprinklerList);
		dayList = new JComboBox(dayStrings);
		settingArea.add(dayList);

		changeStatus = new JButton("ENABLE");
		settingArea.add(changeStatus);
		changeStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// access back end to shut down or start up entire system

				// Change text on button
				if ((hb.checkStatus(areaConverter((String)sprinklerList.getSelectedItem()))) == true) {
					changeStatus.setText("ENABLE");
					hb.disableRegion(areaConverter((String)sprinklerList.getSelectedItem()));	
				} else {
					changeStatus.setText("DISABLE");
					hb.enableRegion(areaConverter((String)sprinklerList.getSelectedItem()));
				}
			}
		});

		// Adding the startTime text field, FocusListener makes the prompting
		// text disappear/reappear based on where
		// user is focused

		startTime = new JTextField("Enter Start Time");
		settingArea.add(startTime);
		startTime.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				startTime.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (startTime.getText() == "") {
					startTime.setText("Enter Start Time");
				}
			}
		});

		// Adding the stopTime text field, FocusListener makes the prompting
		// text disappear/reappear based on where
		// user is focused
		stopTime = new JTextField("Enter Stop Time");
		settingArea.add(stopTime);
		stopTime.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				stopTime.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (stopTime.getText() == "") {
					stopTime.setText("Enter Stop Time");
				}
			}
		});

		tempHigh = new JTextField("Enter Upper Temp Limit");
		tempLow = new JTextField("Enter Lower Temp Limit");
				
		update = new JButton("ENTER");
		settingArea.add(update);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Weekday selectedDay = dayConverter((String) dayList.getSelectedItem());
				Area selectedArea = areaConverter((String) sprinklerList.getSelectedItem());
				start = timeConverter(startTime.getText());
				stop = timeConverter(stopTime.getText());
				hb.addOperatingTime(selectedArea, selectedDay, start, stop);
				hb.setTemperatureRange(selectedArea, Integer.parseInt(tempLow.getText()), Integer.parseInt(tempHigh.getText()));
			}
		});


		
		// Adding the tempLow text field, FocusListener makes the prompting text
		// disappear/reappear based on where
		// user is focused
		
		settingArea.add(tempLow);
		tempLow.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				tempLow.setText("");
			}
			public void focusLost(FocusEvent e) {
				if (tempLow.getText() == "") {
					tempLow.setText("Enter Lower Temp Limit");
				}
			}
		});

		// Adding the tempHigh text field, FocusListener makes the prompting
		// text disappear/reappear based on where
		// user is focused
	
		settingArea.add(tempHigh);
		tempHigh.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				tempHigh.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (tempHigh.getText() == "") {
					tempHigh.setText("Enter Upper Temp Limit");
				}
			}
		});
	}

	public Weekday dayConverter(String input) {
		return Weekday.stringToWeekday(input);
	}

	public Area areaConverter(String input) {
		return Area.stringToArea(input);
	}

	public Time timeConverter(String input) {
		Time t = new Time();
		String[] tokens = input.split(":");
		int hours = Integer.parseInt(tokens[0]);
		int minutes = Integer.parseInt(tokens[1]);
		t.setHours(hours);
		t.setMinutes(minutes);
		return t;
	}

	// ---------------------GENERAL AREA
	// PANEL------------------------------------------------------------------------------

		
	public final void setGeneral() {
		
		container.add(generalArea, BorderLayout.EAST);
		generalArea.setPreferredSize(new Dimension(200, 500));
		generalArea.setBorder(raisedBevel);
		generalArea.setLayout(new BoxLayout(generalArea, BoxLayout.Y_AXIS));

		generalArea.add(Box.createVerticalStrut(20));
		generalArea.add(timeArea);

		timeArea.setLayout(new FlowLayout());
		timeArea.add(dispDay);
		timeArea.add(dispHour);
		timeArea.add(new JLabel(":"));
		timeArea.add(dispMin);
		dispDay.setFont(new Font("Arial", Font.BOLD, 16));
		dispHour.setFont(new Font("Arial", Font.BOLD, 16));
		dispMin.setFont(new Font("Arial", Font.BOLD, 16));
		

		generalArea.add(Box.createVerticalStrut(20));
		systemConLabel = new JLabel("System Control");
		systemConLabel.setFont(new Font("Arial", Font.BOLD, 16));
		generalArea.add(systemConLabel);
		systemConLabel.setAlignmentX(CENTER_ALIGNMENT);

		// System Status Button-----------------------------------------------
		systemPower = new JButton("OFF");
		generalArea.add(systemPower);
		systemPower.setAlignmentX(CENTER_ALIGNMENT);
		systemPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// access back end to shut down or start up entire system

				// Change text on button
				if (systemStatus == true) {
					systemPower.setText("ON");
					systemStatus = false;
					hb.disableAll();
				} else {
					systemPower.setText("OFF");
					systemStatus = true;
					hb.enableAll();
				}
			}
		});

		generalArea.add(Box.createVerticalStrut(20));
		tempConLabel = new JLabel("Temperature Control");
		tempConLabel.setFont(new Font("Arial", Font.BOLD, 16));
		generalArea.add(tempConLabel);
		tempConLabel.setAlignmentX(CENTER_ALIGNMENT);

		generalArea.add(Box.createVerticalStrut(20));
		JPanel tempDisplay = new JPanel();
		tempDisplay.setLayout(new FlowLayout());
		generalArea.add(tempDisplay);
		temperature = new JLabel("70");
		tempDisplay.add(temperature);
		JLabel deg = new JLabel("Degrees (F)");
		tempDisplay.add(deg);

		tempArea = new JPanel();
		generalArea.add(tempArea);
		tempArea.setLayout(new FlowLayout());

		// Temperature UP
		// Button--------------------------------------------------
		tempUp = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("up_arrow.png"));
			img = img.getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH);
			tempUp.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		tempArea.add(tempUp);
		tempUp.setAlignmentX(CENTER_ALIGNMENT);
		tempUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Increase temperature in backend
				temperature.setText(Integer.toString(hb.incrementTemp()));
			}
		});

		// Temperature DOWN
		// Button--------------------------------------------------
		tempDown = new JButton();
		try {
			Image img = ImageIO.read(getClass().getResource("down_arrow.png"));
			img = img.getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH);
			tempDown.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		tempArea.add(tempDown);
		tempDown.setAlignmentX(CENTER_ALIGNMENT);
		tempDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Decrease temperature in backend
				temperature.setText(Integer.toString(hb.decrementTemp()));
			}
		});
	}

	public void setDisplayClock(){
		dispDay.setText(hb.clock.getCurrentDay().toString());
		dispHour.setText(Integer.toString(hb.clock.getHour()));
		dispMin.setText(String.format("%02d", (hb.clock.getMinutes())));
	}
	/*
	 * public void updateVisualTime(){ timeArea.setLayout(new FlowLayout());
	 * JLabel hours = new JLabel(Integer.toString(hb.getHour()));
	 * timeArea.add(hours); JLabel minutes = new
	 * JLabel(Integer.toString(hb.getMinutes())); timeArea.add(minutes); }
	 */

	// public void setSprinkler(String[] days, String[] sprink, JComboBox
	// sprinkPick, JComboBox dayPick, JTextField start, JTextField stop,
	// JTextField temp){
	public void setSprinkler() {
		sprinklerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * JComboBox<String> source = (JComboBox<String>) e.getSource();
				 * sprinklerList = source; Weekday day = (Weekday)
				 * sprinklerList.getSelectedItem();
				 */
				// sprinkler
				// addOperatingTime(day, Time startTime, Time endTime);
			}
		});
	}

	public static void main(String[] args) {
		new userInterface();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
