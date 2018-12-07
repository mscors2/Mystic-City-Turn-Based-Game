/*
 * TODO: Vanson Ho
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class GUI_2 implements UserInterface
{
	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	private JButton inputButton;


	private JTextArea playerArea;
	private JTextArea hpArea;
	private JTextArea attackArea;
	private JTextArea placeArea;
	private JTextArea inputArea;
	private String input;

	private boolean dispose;
	GUI_2()
	{

		if(frame != null)
		{
			return;
		}

		dispose = false;
		//initializing input
		input = "";

		//frame setup
		frame = new JFrame("MY_FRAME");
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,500);
		frame.setTitle("The Best Game Ever");
		frame.setResizable(false);

		try {
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("dungeon.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//font setup
		Font myFont = new Font("Algerian", Font.PLAIN, 12);

		//text area setup
		textArea = new JTextArea(20,25);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);


		//scrollpane setup
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(50, 25, 425, 300);

		frame.add(scrollPane);

		//player area setup
		playerArea = new JTextArea(1, 30);
		playerArea.setBounds(480, 40, 200, 20);
		playerArea.setFont(myFont);
		playerArea.setForeground(Color.RED);
		playerArea.setBackground(Color.BLACK);
		playerArea.setEditable(false);
		frame.add(playerArea);

		//hpArea setup
		hpArea = new JTextArea(1, 30);
		hpArea.setBounds(480, 100, 200, 20);
		hpArea.setFont(new Font("Algerian", Font.PLAIN, 14));
		hpArea.setForeground(Color.RED);
		hpArea.setBackground(Color.BLACK);
		hpArea.setEditable(false);
		frame.add(hpArea);

		//attackArea setup
		attackArea = new JTextArea(1, 30);
		attackArea.setBounds(480, 160, 200, 20);
		attackArea.setFont(new Font("Algerian", Font.PLAIN, 14));
		attackArea.setForeground(Color.YELLOW);
		attackArea.setBackground(Color.BLACK);
		attackArea.setEditable(false);
		frame.add(attackArea);

		//placeArea setup
		placeArea = new JTextArea(1, 30);
		placeArea.setBounds(480, 220, 200, 20);
		placeArea.setFont(myFont);
		placeArea.setForeground(Color.GREEN);
		placeArea.setBackground(Color.BLACK);
		placeArea.setEditable(false);
		frame.add(placeArea);


		//inputArea setup
		inputArea = new JTextArea(1, 30);
		inputArea.setBounds(50, 380, 420, 20);
		inputArea.setForeground(Color.BLACK);
		inputArea.setBackground(Color.WHITE);
		frame.add(inputArea);

		//input button setup
		inputButton = new JButton("Enter Move");
		inputButton.setBounds(500, 350, 100, 60);
		inputButton.setBackground(Color.DARK_GRAY);
		inputButton.setForeground(Color.CYAN);
		inputButton.setEnabled(false);
		frame.add(inputButton);


		frame.setVisible(true);



	}


	public void display(String str)
	{

		textArea.setText(textArea.getText() + "\n" +  str);
		textArea.update(textArea.getGraphics());
		textArea.setCaretPosition(textArea.getDocument().getLength());
		frame.setVisible(true);

		//if moved to different gui or text interface dispose frame
		if(dispose == true)
			frame.dispose();

	}

	public void display(Character c)
	{
		frame.setTitle(c.name());
		playerArea.setText("Player: " + c.name());
		attackArea.setText("Attack: " + c.AttackDamage);
		hpArea.setText(	   "HP: " + c.health + " / " + c.healthCap );
		placeArea.setText( "Place: "+ c.current.name());
		c.display();
		frame.setVisible(true);

		//if moved to different gui or text interface dispose frame
		if(dispose == true)
			frame.dispose();
	}

	public String getLine()
	{

		inputButton.setEnabled(true);
		input = null;

		//waiting for input from inputArea
		while(input == null) {
			inputButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (inputArea.getText().isEmpty())
						input = null;
					else
						input = inputArea.getText();
				}
			});
		}

		
		//tokenizing input
		String[] inputArr = input.split("\\s+");

		if(inputArr[0].equalsIgnoreCase("TEXT") || inputArr[0].equalsIgnoreCase("GUI"))
			dispose = true;

		return input.trim();
	}



}
