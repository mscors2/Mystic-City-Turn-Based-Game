import java.util.Scanner;

/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part IV
 */

public class NPC extends Character 
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Constructors */
	public NPC(Scanner sc, double version)
	{
		super(sc, version);
	}
	
	public NPC(int ID, String name, String description, String type, Place current)
	{
		super(ID, name, description, type, current);
	}
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Main Methods */
	
	/*
	 * Override makeMove()
	 */
	public void makeMove()
	{
		// Display basic Character/Place information
		current.display();
		this.display();
		System.out.println();
		
		// Did Place kill us?
		if (!isAlive)
		{
			System.out.println(name + " is dead! \n");
			return;
		}
		
		// Our DecisionMaker is AI
		AI ai = new AI();
		Move move = ai.getMove(this, current);
		
		// Execute the decision
		makeMove(move);
	}
}
