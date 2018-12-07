/*
 * Michael Scorsolini
 * NetID: mscors2
 * Class: 	CS 342 (Software Design)
 * Project: Term Project Part V
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

public class GUI_3 implements UserInterface
{
	private JFrame frame;
	private JScrollPane scroll;
	private JTextArea tout;
	
	public GUI_3()
	{
		//set up GUI frame and size
		frame = new JFrame("Michael's GUI. Have fun!");
		frame.setSize(600,600);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		
		//Set up Text Area for output
		tout = new JTextArea();
		tout.setEditable(false);
		
		//Set the text area up for scrolling
		scroll = new JScrollPane(tout);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(250, 5, 300, 300);
		frame.add(scroll);
		
		frame.setVisible(true);
		
	}
	
	
	public void display(String str)
	{
		
	}
	
	@Override
	public void display(Character c)
	{
		
		tout.append("*The '" + c.name + "' hero! (#" + String.format("%03d", c.ID) + ")* \n"
				+ c.description);
		
		tout.append("Current Location: " + c.current + "\n\n" + c.current.description);
		
		tout.append("\nCurrent Health: " + c.health + "/" + c.healthCap);
		tout.append("\nCurrent Attack Damage: " + c.AttackDamage);
		tout.append("\nCurrent Weight Held: " + c.currCap + "/" + c.CarryCap);
		
		
		tout.append("\n"
				+ "Players alive in game: " + Game.nPlayersAlive);
		
		if(c.current.allCharacters.size() > 1)
		{
			tout.append("\nOther characters in the room with you: ");
			for(Character a: c.current.allCharacters)
			{
				if(c.name.equals(c.name()))
				{
					//do nothing
				}
				else
					tout.append(c.name());
			}
			tout.append("\n");
		}
		
		
	}
	
	public String getLine()
	{
		// TODO
		String input = JOptionPane.showInputDialog(null, "Input", "Please type a Command", 0);
		return input;	
	}
}
