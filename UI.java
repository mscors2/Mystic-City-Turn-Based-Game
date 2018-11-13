import java.util.Scanner;

/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part IV
 */

public class UI implements DecisionMaker
{
	/*
	 * (non-Javadoc)
	 * @see DecisionMaker#getMove(Character, Place)
	 * 
	 * Automatic response^ generated from overriding an interface method
	 */
	public Move getMove(Character character, Place place) 
	{	
		// Prompt the user for a move and argument
		KeyboardScanner ksc = new KeyboardScanner();
		Scanner sc = ksc.getKeyboardScanner();
		Move.MoveType type = Move.MoveType.PASS;	// temporary
		String argument = "";						// temporary
		
		// this flag is more for precaution
		boolean isLoop = true;
		do
		{
			System.out.print(">> ");
			String line = sc.nextLine().trim().toUpperCase();
			String[] arr = line.split("\\s+");
			
			if 		(arr[0].equalsIgnoreCase("QUIT") || arr[0].equalsIgnoreCase("EXIT"))
				type = Move.MoveType.QUIT;
			else if (arr[0].equalsIgnoreCase("LOOK"))
				type = Move.MoveType.LOOK;
			else if (arr[0].equalsIgnoreCase("INVENTORY") || arr[0].equalsIgnoreCase("INVE"))
				type = Move.MoveType.INVENTORY;
			else if (arr[0].equalsIgnoreCase("GO"))
				type = Move.MoveType.GO;
			else if (arr[0].equalsIgnoreCase("GET"))
				type = Move.MoveType.GET;
			else if (arr[0].equalsIgnoreCase("DROP"))
				type = Move.MoveType.DROP;
			else if (arr[0].equalsIgnoreCase("USE"))
				type = Move.MoveType.USE;
			else if (arr[0].equalsIgnoreCase("PASS"))
				type = Move.MoveType.PASS;
			else
			{
				// invalid user command, try again
				System.out.println("*An old and ancient voice fills your mind...* \n"
						+ " -> QUIT or EXIT \n"
						+ " -> LOOK \n"
						+ " -> INVENTORY or INVE \n"
						+ " -> GO <direction> \n"
						+ " -> GET <artifact_name> \n"
						+ " -> DROP <artifact_name> \n"
						+ " -> USE <artifact_name> \n"
						+ " -> PASS (skip a turn!) \n");
				
				// keep looping
				continue;
			}
			
			// construct the argument for the valid move
			for (int i = 1; i < arr.length; i++)
				argument += arr[i] + " ";
			argument = argument.trim();
			
			// break out of the loop (again the flag was just in case)
			isLoop = false;
			break;
			
		} while (isLoop);
		
		// encapsulate this in a Move object
		Move move = new Move(type, argument);
		return move;
	}
	
}
