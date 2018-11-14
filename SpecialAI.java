import java.util.Random;

/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part IV
 */

public class SpecialAI implements DecisionMaker 
{
	/*
	 * (non-Javadoc)
	 * @see DecisionMaker#getMove(Character, Place)
	 * 
	 * Automatic response^ generated from overriding an interface method
	 */
	public Move getMove(Character character, Place place) 
	{	
		// Generate special cases for Special NPCs
		Move.MoveType[] options = getOptions(character.type());
		
		// AI implementations will decide for NPC's only
		// ***BANNED MOVES: LOOK, QUIT, EXIT, INVENTORY*** 
		Move.MoveType type = options[new Random().nextInt(options.length)];
		String argument = "";
		
		// Generate a random argument for the given MoveType
		switch(type)
		{
			// All possible moves
			case GO:
			{
				// Choose a random Direction
				String[] directions = {"N", "E", "S", "W", "NE", "SE", "SW", "NW",
						"NNE", "NNW", "ENE", "WNW", "ESE", "WSW", "SSE", "SSW"};
				argument = directions[new Random().nextInt(directions.length)];
				break;
			}
			case PASS:
			{
				// Skip a turn
				argument = "";
				break;
			}
			case GET:
			{
				// Choose a random Artifact name from our given Place
				argument = place.getRandomArtifactName();
				break;
			}
			case DROP:
			case USE:
			{
				// Choose a random Artifact name from our given Character
				String[] names = character.getArtifactNames();
				if (names.length < 1)
					break;
				argument = names[new Random().nextInt(names.length)];
				break;
			}
			default:
			{
				// do nothing
			}
		}
		
		// Encapsulate our decision in a Move object
		Move move = new Move(type, argument);
		return move;
	}
	
	private Move.MoveType[] getOptions(String type)
	{
		if (type.equalsIgnoreCase("JOKER"))
		{
			Move.MoveType[] arr = new Move.MoveType[1];
			arr[0] = Move.MoveType.GET;
			return arr;
		}
		else
		{
			return Move.getAIMoves();
		}
	}
}
