import java.util.Scanner;
import java.util.Vector;
import java.util.*;

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
	protected int health;
	protected int healthCap;
	protected int AttackDamage;
	protected Place current;				// Reference
	protected Vector<Artifact> artifacts; 	// Reference
	protected int CarryCap;
	protected int currCap;
	protected boolean isAlive;
	public IO io;
	
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
		health = 3;
		healthCap = 3;
		AttackDamage = 1;
		// TODO
		current = Place.getPlaceByID(1);
		artifacts = new Vector<Artifact>();
		
		CarryCap = 25; //amount of weight a character can hold
		currCap = 0;
		isAlive = true;
		io = new IO();
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
		health = 3;
		healthCap = 3;
		AttackDamage = 1;
		isAlive = true;
		CarryCap = 25; //amount of weight a character can hold
		currCap = 0;
		io = new IO();
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
				p.health = 3;
				p.AttackDamage = 1;
				p.CarryCap = 5; //amount of weight a character can hold
				p.currCap = 0;
				Game.addCharacter(ID, p);
				current.addCharacter(p);
				Game.nPlayers++;
			}
			else if (type.equalsIgnoreCase("JOKER"))
			{
				Joker j = new Joker(ID, name, description, type, current);
				j.health = 3;
				j.AttackDamage = 1;
				j.healthCap = 3;
				j.CarryCap = 5; //amount of weight a character can hold
				j.currCap = 0;
				Game.addCharacter(ID, j);
				current.addCharacter(j);
			}
			else if (type.equalsIgnoreCase("WIZARD"))
			{
				Wizard w = new Wizard(ID, name, description, type, current);
				w.health = 3;
				w.AttackDamage = 1;
				w.healthCap = 3;
				w.CarryCap = 5; //amount of weight a character can hold
				w.currCap = 0;
				Game.addCharacter(ID, w);
				current.addCharacter(w);
			}
			else if (type.equalsIgnoreCase("KILLER"))
			{
				Killer k = new Killer(ID, name, description, type, current);
				k.health = 3;
				k.healthCap = 3;
				k.AttackDamage = 1;
				k.CarryCap = 5; //amount of weight a character can hold
				k.currCap = 0;
				
				Game.addCharacter(ID, k);
				current.addCharacter(k);
			}
			else
			{
				// Default to NPC
				NPC npc = new NPC(ID, name, description, type, current);
				npc.health = 3;
				npc.AttackDamage = 1;
				npc.healthCap = 3;
				npc.CarryCap = 25; //amount of weight a character can hold
				npc.currCap = 0;
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
		current.display(io);
		display();
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
					io.display("*Really? Just look above this message... ugh okay...* \n");
					current.display(io);
					break;
				}
				case QUIT:
				{
					// Exit/Quit out of the entire game (lot of power for any Player)
					io.display("*Well at least you tried, it's been fun! Try again sometime won't you?* \n");
					System.out.println("Exiting the game... goodbye~ \n");
					System.exit(0);
				}
				case INVENTORY:
				{
					// Empty vs Non-empty collection
					if (artifacts.size() < 1)
					{
						// No Artifacts
						io.display("*Your bag falls like a feather as you set it on the ground...* \n"
								+ "*There's nothing inside...* \n");
					}
					else
					{
						// Print all Artifacts
						io.display("*You put you bag on the ground and look inside...*");
						String allArtifacts = "";
						for (Artifact x : artifacts)
							allArtifacts += x.name() + ", ";
						allArtifacts = allArtifacts.substring(0, allArtifacts.length() - 2);
						io.display("[" + allArtifacts + "] \n");
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
						io.display("*Whoops! You can't go that way!* \n");
					}
					else
					{				
						io.display("Following " + direction);
						// Update our new Place to our current Place
						current.removeCharacter(this);
						result.addCharacter(this);
						current = result;
						
						// Valid move
						hasMoved = true;
						
						// Check for the EXIT Place
						if (current.match("EXIT"))
						{
							io.display("*CONGRATULATIONS! You beat the game!* \n");
							System.exit(0);
						}
					}
					break;
				}
				case GET:
				{
					Artifact result = current.getArtifact(move.argument());
					// Attempt to turn our given argument into an Artifact name and retrieve it
					//Artifact result = current.removeArtifactByName(move.argument());
					
					
					
					// Invalid Artifact?
					if (result == null)
					{
						io.display("*Sorry but the '" + move.argument() + "' doesn't exist here...* \n");
					}//check capacity
					else if((this.currCap + result.getMobility()) > this.CarryCap)
					{
						io.display("You cant carry this item! You do not have enough capacity!");
						current.addArtifact(result);
					}
					else
					{
						// Update our current Place and our own collection of Artifacts
						io.display("*Hooray! You've now got the '" + result.name() + "\n");
						artifacts.add(result);
						this.currCap = this.currCap + result.getMobility();
						io.display("Current held weight increased to: " + this.currCap + "/" + this.CarryCap);
						
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
							io.display("*You place the '" + x.name() + "' back on the floor* \n");
							current.addArtifact(x);
							this.currCap = this.currCap - x.getMobility();
							io.display("Capcity reduced to: " + this.currCap + "/" + this.CarryCap);
							artifacts.remove(x);
							
							break;
						}
						count++;
					}
					
					// Invalid Artifact name
					if (count == size)
						io.display("*Whoops you don't even have the '" + move.argument() + "'...* \n");
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
							//check if the object is usuable
							if(x.getTypeID() == 8)
							{
								//not food or Book
								// We have it! Now use it on every possible Direction
								io.display("You can't use food you must eat it!");
								break;
								
							}
							else if(x.getTypeID() == 2)
							{
								io.display("You can't use a book/scroll you must read it!");
								break;
							}
							else if(x.getTypeID() == 7)
							{
								//using a potions counts as a move
								x.use(this);
								hasMoved = true;
								break;
							}
							else
							{
								// We have it! Now use it and let the child classes handle it
								io.display("Trying to use artifact");
								x.use(this);
								//break;
							}
						}
						count++;
					}
					
					// Invalid Artifact name
					if (count == size)
						io.display("*Slow down buddy we don't even have the '" + move.argument() + "'!* \n");
					break;
				}
				case PASS:
				{
					// Print a PASS message and move on
					io.display("*Well aren't you a useful hero... NEXT!!!* \n");
					
					// Valid move
					hasMoved = true;
					
					break;
				}
				case READ:
				{
					// Attempt to find the given Artifact name in our collection
					for (Artifact x : artifacts)
					{
						//check if we have a match
						if (x.match(move.argument()))
						{
							//check the object type
							if(x.getTypeID() == 2)
							{
								x.use(this);
								break;
							}
							else if(x.getTypeID() == 8)
							{
								io.display("You can't read food, you must eat it!");
								break;
							}
							else
							{
								io.display("You can't read this Artifact you must use it!");
								break;
							}
						}
					}
				}
				case EAT:
				{
					// Attempt to find the given Artifact name in our collection
					for (Artifact x : artifacts)
					{
						//check if we have a match
						if (x.match(move.argument()))
						{
							//check the object type
							if(x.getTypeID() == 8)
							{
								x.use(this);
								break;
							}
							else if(x.getTypeID() == 2)
							{
								io.display("You can't eat a book, you must read it!");
								break;
							}
							else
							{
								io.display("You can't eat this Artifact you must use it!");
								break;
							}
						}
					}
				}
				case MIX:
				{
					//first check if player is in potion lab
					if(this.current.name().equals("Potions Lab"))
					{
						int count = 0;
						//check to see if player has two potions
						List<Artifact> potionList = new ArrayList<Artifact>();
						for(Artifact x: artifacts)
						{
							if(x.getTypeID() == 7)
							{
								potionList.add(x);
								count++; 
							}
						}
						
						if(count < 2)
						{
							//player doesn't have two potions
							io.display("You must have at least two potions to mix");
							break;
						}
						
						//prompt player for two potions to be used
						io.display("List of Potions you can mix: \n "
										  +"(Enter the NUMBERS of both seperated by a comma ex: 1,2)\n\n");
						int pCount = 0;
						for(Artifact x: potionList)
						{
							io.display(pCount + ". " + x.name());
							pCount++;
						}
						
						//get input
						KeyboardScanner ksc = new KeyboardScanner();
						Scanner sc = ksc.getKeyboardScanner();
						String line = sc.nextLine().trim().toUpperCase();
						String[] arr = line.split(",");
						
						if(arr.length < 2)
						{
							io.display("You must enter two potions");
							break;
						}
						
						//check input validity
						if(Integer.parseInt(arr[0]) > pCount 
						|| Integer.parseInt(arr[0]) < 0 
						|| Integer.parseInt(arr[1]) > pCount
						|| Integer.parseInt(arr[1]) < 0)
						{
							io.display("Error: Input Given does not match bounds!");
							break;
						}
						
						//check if potion listed is the same
						if(Integer.parseInt(arr[0]) == Integer.parseInt(arr[1]))
						{
							io.display("Error: you can't mix the same potion with itself");
							break;
						}
						
						//send the potions into the mix function
						
						Artifact p1 = potionList.get(Integer.parseInt(arr[0]));
						Artifact p2 = potionList.get(Integer.parseInt(arr[1]));
						
						Potion newP = Potion.mix(this, p1, p2);
						this.artifacts.add(newP);
						break;
					}
					else
					{
						io.display("You must be in the potions lab to mix potions");
						break;
					}
				}
				case ATTACK:
				{
					//first check to see if there is more than one player in the room
					if(this.current.allCharacters.size() > 1)
					{
						io.display(this.name() + " has attacked all characters in the room!");
						for(Character a: this.current.allCharacters)
						{
							if (a.name().equalsIgnoreCase(this.name()))
							{
								//ignore this is the player attacking
							}
							else
							{
								//ATTACK
								a.health = a.health - this.AttackDamage;
								io.display(a.name() + " took damage! Health after attack: " + a.health + "/" + a.healthCap);
								
								//check if player died
								if(a.health == 0 || a.health < 0)
								{
									a.isAlive = false;
									io.display(a.name() + " has died after attack from " + this.name() + "!\n");
									//drop all loot from dead character and remove from game
									for (Artifact s: a.artifacts)
									{
										current.addArtifact(s);
										a.artifacts.remove(s);
									}
									for(Character z: current.allCharacters)
									{
										if(a.name() == z.name())
										{
											current.allCharacters.remove(z);
										}
											
									}
									//a.name = a.name + " (DEAD)";
									Game.nPlayersAlive = Game.nPlayersAlive - 1;
								}
							}
						}
						hasMoved = true;
						break;
					}
					else
					{
						io.display("You attacked what seemed to a ghost of your imagination");
						hasMoved = true;
						break;
					}
				}
				case TEXT:
				{
					io.display("Swapping to Text GUI (aka terminal)");
					io.selectInterface(0);
					break;
				}
				case GUI:
				{
					try 
					{
						int guiNum = Integer.parseInt(move.argument());
						if (guiNum < 1 || guiNum > 3)
							io.display("Sorry but that GUI # doesn't work");
						io.display("Swapping to GUI " + guiNum);
						io.selectInterface(guiNum);
						io.display("New GUI! Hello!");
					} 
					catch (NumberFormatException e) 
					{
						io.display("Sorry but that GUI # doesn't work");
					}
					break;
				}
				default:
				{
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
		io.display("*The '" + name + "' hero! (#" + String.format("%03d", ID) + ")* \n"
				+ description);
		
		io.display("\nCurrent Health: " + this.health + "/" + this.healthCap);
		io.display("Current Attack Damage: " + this.AttackDamage);
		io.display("Current Weight Held: " + this.currCap + "/" + this.CarryCap);
		
		
		io.display("Players alive in game: " + Game.nPlayersAlive);
		
		if(this.current.allCharacters.size() > 1)
		{
			io.display("\nOther characters in the room with you: ");
			for(Character c: this.current.allCharacters)
			{
				if(this.name.equals(c.name()))
				{
					//do nothing
				}
				else
					io.display(c.name());
			}
			io.display("\n");
		}
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
		// sometimes bad input
		if (a == null)
			return;

		// no duplicates allowed
		for (Artifact i : artifacts)
			if (i.match(a))
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
		current.removeCharacter(this);
		current = p;
		current.addCharacter(this);
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
	
	public void incrAttack()
	{
		this.AttackDamage = this.AttackDamage + 1;
	}
	
	public void incrHealthCap()
	{
		io.display("Your health capacity was increased by 2");
		this.healthCap = this.healthCap + 2;
		io.display("Health Capacity: " + this.healthCap);
	}
	
	public void restoreHealth()
	{
		int healthToGain = this.health + 2;
		//check to make sure health doesnt go over capacity
		if(healthToGain > this.healthCap)
		{
			health = healthCap;
		}
		else
		{
			health = health + 2;
		}
		
		io.display("Health restored to: " + this.health + "/" + this.healthCap);
	}
	
	public boolean isHealthFull()
	{
		return this.health == this.healthCap;
	}
	
	public void removeArtByID(int id)
	{
		for(Artifact a: artifacts)
		{
			if(a.getID() == id)
			{
				artifacts.remove(a);
			}
		}
	}
	
	
}
