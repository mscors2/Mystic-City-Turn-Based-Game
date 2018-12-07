import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JPanel target;
	private JTextArea output;
	private JTextField input;
    private JButton inputButton;
	private JButton inveButton;
    private JButton lookButton;
    private JButton attackButton;
    private JButton passButton; 
    
    private String userInput;
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Main Methods */
	public void display(String str)
	{
		if (myFrame == null || output == null)
			buildFrame();
		output.append("\n" + str);
	}
	
	public void display(Character c)
	{
		if (myFrame == null || output == null)
			buildFrame();
				
		c.display();
	}
	
	public String getLine()
	{
		if (myFrame == null || input == null)
			buildFrame();
		 
		//Turn on buttons and input text box
		enableInput();
		waitForInput();
		disableInput();
		
		// Return user's input and reset
		String userInputCopy = userInput;
		userInput = "";
		
		// Close if we have to
		String[] arr = userInputCopy.split("\\s+");
		if (arr[0].trim().equalsIgnoreCase("TEXT") || arr[0].trim().equalsIgnoreCase("GUI"))
			myFrame.dispose();
		
		return userInputCopy;
	}
	
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Support Methods (Private) */
	
	// Instantiate a new JFrame aka GUI Window
	@SuppressWarnings("deprecation")
	private void buildFrame()
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
        
        inputButton = new JButton("Act");
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
        
        // VERY IMPORTANT Event Handlers for the Buttons
        setupButtons();
        
        // Add a target visual
        target = new JPanel();
        target.setBounds(20, 20, 210, 200);
        try
        {
        	URL url = new URL("https://i.ytimg.com/vi/LVTWlwNNt4c/hqdefault.jpg");
        	Image image = ImageIO.read(url);
        	Image scaledImage = image.getScaledInstance(target.getWidth(), target.getHeight(), java.awt.Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));
        	target.add(label);
        } catch (MalformedURLException e) {} catch(IOException e) {}
        
        myFrame.add(target);        
        
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
	private void enableInput()
	{
		input.setEditable(true);
		input.setText("");
		inputButton.setEnabled(true);
		inveButton.setEnabled(true);
		lookButton.setEnabled(true);
		attackButton.setEnabled(true);
		passButton.setEnabled(true);
	}
	
	private void disableInput()
	{
		input.setEditable(false);
		inputButton.setEnabled(false);
		inveButton.setEnabled(false);
		lookButton.setEnabled(false);
		attackButton.setEnabled(false);
		passButton.setEnabled(false);
	}
	
	// Force the process to wait for the User to interact with the GUI
	private void waitForInput()
	{
		while (userInput == null || userInput.isEmpty())
		{
			// Wait for the user to input something
			try 
			{
				Thread.sleep(200);
			} catch(InterruptedException e) {}
		}
	}
	
	// Instructs the code for what each button will execute
	private void setupButtons()
	{
		inputButton.addActionListener(new ActionListener() 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    { 
		    	if (input.getText().isEmpty())
		    		userInput = "nothing";
		    	else
		    		userInput = input.getText();
		    	
		    	// Reset our target
				changeTarget("https://i.ytimg.com/vi/LVTWlwNNt4c/hqdefault.jpg");
	    	} 
		});
		
		inveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				changeTarget("https://media-waterdeep.cursecdn.com/avatars/thumbnails/211/743/315/315/636569073436710611.jpeg");
				userInput = "Inventory";
			}
		});
		
		lookButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				changeTarget("https://res.cloudinary.com/teepublic/image/private/s--5jhGuhy8--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1501711831/production/designs/1786687_1.jpg");
				userInput = "Look";
			}
		});
		
		attackButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				changeTarget("https://honeysanime.com/wp-content/uploads/2018/11/Goblin-Slayer-Wallpaper-506x500.jpg");
				userInput = "Attack";
			}
		});
		
		passButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				changeTarget("https://ak4.picdn.net/shutterstock/videos/31907524/thumb/3.jpg");
				userInput = "Pass";
			}
		});
		
		// This isn't a button but handles the ENTER key inside the input JTextField
		@SuppressWarnings("serial")
		Action action = new AbstractAction()
		{
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				if (input.getText().isEmpty())
					userInput = "nothing";
				else
					userInput = input.getText();
				
				// Reset our target
				changeTarget("https://i.ytimg.com/vi/LVTWlwNNt4c/hqdefault.jpg");
			}
		};
		input.addActionListener(action);
	}
	
	// Changes the picture-view-window target thing to whatever image URL you give it
	private void changeTarget(String imageURL)
	{
		// Reset our target
		target.removeAll();
		
		// Renew our target
        try
        {
        	URL url = new URL(imageURL);
        	Image image = ImageIO.read(url);
        	Image scaledImage = image.getScaledInstance(target.getWidth(), target.getHeight(), java.awt.Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));
        	target.add(label);
        } catch (MalformedURLException e) {} catch(IOException e) {}
		
        // Refresh
        myFrame.setVisible(true);
	}
}
