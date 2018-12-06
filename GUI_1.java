import javax.swing.*;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * Project: Term Project Part V
 */

public class GUI_1 implements UserInterface
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Fields */
	private JFrame myFrame;
	private JTextArea output;
	private JTextField input;
    private JButton inputButton;
	private JButton inveButton;
    private JButton lookButton;
    private JButton attackButton;
    private JButton passButton; 
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Methods */
	public void display(String str)
	{
		// Check if our window is already up
		if (myFrame == null || output == null)
			buildFrame();
		
		// Add our text to the output window (JTextArea)
		output.append(str);
	}
	
	public void display(Character c)
	{
		// Check if our window is already up
		if (myFrame == null || output == null)
			buildFrame();
		
		// Character class will handle its own display
		c.display();
	}
	
	public String getLine()
	{
		// Check if our window is already up
		if (myFrame == null || input == null)
			buildFrame();
		 
		//Turn on buttons and input text box
		enableInput();
		String result = "";
		disableInput();
		
		// Return user's input
		return result;
	}
	
	
	// Instantiate a new JFrame aka GUI Window
	@SuppressWarnings("deprecation")
	public void buildFrame()
	{
		// Init window's display properties
		myFrame = new JFrame("Gaming Visual Apparatus (GUI_1)");

		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(650, 400);
		myFrame.setResizable(false);
		myFrame.setLayout(null);
		myFrame.setLocationRelativeTo(null);
		
		// Add output window (Read Only JTextArea)
		output = new JTextArea();
		output.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(250, 25, 375, 300);
        myFrame.add(scrollPane);
        
        // Add the input components
        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setSize(inputLabel.getPreferredSize());
        inputLabel.move(250, 335);
        
        input = new JTextField();
        input.setEditable(false);
        input.setBounds(290, 335, 275, 20);
        
        JButton inputButton = new JButton("Act");
        inputButton.setBounds(570, 335, 55, 20);
        inputButton.setEnabled(false);
        try
        {
        	URL url = new URL("https://i.ytimg.com/vi/p_y_PGadK0s/maxresdefault.jpg");
        	Image icon = ImageIO.read(url);
        	Image scaledIcon = icon.getScaledInstance(inputButton.getWidth(), inputButton.getHeight(), java.awt.Image.SCALE_SMOOTH);
        	inputButton.setIcon(new ImageIcon(scaledIcon));
        } catch (MalformedURLException e) {} catch(IOException e) {}
        
        myFrame.add(inputLabel);
        myFrame.add(input);
        myFrame.add(inputButton);
        
        // Add Controls (INVE, LOOK, ATTACK, PASS)
        inveButton = new JButton("Bag");
        lookButton = new JButton("Look");
        attackButton = new JButton("Attack!");
        passButton = new JButton("Skip");
        
        inveButton.setBounds(75, 240, 100, 30);
        lookButton.setBounds(20, 275, 100, 30);
        attackButton.setBounds(130, 275, 100, 30);
        passButton.setBounds(75, 310, 100, 30);
        
        inveButton.setEnabled(false);
        lookButton.setEnabled(false);
        attackButton.setEnabled(false);
        passButton.setEnabled(false);
        
        myFrame.add(inveButton);
        myFrame.add(lookButton);
        myFrame.add(attackButton);
        myFrame.add(passButton);
        
        // Add a target visual
        JPanel panel = new JPanel();
        panel.setBounds(20, 20, 210, 200);
        try
        {
        	URL url = new URL("https://i.ytimg.com/vi/LVTWlwNNt4c/hqdefault.jpg");
        	Image image = ImageIO.read(url);
        	Image scaledImage = image.getScaledInstance(panel.getWidth(), panel.getHeight(), java.awt.Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));
        	panel.add(label);
        } catch (MalformedURLException e) {} catch(IOException e) {}
        
        myFrame.add(panel);        
        
		// Change iconImage (Zork style because that's the theme of this program)
		try 
		{
			URL url = new URL("https://www.arbelindustries.com/wp-content/uploads/2016/12/zork-uo-512.jpg");
			Image icon = ImageIO.read(url);
			myFrame.setIconImage(new ImageIcon(icon).getImage());
		} catch (MalformedURLException e) {} catch(IOException e) {}
		
		// Make display visible to the user
		myFrame.setVisible(true);
	}
	
	// This turns on our controls and input text box for the user to type in commands
	public void enableInput()
	{
		input.setEditable(true);
		inputButton.setEnabled(true);
		inveButton.setEnabled(true);
		lookButton.setEnabled(true);
		attackButton.setEnabled(true);
		passButton.setEnabled(true);
	}
	
	public void disableInput()
	{
		input.setEditable(false);
		inputButton.setEnabled(false);
		inveButton.setEnabled(false);
		lookButton.setEnabled(false);
		attackButton.setEnabled(false);
		passButton.setEnabled(false);
	}
}
