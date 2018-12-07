/*
 * Michael Scorsolini
 * NetID: mscors2
 * Class: 	CS 342 (Software Design)
 * Project: Term Project Part V
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
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
	private JButton iButton;
	private JButton lButton;
	private JButton aButton;
	private JButton pButton;
	private JButton eButton;
	private JTextArea note;
	private JTextArea note2;
	private JTextArea inputNote;
	private JTextArea inputText;
	
	private String Input;
	
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
		tout.setBackground(Color.BLACK);
		tout.setForeground(Color.CYAN);
		
		//Set up note text Area
		note = new JTextArea();
		note.setEditable(false);
		note.setBackground(Color.BLACK);
		note.setForeground(Color.BLUE);
		
		note2 = new JTextArea();
		note2.setEditable(false);
		note2.setBackground(Color.BLACK);
		note2.setForeground(Color.MAGENTA);
		
		inputNote = new JTextArea();
		inputNote.setEditable(false);
		inputNote.setBackground(Color.BLACK);
		inputNote.setForeground(Color.RED);
		
		note.setBounds(175, 425, 250, 20);
		note2.setBounds(175, 445, 300, 20);
		inputNote.setBounds(175, 480, 100, 20);
		
		note.setText("*Blue Buttons Uses A Turn");
		note2.setText("*Magenta Buttons Do Not Use A Turn");
		inputNote.setText("Command Line:");
		
		//add notes
		frame.add(note);
		frame.add(note2);
		frame.add(inputNote);
		
		//set up input text area
		inputText = new JTextArea();
		inputText.setEditable(false);
		inputText.setBackground(Color.RED);
		inputText.setForeground(Color.BLACK);
		inputText.setBounds(175, 505, 200, 20);
		frame.add(inputText);
		
		//Set the text area up for scrolling
		scroll = new JScrollPane(tout);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(50, 5, 500, 400);
		scroll.setBackground(Color.black);
		scroll.setForeground(Color.CYAN);
		frame.add(scroll);
		
		// Add Controls (INVE, LOOK, ATTACK, PASS)
        iButton = new JButton("Check Bag");
        lButton = new JButton("Check Map");
        aButton = new JButton("Attack!");
        pButton = new JButton("Wait Turn");
        eButton = new JButton("EXIT GAME");
        
        eButton.setBackground(Color.RED);
        //set locations of buttons
        iButton.setBounds(50, 425, 100, 30);
        lButton.setBounds(50, 460, 100, 30);
        aButton.setBounds(50, 495, 100, 30);
        pButton.setBounds(50, 530, 100, 30);
        eButton.setBounds(475, 475, 100, 100);
        
        
        //colors
        iButton.setForeground(Color.MAGENTA);
        lButton.setForeground(Color.MAGENTA);
        aButton.setForeground(Color.BLUE);
        pButton.setForeground(Color.BLUE);
        eButton.setBackground(Color.RED);
        
        //add to frame
        frame.add(iButton);
        frame.add(lButton);
        frame.add(aButton);
        frame.add(pButton);
        frame.add(eButton);
        
        setEventListeners();
        
		frame.setVisible(true);
		
	}
	
	public void setEventListeners()
	{
		iButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Input = "Inventory";
			}
		});
		
		aButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Input = "Attack";
			}
		});
		
		pButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Input = "Pass";
			}
		});
		
		eButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Input = "Exit";
			}
		});
		
		lButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Input = "";
			}
		});
		
		// This isn't a button but handles the ENTER key inside the input JTextField
		@SuppressWarnings("serial")
		Action action = new AbstractAction()
		{
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				if (inputText.getText().isEmpty())
					Input = "nothing";
				else
					Input = inputText.getText();
			}
		};
		inputText.addActionListener(action);
	}
	
	
	public void display(String str)
	{
		tout.append("\n" + str);
	}
	
	@Override
	public void display(Character c)
	{
		
		tout.append("\n\n*The '" + c.name + " hero! \n"
				+ c.description);
		
		tout.append("\nCurrent Location: " + c.current.name + "\n\n" + c.current.description);
		
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
		
		String[] arr = input.split("\\s+");
		if (arr[0].trim().equalsIgnoreCase("TEXT") || arr[0].trim().equalsIgnoreCase("GUI"))
			frame.dispose();
		
		return input;	
	}
}
