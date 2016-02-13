package Application;

//Created by Clayton Paplaczyk
//  Description: The BicyclePanel class draws a bicycle in a panel
//               and moves it using javax.swing.Timer

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;

public class BicyclePanel extends JPanel
{
	private int width;
	private int centerX, centerY, diameter; //parameter to draw a bicycle
	private Color bicycleColor, background; //color of the bicycle and background color
	private int currentAngle; //the current starting angle of arcs of a wheel of the bicycle.
	private int step; //how much currentAngle changes for each tick of Timer
	private Timer timer; //This is used to control the bicycle's movement.
	private int delay;   //delay of Timer

	//The constructor initializes parameters for the bicycle, and start the timer
	public BicyclePanel(Color bicycleColor, Color backColor, int width)
	{
		this.bicycleColor = bicycleColor;
		this.width = width;
		delay = 20;
		step = 3;
		this.background = backColor;
		currentAngle = 0;
		centerX = (width/4);
		centerY = (width/4);
		diameter = (width-10)/6;
		timer = new Timer(delay, new MoveListener());
		timer.start();
	}

	//The timer should stop using its stop method. 
	public void resume(){
		timer.start();
	}

	//The timer should stop using its stop method. 
	public void suspend(){
		timer.stop();
	}

	//The direction of movement of the bicycle should be reversed. If the bicycle is
	//not moving when this method is called, it should start moving the bicycle. 
	public void reverse(){
		
		//if the bike is already moving it will reverse
		if(step>0)
			step=-3;
		else
			step=3;
		
		//if the bike is already stopped it starts again
		if(timer.isRunning() != true)
			timer.start();
	}

	//This method sets the delay of the timer using its parameter. 
	public void setDelay(int delayValue){
		timer.setDelay(delayValue);
	}

	//paintComponent method draws a bicycle by drawing two wheels and links between them
	//This method is given to you, and you do not need to modify it
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);

		//sets canvas background color to white
		setBackground(background); 

		//left wheel
		page.setColor(bicycleColor);
		page.fillArc(centerX, centerY, diameter, diameter, currentAngle, 90);
		page.fillArc(centerX, centerY, diameter, diameter, currentAngle+180, 90);

		page.setColor(Color.black);
		page.drawOval(centerX, centerY, diameter, diameter);


		//right wheel
		page.setColor(bicycleColor);
		page.fillArc(centerX+2*diameter, centerY, diameter, diameter, currentAngle, 90);
		page.fillArc(centerX+2*diameter, centerY, diameter, diameter, currentAngle+180, 90);

		page.setColor(Color.black);
		page.drawOval(centerX+2*diameter, centerY, diameter, diameter);


		//link two wheels
		page.setColor(bicycleColor);
		int seatLeftX = centerX+diameter-10;
		int seatLeftY = centerY-20;
		int seatRightX = centerX+2*diameter+10;
		int seatRightY = centerY-20;

		page.drawLine(seatLeftX, seatLeftY, seatRightX, seatRightY);

		page.drawLine(seatLeftX, seatLeftY, centerX+diameter/2, centerY+diameter/2);
		page.drawLine(seatRightX, seatRightY, centerX+(diameter*5)/2, centerY+diameter/2);

		page.drawLine(centerX+(diameter*3)/2, centerY+diameter/2, seatLeftX-10, seatLeftY-10);
		page.drawLine(centerX+(diameter*3)/2, centerY+diameter/2, seatRightX+10, seatRightY-10);

		page.drawLine(seatLeftX-20, seatLeftY-10, seatLeftX-10, seatLeftY-10);
		page.drawLine(seatRightX+20, seatRightY-10, seatRightX+10, seatRightY-10);

	}

	//MoveListener defined an action to be taken for each tick of the Timer.
	private class MoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			currentAngle -= step;
			
			if((centerX + step) > 0 && (centerX + step) < getSize().getWidth()-3*diameter)
				centerX += step;
			else
				reverse();
			
			//repaints each time a movement should happen
			repaint();
		}
	}
}

