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
		
<<<<<<< HEAD
		CarryCap = 25; //amount of weight a character can hold
		currCap = 0;
=======
		isAlive = true;
>>>>>>> bb31e939a57ff794fdabb040dcaac2c952a0c8bc
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
					Artifact result = current.getArtifact(move.argument());
					// Attempt to turn our given argument into an Artifact name and retrieve it
					//Artifact result = current.removeArtifactByName(move.argument());
					
					
					
					// Invalid Artifact?
					if (result == null)
					{
						System.out.println("*Sorry but the '" + move.argument() + "' doesn't exist here...* \n");
					}//check capacity
					else if((this.currCap + result.getMobility()) > this.CarryCap)
					{
						System.out.println("You cant carry this item! You do not have enough capacity!");
					}
					else
					{
						// Update our current Place and our own collection of Artifacts
						System.out.println("*Hooray! You've now got the '" + result.name() + "\n");
						artifacts.add(result);
						this.currCap = this.currCap + result.getMobility();
						System.out.println("Current held weight increased to: " + this.currCap + "/" + this.CarryCap);
						
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
							this.currCap = this.currCap - x.getMobility();
							System.out.println("Capcity reduced to: " + this.currCap + "/" + this.CarryCap);
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
							//check if the object is usuable
							if(x.getTypeID() == 8)
							{
								//not food or Book
								// We have it! Now use it on every possible Direction
								System.out.println("You can't use food you must eat it!");
								break;
								
							}
							else if(x.getTypeID() == 2)
							{
								System.out.println("You can't use a book/scroll you must read it!");
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
								System.out.println("Trying to use artifact");
								x.use(this);
								//break;
							}
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
				case READ:
				{
					// Attempt to find the given Artifact name in our collection
					int count = 0;
					int size = artifacts.size();
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
								System.out.println("You can't read food, you must eat it!");
								break;
							}
							else
							{
								System.out.println("You can't read this Artifact you must use it!");
								break;
							}
						}
					}
				}
				case EAT:
				{
					// Attempt to find the given Artifact name in our collection
					int count = 0;
					int size = artifacts.size();
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
								System.out.println("You can't eat a book, you must read it!");
								break;
							}
							else
							{
								System.out.println("You can't eat this Artifact you must use it!");
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
							System.out.println("You must have at least two potions to mix");
							break;
						}
						
						//prompt player for two potions to be used
						System.out.println("List of Potions you can mix: \n "
										  +"(Enter the NUMBERS of both seperated by a comma ex: 1,2)\n\n");
						int pCount = 0;
						for(Artifact x: potionList)
						{
							System.out.println(pCount + ". " + x.name());
							pCount++;
						}
						
						System.out.println("\n>> ");
						//get input
						KeyboardScanner ksc = new KeyboardScanner();
						Scanner sc = ksc.getKeyboardScanner();
						String line = sc.nextLine().trim().toUpperCase();
						String[] arr = line.split(",");
						
						if(arr.length < 2)
						{
							System.out.println("You must enter two potions");
							break;
						}
						
						//check input validity
						if(Integer.parseInt(arr[0]) > pCount 
						|| Integer.parseInt(arr[0]) < 0 
						|| Integer.parseInt(arr[1]) > pCount
						|| Integer.parseInt(arr[1]) < 0)
						{
							System.out.println("Error: Input Given does not match bounds!");
							break;
						}
						
						//check if potion listed is the same
						if(Integer.parseInt(arr[0]) == Integer.parseInt(arr[1]))
						{
							System.out.println("Error: you can't mix the same potion with itself");
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
						System.out.println("You must be in the potions lab to mix potions");
						break;
					}
				}
				case ATTACK:
				{
					//first check to see if there is more than one player in the room
					if(this.current.allCharacters.size() > 1)
					{
						System.out.println(this.name() + " has attacked all characters in the room!");
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
								System.out.println(a.name() + " took damage! Health after attack: " + a.health + "/" + a.healthCap);
								
								//check if player died
								if(a.health == 0 || a.health < 0)
								{
									a.isAlive = false;
									System.out.println(a.name() + " has died after attack from " + this.name() + "!\n");
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
						System.out.println("You attacked what seemed to a ghost of your imagination");
						hasMoved = true;
						break;
					}
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
		
		System.out.println("\nCurrent Health: " + this.health + "/" + this.healthCap);
		System.out.println("Current Attack Damage: " + this.AttackDamage);
		System.out.println("Current Weight Held: " + this.currCap + "/" + this.CarryCap);
		
		
		System.out.println("Players alive in game: " + Game.nPlayersAlive);
		
		if(this.current.allCharacters.size() > 1)
		{
			System.out.println("\nOther characters in the room with you: ");
			for(Character c: this.current.allCharacters)
			{
				if(this.name.equals(c.name()))
				{
					//do nothing
				}
				else
					System.out.println(c.name());
			}
			System.out.println("\n");
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
		System.out.println("Your health capacity was increased by 2");
		this.healthCap = this.healthCap + 2;
		System.out.println("Health Capacity: " + this.healthCap);
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
		
		System.out.println("Health restored to: " + this.health + "/" + this.healthCap);
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
