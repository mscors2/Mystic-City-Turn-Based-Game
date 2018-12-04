/*
 * TODO: Vanson Ho
 */
import javax.swing.*;
import java.util.*;

public class GUI_2 implements UserInterface 
{
	public void display(String str)
	{
		JFrame frame = new JFrame("GUI_FRAME");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel(str);
		frame.getContentPane().add(label);

		frame.pack();
		frame.setVisible(true);
	}
	
	public String getLine()
	{
		String input = JOptionPane.showInputDialog(null, "Input", "Please type a String", 0);
		return input;
	}
	
}