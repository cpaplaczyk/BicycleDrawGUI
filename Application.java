package Application;


//  Created by Clayton Paplaczyk
//  Description: The Application class creates a control panel and
//               adds it as its Applet content and also sets its size.

import javax.swing.*;

public class Application extends JApplet
{
	private final int WIDTH = 650;
	private final int HEIGHT = 340;

	public void init()
	{
		ControlPanel panel = new ControlPanel(WIDTH,HEIGHT);
		getContentPane().add(panel);
		setSize(WIDTH,HEIGHT);
	}
}

