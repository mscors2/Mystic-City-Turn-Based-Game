/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * Project: Term Project Part V
 */

public class TextInterface implements UserInterface
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Methods */
	public void display(String str)
	{
		System.out.println(str);
	}
	
	public String getLine()
	{
		System.out.print(">> ");
		return new KeyboardScanner().getKeyboardScanner().next();
	}
}
