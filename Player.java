import java.util.Scanner;

/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part IV
 */

public class Player extends Character
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Constructors */
	public Player(Scanner sc, double version)
	{
		super(sc, version);
	}
	
	public Player(int ID, String name, String description, String type, Place current)
	{
		// Character building from version 4.0 and up only
		super(ID, name, description, type, current);
	}

	
	/*
	 * override makeMove()
	 */
	public void makeMove()
	{		
		// display basic Character/Place information
		current.display();
		this.display();
		System.out.println();
		
		UI ui = new UI();
		Move move = ui.getMove(this, current);
		
		// execute the move
		makeMove(move);
	}
}
