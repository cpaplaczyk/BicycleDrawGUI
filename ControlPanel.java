package Application;

//  Created by Clayton Paplaczyk	
//  Description: The ControlPanel is a subclass of JPanel class.
//	It contains 6 buttons including two start buttons, two stop buttons,
//	and two reverse buttons. It also contains two labels and two JSlider
//	objects. It also contains two panels -- two objects of BicyclePanel
//	class, one for the red bicycle and one for the blue bicycle.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel
{
	//1 for the red bicycle control, 2 for the blue bicycle control
	private BicyclePanel bicycle1, bicycle2;
	private JLabel fanLabel1, fanLabel2;
	private JButton start1, start2, stop1, stop2, reverse1, reverse2;
	private JSlider slide1, slide2;
	private JPanel leftPanel, rightPanel, topPanelLeft, leftTopPanel, topPanelRight, leftBottomPanel, bottomPanelLeft, bottomPanelRight;
	private int width, height;

	//The constructor creates 6 buttons, 2 sliders, and 2 bicycle panels
	//and organize them using layouts.
	public ControlPanel(int width, int height)
	{
		this.width = width;
		this.height = height;

		//create 2 bicycle panels and arrange them using GridLayout
		bicycle1 = new BicyclePanel(Color.red, Color.cyan, width/2);
		bicycle2 = new BicyclePanel(Color.blue, Color.yellow, width/2);

		//creation of rightPanel
		rightPanel = new JPanel();
		//setting layout for rightPanel
		rightPanel.setLayout(new GridLayout(2,1));
		//adding bicycle1 and 2 to rightPanel
		rightPanel.add(bicycle1);
		rightPanel.add(bicycle2);

		//creating JButtons
		start1 = new JButton("Start Red");
		start1.addActionListener(new ButtonListener());
		start2 = new JButton("Start Blue");
		start2.addActionListener(new ButtonListener());
		stop1 = new JButton("Stop Red");
		stop1.addActionListener(new ButtonListener());
		stop2 = new JButton("Stop Blue");
		stop2.addActionListener(new ButtonListener());
		reverse1 = new JButton("Reverse Red");
		reverse1.addActionListener(new ButtonListener());
		reverse2 = new JButton("Reverse Blue");
		reverse2.addActionListener(new ButtonListener());

		//creating JLabels
		fanLabel1 = new JLabel("Red Fan Delay", JLabel.CENTER);
		fanLabel2 = new JLabel("Blue Fan Delay", JLabel.CENTER);
		//blank = new JLabel("");

		//creating JSliders
		slide1 = new JSlider(JSlider.VERTICAL, 0, 50, 20);
		slide2 = new JSlider(JSlider.VERTICAL, 0, 50, 20);
		//Turn on labels at major tick marks for slide1
		slide1.setMajorTickSpacing(10);
		slide1.setMinorTickSpacing(2);
		slide1.setPaintTicks(true);
		slide1.setPaintLabels(true);
		//Turn on labels at major tick marks for slide2
		slide2.setMajorTickSpacing(10);
		slide2.setMinorTickSpacing(2);
		slide2.setPaintTicks(true);
		slide2.setPaintLabels(true);
		//creating listener for JSliders
		slide1.addChangeListener(new SliderListener());
		slide2.addChangeListener(new SliderListener());

		//creation of leftPanel
		leftPanel = new JPanel();
		//setting layout for leftPanel
		leftPanel.setLayout(new GridLayout(2,1));
		
		//creating layouts for JPanels
        topPanelLeft = new JPanel();
        topPanelLeft.setLayout(new GridLayout(3,1));
        topPanelRight = new JPanel();
        topPanelRight.setLayout(new BorderLayout());
        bottomPanelLeft = new JPanel();
        bottomPanelLeft.setLayout(new GridLayout(3,1));
        bottomPanelRight = new JPanel();
        bottomPanelRight.setLayout(new BorderLayout());


		leftTopPanel = new JPanel();
		leftTopPanel.setLayout(new GridLayout(1,2));
		
		//adding JButtons, JSliders and JLebels to JPanels
		leftTopPanel = new JPanel();
		leftTopPanel.setLayout(new GridLayout(1,2));
		topPanelLeft.add(start1);
		topPanelLeft.add(stop1);
		topPanelLeft.add(reverse1);
		topPanelRight.add(fanLabel1, BorderLayout.NORTH);
		topPanelRight.add(slide1, BorderLayout.WEST);
		leftTopPanel.add(topPanelLeft);
		leftTopPanel.add(topPanelRight);
		leftBottomPanel = new JPanel();
		leftBottomPanel.setLayout(new GridLayout(1,2));
		bottomPanelLeft.add(start2);
		bottomPanelLeft.add(stop2);
		bottomPanelLeft.add(reverse2);
		bottomPanelRight.add(fanLabel2, BorderLayout.NORTH);
		bottomPanelRight.add(slide2, BorderLayout.WEST);
		leftBottomPanel.add(bottomPanelLeft);
		leftBottomPanel.add(bottomPanelRight);
		leftPanel.add(leftTopPanel);
		leftPanel.add(leftBottomPanel);

		//organize the left panel and right panel using SplitPane
		setLayout(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(width, 120));
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		add(sp);

		setPreferredSize(new Dimension(width,height));
	}

	//When a button is clicked ActionListener is implemented
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//Start movement for Red bike
			if(event.getSource() == start1){
				bicycle1.resume();
			}

			//Start movement for Blue bike
			if(event.getSource() == start2){
				bicycle2.resume();
			}

			//Stop movement for Red bike
			if(event.getSource() == stop1){
				bicycle1.suspend();
			}

			//Stop movement for Blue bike
			if(event.getSource() == stop2){
				bicycle2.suspend();
			}

			//Reverse movement for Red bike
			if(event.getSource() == reverse1){
				bicycle1.reverse();
			}

			//Reverse movement for Blue bike
			if(event.getSource() == reverse2){
				bicycle2.reverse();
			}
		}
	} //end of ButtonListener

	private class SliderListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent event)
		{
			//JSlider for Red bike
			if(event.getSource() == slide1){
				bicycle1.setDelay(slide1.getValue());
			}

			//JSlider for Blue bike
			if(event.getSource() == slide2){
				bicycle2.setDelay(slide2.getValue());
			}
		}

	} //end of SliderListener

} //end of ControlPanel
