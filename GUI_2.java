/*
 * TODO: Vanson Ho
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUI_2 implements UserInterface
{
	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel label;
	private JButton button;


	private JTextArea playerArea;
	private JTextArea hpArea;
	private JTextArea attackArea;
	private JTextArea placeArea;
	/*
        GUI_2()
        {
            frame = new JFrame("GUI_FRAME");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
        }

        public void display(String str)
        {

            frame = new JFrame("GUI_FRAME");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel(str);
            frame.getContentPane().add(label);

            frame.pack();
            frame.setVisible(true);
        }

        */
	GUI_2()
	{

		frame = new JFrame("MY_FRAME");
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,500);
		frame.setTitle("The Best Game Ever");



		Font myFont = new Font("Algerian", Font.PLAIN, 16);

		textArea = new JTextArea(20,25);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(50, 25, 425, 300);
		frame.add(scrollPane);

		//player area setup
		playerArea = new JTextArea(1, 30);
		playerArea.setBounds(490, 40, 175, 20);
		playerArea.setFont(myFont);
		playerArea.setForeground(Color.RED);
		frame.add(playerArea);

		//hpArea setup
		playerArea = new JTextArea(1, 30);
		playerArea.setBounds(490, 40, 175, 20);
		playerArea.setFont(myFont);
		playerArea.setForeground(Color.RED);
		frame.add(playerArea);


		frame.setVisible(true);



	}

	public void display(String str)
	{

		textArea.setText(textArea.getText() + "\n" +  str);
		textArea.update(textArea.getGraphics());
		frame.setVisible(true);

	}

	public void display(Character c)
	{
		frame.setTitle(c.name());
		playerArea.setText("Player: " + c.name());
		c.display();
		frame.setVisible(true);
	}
	public String getLine()
	{
		String input = JOptionPane.showInputDialog(null, "Input", "Please type a String", 0);

		textArea.setText(textArea.getText() + "\n" + "Making next move...");
		return input;
	}



}
