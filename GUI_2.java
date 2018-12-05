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
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.setTitle("The Best Game Ever");

		/*
		textArea = new JTextArea(5,30);
		scrollPane = new JScrollPane(textArea);
		button = new JButton("Enter this to end turn");
		frame.setLayout(new FlowLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(scrollPane);

*/

		textArea = new JTextArea(20,60);

		scrollPane = new JScrollPane(textArea);
		frame.add(scrollPane);
//		frame.add(textArea);
		frame.setVisible(true);

	}

	public void display(String str)
	{

		textArea.setText(textArea.getText() + "\n" +  str);
		textArea.update(textArea.getGraphics());
		frame.setVisible(true);

	}
	public String getLine()
	{
		String input = JOptionPane.showInputDialog(null, "Input", "Please type a String", 0);

		textArea.setText(textArea.getText() + "\n" + "Making next move...");
		return input;
	}

}
