/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part IV
 */

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game 
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Fields */
	private String name;
	private double version;		// ADDED, for some reason this isn't in the rubric
	public static HashMap<Integer, Character> characters;	// reference
	
	public static int nPlayers;
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Constructors */
	public Game()
	{
		System.err.println("Game->default constructor: \n"
				+ "No Scanner argument constructor means no .gdf file \n"
				+ "which means this game has no game information \n");
		
		name = "";
		version = 0;
		characters = new HashMap<Integer, Character>();
	}
	
	public Game(Scanner sc)
	{
		// TODO
		// Setup .gdf parsing
		String line = "";
		String[] arr = new String[0];
		
		// GDF, version, Enironment Name
		line = CleanLineScanner.getCleanLine(sc);
		arr = line.split("\\s+");
		
		// Assign
		version = Double.parseDouble(arr[1]);
		name = "";
		for (int i = 2; i < arr.length; i++)
			name += arr[i] + " ";
		name = name.trim();
		characters = new HashMap<Integer, Character>();
		nPlayers = 0;
		
		// First initialize all collections
		Place.allPlacesHM = new HashMap<Integer, Place>();
		Place.allPlacesHM.put(0, new Place(0, "NOWHERE", "*This is an empty unescapable void of nothingness*"));
		Place.allPlacesHM.put(1, new Place(1, "EXIT", "*You find yourself outside the cold dark dungeon!*"));
		
		// PLACES, nPlaces
		line = CleanLineScanner.getCleanLine(sc);
		arr = line.split("\\s+");
		
		// Build all Places
		int nPlaces = Integer.parseInt(arr[1]);
		for (int i = 0; i < nPlaces; i++)
		{
			// Don't use this - Place will add itself to the collections
			Place temp = new Place(sc, version);
		}
		
		// DIRECTIONS nDirections
		line = CleanLineScanner.getCleanLine(sc);
		arr = line.split("\\s+");
		
		// Build all Directions
		int nDirections = Integer.parseInt(arr[1]);
		for (int i = 0; i < nDirections; i++)
		{
			// Don't use this - Direction will add itself to the collections
			Direction temp = new Direction(sc, version);
		}
		
		// Character building is from version 4.0 and up
		if (version >= 4.0)
		{
			// CHARACTERS nCharacters
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Build all Characters
			int nCharacters = Integer.parseInt(arr[1]);
			for (int i = 0; i < nCharacters; i++)
			{
				// Don't use this - Character will add itself to the collections
				Character temp = new Character(sc, version);
			}
		}
		else
		{
			// Add one player as this is now a single player game
			characters.put(0, new Character(0, "Main Hero", "*Simple but honest and fair*", "PLAYER", Place.getPlaceByID(1)));
		}
		
		// ARTIFACTS nArtifacts
		line = CleanLineScanner.getCleanLine(sc);
		arr = line.split("\\s+");
		
		// Build all Artifacts
		int nArtifacts = Integer.parseInt(arr[1]);
		for (int i = 0; i < nArtifacts; i++)
		{
			// Don't use this - Artifact will add itself to the collections
			Artifact temp = new Artifact(sc, version);
		}
	}
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Main Methods */
	public void play()
	{
		boolean playing = true;
		while (playing)
		{
			int count = 0;
			for (Integer key : characters.keySet())
			{
				Character c = characters.get(key);
				
				// Are they playable?
				if (c.isAlive())
				{
					c.makeMove();
					System.out.println("Retrieving next hero...\n");
					System.out.println("//------------------------------------------------------------------------// \n");
					
					// Give the user some time to read the logs
					try 
					{
						TimeUnit.SECONDS.sleep((long) 2.5);
					} 
					catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					count++;
					
					System.out.println(c.name() + " is dead! \n");
					System.out.println("Retrieving next hero...\n");
					System.out.println("//------------------------------------------------------------------------// \n");
					
					// Give the user some time to read the logs
					try 
					{
						TimeUnit.SECONDS.sleep((long) 2.0);
					} 
					catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				// Is everyone dead?
				if (count == Game.nPlayers)
					playing = false;
			}
		}
		
		// Quit game
		System.out.println("**GAME OVER! Everyone is dead!*");
		System.exit(0);
	}

	/* ----------------------------------------------------------------------------------------------------------- */
	/* Support Methods */
	public static Character getCharacterByID(int ID)
	{
		return characters.get(ID);
	}
	
	public static void addCharacter(int ID, Character character)
	{
		characters.put(ID, character);
	}
	
	public static Character getRandomCharacter()
	{
		if (characters.size() < 1)
			return null;
		
		Object[] keys = characters.keySet().toArray();
		return characters.get(keys[new Random().nextInt(keys.length)]);
	}
}
