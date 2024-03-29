import java.util.Scanner;

/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part IV
 */

public class Killer extends NPC
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Constructors */
	public Killer(Scanner sc, double version)
	{
		super(sc, version);
	}
	
	public Killer(int ID, String name, String description, String type, Place current)
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
		io.display(current.getDisplay());
		display();
		System.out.println();
		
		// Our DecisionMaker is AI
		SpecialAI sai = new SpecialAI();
		Move move = sai.getMove(this, current);
		
		// Execute the decision
		makeMove(move);
	}
}
