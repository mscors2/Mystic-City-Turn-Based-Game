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
		io.display(current.getDisplay());
		display();
		io.display("");
		
		// did the Place kill us?
		if (!isAlive)
		{
			io.display(name + " is dead! \n");
			return;
		}

		
		UI ui = new UI();
		Move move = ui.getMove(this, current);
		
		// execute the move
		makeMove(move);
	}
}
