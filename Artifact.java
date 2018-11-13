import java.util.Scanner;

public class Artifact {
	private int ID;
	private String name;
	private int val;
	private String description;
	private int mobility;
	private int keyPattern;

	public Artifact(Scanner sc, double version) {		
		// TODO
		// Version support
		if (version < 3.1)
		{
			System.err.println("Artifact->scanner constructor: \n"
					+ "Artifact building is from version 3.1 and up \n");
			return;
		}
		else if (version == 3.1)
		{
			// Setup .gdf parsing
			String line = "";
			String[] arr = new String[0];
			
			// PlaceID ->	0 is for Player's inventory 
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length != 1)
			{
				System.err.println("GDF->error: \n"
						+ "Artifact should only have one string in the first line \n");
				return;
			}

			// Assign
			int placeID = Integer.parseInt(arr[0]);
				
			// ID value mobility keyPattern name
			// ID ->	unique integer
			// value -> 	anything
			// mobility ->	< 0 for immovable >= 0 for movable
			// keyPattern ->	= 0 opens no locks
			// name ->	String and may contain spaces but not tabs
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length < 5)
			{
				System.err.println("GDF->error: \n"
						+ "Version 3.1 should have at least five Strings on the second line");
			}
			
			// Assign
			this.ID = Integer.parseInt(arr[0]);
			this.val = Integer.parseInt(arr[1]);
			this.mobility = Integer.parseInt(arr[2]);
			this.keyPattern = Integer.parseInt(arr[3]);
			this.name = "";
			for (int i = 4; i < arr.length; i++)
				this.name += arr[i] + " ";
			this.name = this.name.trim();
			
			// ndescr ->	number of description lines
			// description ->	String of next ndescr lines
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Assign
			int ndescr = Integer.parseInt(arr[0]);
			this.description = "";
			for (int i = 0; i < ndescr; i++)
				this.description += CleanLineScanner.getCleanLine(sc) + "\n";
			this.description = this.description.trim();
			
			// Assign the correct Place
			if (Math.abs(placeID) != 0)
				Place.getPlaceByID(Math.abs(placeID)).addArtifact(this);
			else
				Game.getCharacterByID(0).addArtifact(this);
		}
		else if (version == 4.0 || version == 5.0)
		{
			// Setup .gdf parsing
			String line = "";
			String[] arr = new String[0];
			
			// PlaceID ->	0 is for Player's inventory 
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length != 1)
			{
				System.err.println("GDF->error: \n"
						+ "Artifact should only have one string in the first line \n");
				return;
			}

			// Assign
			int placeID = Integer.parseInt(arr[0]);
				
			// ID value mobility keyPattern name
			// ID ->	unique integer
			// value -> 	anything
			// mobility ->	< 0 for immovable >= 0 for movable
			// keyPattern ->	= 0 opens no locks
			// name ->	String and may contain spaces but not tabs
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length < 5)
			{
				System.err.println("GDF->error: \n"
						+ "Artifact should have at least five Strings on the second line");
			}
			
			// Assign
			this.ID = Integer.parseInt(arr[0]);
			this.val = Integer.parseInt(arr[1]);
			this.mobility = Integer.parseInt(arr[2]);
			this.keyPattern = Integer.parseInt(arr[3]);
			this.name = "";
			for (int i = 4; i < arr.length; i++)
				this.name += arr[i] + " ";
			this.name = this.name.trim();
			
			// ndescr ->	number of description lines
			// description ->	String of next ndescr lines
			line = CleanLineScanner.getCleanLine(sc);
			arr = line.split("\\s+");
			
			// Assign
			int ndescr = Integer.parseInt(arr[0]);
			this.description = "";
			for (int i = 0; i < ndescr; i++)
				this.description += CleanLineScanner.getCleanLine(sc);
			this.description = this.description.trim();
			
			// Assign the correct Place
			if (placeID < 0)
				Game.getCharacterByID(Math.abs(placeID)).addArtifact(this);
			else if (placeID == 0)
				Place.getRandomPlace().addArtifact(this);
			else
				Place.getPlaceByID(placeID).addArtifact(this);
		}
	}


	// ADDED by Jack Delaney to help support matching
	public boolean match(String s)
	{
		return s.equalsIgnoreCase(name);
	}
	
	public boolean match(Artifact a)
	{
		return match(a.name);
	}
	
	public boolean match(int keyPattern)
	{
		return this.keyPattern == keyPattern;
	}
		
	public void print() {
		System.out.println("ID: " + ID);
		System.out.println("Name: " + name);
		System.out.println("Description: " + description);
		System.out.println("Weight: " + mobility);
		System.out.println("Value: " + val);
		System.out.println("Key Pattern: " + keyPattern);
	}
	
	public String name()
	{
		return name;
	}
	
	public boolean isKey()
	{
		return keyPattern > 0;
	}
}
