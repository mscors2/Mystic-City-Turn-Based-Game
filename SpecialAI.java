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
		Move.MoveType[] options = getOptions(character);
		
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
	
	private Move.MoveType[] getOptions(Character character)
	{
		if (character.type.equalsIgnoreCase("JOKER"))
		{
			// Jokers can only hoard artifacts
			Move.MoveType[] arr = new Move.MoveType[1];
			arr[0] = Move.MoveType.GET;
			return arr;
		}
		else if (character.type.equalsIgnoreCase("WIZARD"))
		{
			// WIZARDS will TELEPORT between rooms in addition to their move
			
			// Teleport
			character.current = Place.getRandomPlace();
			System.out.println("*Woosh! " + character.name + " teleported!*");
			
			// Wizards can only interact with Artifacts
			Move.MoveType[] arr = new Move.MoveType[3];
			arr[0] = Move.MoveType.GET;
			arr[1] = Move.MoveType.USE;
			arr[2] = Move.MoveType.DROP;
			return arr;
		}
		else
		{
			return Move.getAIMoves();
		}
	}
}
