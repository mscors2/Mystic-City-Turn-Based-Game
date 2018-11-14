import java.util.Scanner;
import java.util.Vector;

/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * Project: Term Project Part IV
 */

public class Character 
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Fields */
	
	protected int ID;
	protected String name;
	protected String description;
	protected String type;					// Supports "PLAYER" and "NPC" only
	
	protected Place current;				// Reference
	protected Vector<Artifact> artifacts; 	// Reference
	
	protected boolean isAlive;
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Constructors */
	
	public Character()
	{
		// Error message
		System.err.println("Character->default constructor: \n"
				+ "No argument constructor means this Character is nameless \n");
		
		// Initialize
		ID = -1;
		name = "";
		description = "";	
		type = "NPC";
		
		// TODO
		current = Place.getPlaceByID(1);
		artifacts = new Vector<Artifact>();
	}

	public Character(int ID, String name, String description, String type, Place current)
	{
		// Character building from version 4.0 and up only
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.type = type;
	
		this.current = current;
		this.artifacts = new Vector<Artifact>();
		
		isAlive = true;
	}
	
	public Character(Scanner sc, double version)
	{
		// Version support
		if (version < 4.0)
		{
			System.err.println("Character->scanner constructor: \n"
					+ "Character building is from version 4.0 and up \n");
			return;
		}
		else if (version == 4.0)
		{
			// Setup .gdf parsing
			String line = "";
			String[] arr = new String[0];
			
			// TYPE PlaceID
			// TYPE ->		"PLAYER" or "NPC"
			// PlaceID ->	> 0 indicates the starting place, == 0 indicates random and < 0 not permitted 
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length != 2)
			{
				System.err.println("GDF->error: \n"
						+ "Character should only have two strings in the first line \n");
				return;
			}

			// Assign
			String type = arr[0];
			int placeID = Integer.parseInt(arr[1]);
			Place current = null;
			if (placeID == 0)
				current = Place.getRandomPlace();
			else
				current = Place.getPlaceByID(Math.abs(placeID));
			
			// ID long_name_with_spaces
			// ID ->	unique integer > 0
			// name ->	resulting string with multiple spaces in it
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length < 2)
			{
				System.err.println("GDF->error: \n"
						+ "Character should have at least two strings in the second line \n");
				return;
			}
			
			// Assign
			int ID = Integer.parseInt(arr[0]);
			String name = "";
			for (int i = 1; i < arr.length; i++)
				name += arr[i] + " ";
			name = name.trim();
			
			// ndescr ->		number of lines of the description
			// description ->	description itself ("\n" are in it)
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length != 1)
			{
				System.err.println("GDF->error: \n"
						+ "Character should only have one string on the third line \n");
				return;
			}
			
			// Assign
			int ndescr = Integer.parseInt(arr[0]);
			String description = "";
			for (int i = 0; i < ndescr; i++)
				description += CleanLineScanner.getCleanLine(sc) + "\n";
			description = description.trim();
			
			// Finally, initialize specific Character and add to collections
			if (type.equalsIgnoreCase("PLAYER"))
			{
				Player p = new Player(ID, name, description, type, current);
				Game.addCharacter(ID, p);
				current.addCharacter(p);
			}
			else
			{
				NPC npc = new NPC(ID, name, description, type, current);
				Game.addCharacter(ID, npc);
				current.addCharacter(npc);
			}
		}
		else if (version >= 5.0)
		{
			// Setup .gdf parsing
			String line = "";
			String[] arr = new String[0];
			
			// TYPE ->		"PLAYER" or "NPC"
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
	
			// Assign
			String type = arr[0];
	
			// PlaceID ->	> 0 indicates the starting place, == 0 indicates random and < 0 not permitted
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Assign
			int placeID = Integer.parseInt(arr[0]);
			Place current = null;
			if (placeID == 0)
				current = Place.getRandomPlace();
			else
				current = Place.getPlaceByID(Math.abs(placeID));
			
			// ID long_name_with_spaces
			// ID ->	unique integer > 0
			// name ->	resulting string with multiple spaces in it
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length < 2)
			{
				System.err.println("GDF->error: \n"
						+ "Character should have at least two strings in the second line \n");
				return;
			}
			
			// Assign
			int ID = Integer.parseInt(arr[0]);
			String name = "";
			for (int i = 1; i < arr.length; i++)
				name += arr[i] + " ";
			name = name.trim();
			
			// ndescr ->		number of lines of the description
			// description ->	description itself ("\n" are in it)
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length != 1)
			{
				System.err.println("GDF->error: \n"
						+ "Character should only have one string on the third line \n");
				return;
			}
			
			// Assign
			int ndescr = Integer.parseInt(arr[0]);
			String description = "";
			for (int i = 0; i < ndescr; i++)
				description += CleanLineScanner.getCleanLine(sc) + "\n";
			description = description.trim();
			
			// Finally, initialize specific Character and add to collections
			if (type.equalsIgnoreCase("PLAYER"))
			{
				Player p = new Player(ID, name, description, type, current);
				Game.addCharacter(ID, p);
				current.addCharacter(p);
				Game.nPlayers++;
			}
			else if (type.equalsIgnoreCase("JOKER"))
			{
				Joker j = new Joker(ID, name, description, type, current);
				Game.addCharacter(ID, j);
				current.addCharacter(j);
			}
			else if (type.equalsIgnoreCase("WIZARD"))
			{
				Wizard w = new Wizard(ID, name, description, type, current);
				Game.addCharacter(ID, w);
				current.addCharacter(w);
			}
			else
			{
				// Default to NPC
				NPC npc = new NPC(ID, name, description, type, current);
				Game.addCharacter(ID, npc);
				current.addCharacter(npc);
			}
		}
		else
		{
			// Invalid version
			System.err.println("Character->scanner constructor: \n"
					+ "Character building is only supported by version 4.0 and 5.0");
			return;
		}
	}
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Main Methods */
	
	/*
	 *  No move given, just print out information
	 */
	public void makeMove()
	{
		// Print current Place and Player information
		current.display();
		this.display();
	}
	
	/*
	 * Switch statements will handle and execute the appropriate move
	 */
	public void makeMove(Move move)
	{					
		// Switch move
		boolean hasMoved = false;
		while (!hasMoved)
		{
			switch (move.type())
			{
				// All possible moves
				case LOOK:
				{
					// Re-print the current Place information
					System.out.println("*Really? Just look above this message... ugh okay...* \n");
					current.display();
					break;
				}
				case QUIT:
				{
					// Exit/Quit out of the entire game (lot of power for any Player)
					System.out.println("*Well at least you tried, it's been fun! Try again sometime won't you?* \n");
					System.exit(0);
				}
				case INVENTORY:
				{
					// Empty vs Non-empty collection
					if (artifacts.size() < 1)
					{
						// No Artifacts
						System.out.println("*Your bag falls like a feather as you set it on the ground...* \n"
								+ "*There's nothing inside...* \n");
					}
					else
					{
						// Print all Artifacts
						System.out.println("*You put you bag on the ground and look inside...*");
						String allArtifacts = "";
						for (Artifact x : artifacts)
							allArtifacts += x.name() + ", ";
						allArtifacts = allArtifacts.substring(0, allArtifacts.length() - 2);
						System.out.println("[" + allArtifacts + "] \n");
					}
					break;
				}
				case GO:
				{
					// Attempt to turn our given argument into a valid Direction and follow it
					String direction = move.argument();
					Place result = current.followDirection(direction);
					
					// Invalid Direction?
					if (result == current)
					{
						System.out.println("*Whoops! You can't go that way!* \n");
					}
					else
					{					
						// Update our new Place to our current Place
						current.removeCharacter(this);
						result.addCharacter(this);
						current = result;
						
						// Valid move
						hasMoved = true;
						
						// Check for the EXIT Place
						if (current.match("EXIT"))
						{
							System.out.println("*CONGRATULATIONS! You beat the game!* \n");
							System.exit(0);
						}
					}
					break;
				}
				case GET:
				{
					// Attempt to turn our given argument into an Artifact name and retrieve it
					Artifact result = current.removeArtifactByName(move.argument());
					
					// Invalid Artifact?
					if (result == null)
					{
						System.out.println("*Sorry but the '" + move.argument() + "' doesn't exist here...* \n");
					}
					else
					{
						// Update our current Place and our own collection of Artifacts
						System.out.println("*Hooray! You've now got the '" + result.name() + "' key!* \n");
						artifacts.add(result);
					}
					break;
				}
				case DROP:
				{
					// Attempt to find the given Artifact name in our collection
					int count = 0;
					int size = artifacts.size();
					for (Artifact x : artifacts)
					{
						// Valid Artifact name?
						if (x.match(move.argument()))
						{
							// We have it! Now update the current Place and our own collection of Artifacts
							System.out.println("*You place the '" + x.name() + "' back on the floor* \n");
							current.addArtifact(x);
							artifacts.remove(x);
							break;
						}
						count++;
					}
					
					// Invalid Artifact name
					if (count == size)
						System.out.println("*Whoops you don't even have the '" + move.argument() + "'...* \n");
					break;
				}
				case USE:
				{
					// Attempt to find the given Artifact name in our collection
					int count = 0;
					int size = artifacts.size();
					for (Artifact x : artifacts)
					{
						// Valid Artifact name?
						if (x.match(move.argument()))
						{
							// We have it! Now use it on every possible Direction
							System.out.println("*Let's try the '" + x.name() + "' on every door!* \n");
							current.useKey(x);
							break;
						}
						count++;
					}
					
					// Invalid Artifact name
					if (count == size)
						System.out.println("*Slow down buddy we don't even have the '" + move.argument() + "'!* \n");
					break;
				}
				case PASS:
				{
					// Print a PASS message and move on
					System.out.println("*Well aren't you a useful hero... NEXT!!!* \n");
					
					// Valid move
					hasMoved = true;
					
					break;
				}
			}
			
			// Do we need to move again?
			if (type.equalsIgnoreCase("PLAYER") && !hasMoved)
			{
				// Get a new move from the Player
				move = new UI().getMove(this, current);
			}
			else
			{
				// Skip AI who made a bad move
				hasMoved = true;
			}
			
		}
	}

	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Support Methods */
	
	/*
	 * Returns the nname of our Character
	 */
	public String name()
	{
		return name;
	}
	
	/*
	 * print() is for debugging
	 */
	public void print()
	{
		System.err.println("Character information: \n"
				+ " -> ID = " + ID + " \n"
				+ " -> name = " + name + " \n"
				+ " -> description = " + description + " \n");
	}
	
	/*
	 * display() is for normal play 
	 */
	public void display()
	{
		System.out.println("*The '" + name + "' hero! (#" + String.format("%03d", ID) + ")* \n"
				+ description);
	}
	
	/*
	 * ADDED: Helps compare Character objects
	 */
	public boolean match(String name)
	{
		return this.name.equalsIgnoreCase(name);
	}
	
	public boolean match(Character character)
	{
		return match(character.name);
	}
	
	public boolean match(int ID)
	{
		return this.ID == ID;
	}
	
	/*
	 * ADDED: Getter method for all Artifact names in our collection
	 */
	public String[] getArtifactNames()
	{
		// Populate an array of names and return it
		String[] arr = new String[artifacts.size()];
		for (int i = 0; i < arr.length; i++)
			arr[i] = artifacts.get(i).name(); 
		
		return arr;
	}
	
	/*
	 * ADDED: to help Artifact Constructor
	 */
	public void addArtifact(Artifact a)
	{
		if (a == null)
			return;
		
		artifacts.add(a);
	}
	
	/*
	 * ADDED: Type getter method
	 */
	public String type()
	{
		return type;
	}
	
	/*
	 * ADDED: supports special Places
	 */
	public void setPlace(Place p)
	{
		current = p;
	}
	
	public void kill()
	{
		isAlive = false;
	}
	
	public void resurrect()
	{
		isAlive = true;
	}
	
	public boolean isAlive()
	{
		return isAlive;
	}
	
	public Artifact loseArtifact()
	{
		if (artifacts.size() > 1)
			return artifacts.remove(0);
		else
			return null;
	}
}
