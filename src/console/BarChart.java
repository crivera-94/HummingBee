package console;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import schedule.Weekday;

class BarChart extends JPanel {
	ArrayList<Integer> array = new ArrayList<Integer>(7);
	//private Map<Color, String> labels = new LinkedHashMap<Color, String>();

	public BarChart(HashMap<Weekday,Integer> hash) {

		for( Weekday day : Weekday.values()){
			array.add(hash.get(day));
		}

	}

	@Override
	protected void paintComponent(Graphics gp) {
		super.paintComponent(gp);
		// Cast the graphics objects to Graphics2D
		Graphics2D g = (Graphics2D) gp;
		// determine longest bar
		int max = Integer.MIN_VALUE;
		for (Integer value : array) {
			max = Math.max(max, value)+1;
		}
		// paint bars

		int width = (getWidth() / array.size()) - 2;
		int x = 1;
		for (Integer value : array) {
			//String days = labels.get(day);
			int height = (int) ((getHeight() - 5) * ((double) value / max));
			g.setColor(Color.BLUE);
			g.fillRect(x, getHeight() - height, width, height);
			g.setColor(Color.black);
			g.drawRect(x, getHeight() - height, width, height);
			x += (width + 2);
			//g.drawString(, x-width/2, max-80);
		}
	}

}
