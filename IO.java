/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * Project: Term Project Part V
 */

public class IO 
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Fields */
	public static final int TEXT = 0;
	public static final int GUI_1 = 1;
	public static final int GUI_2 = 2;
	public static final int GUI_3 = 3;
	private UserInterface implementor;
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Constructors */
	public IO()
	{
		// default
		selectInterface(0);
	}
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Methods */
	public void display(String str)
	{
		implementor.display(str);
	}
	
	public void display(Character c)
	{
		implementor.display(c);
	}
	
	public String getLine()
	{
		return implementor.getLine();
	}
	
	public void selectInterface(int x)
	{
		switch(x)
		{
			case TEXT:
				implementor = new TextInterface();
				break;
			case GUI_1:
				implementor = new GUI_1();
				break;
			case GUI_2:
				implementor = new GUI_2();
				break;
			case GUI_3:
				implementor = new GUI_3();
				break;
			default:
				System.err.println("selectInterface(int x) -> invalid x");
				break;
		}
	}
}
