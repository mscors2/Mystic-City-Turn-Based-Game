import java.util.Scanner;

public class Artifact {
	private int ID;
	private String name;
	private int val;
	private String description;
	private int mobility;
	private int keyPattern;
	private int ArtifactType;

	public Artifact(int id, String name, int value, int weight, String desc, int keyPat, int type)
	{
		ID = id;
		this.name = name;
		this.val = value;
		this.mobility = weight;
		this.description = desc;
		this.keyPattern = keyPat;
		this.ArtifactType = type;
	}
	
	public Artifact(Scanner sc, int typeGiven, int placeID)
	{
		//This is for assigning types of artifacts
		this.ArtifactType = typeGiven;
		
		// Setup .gdf parsing
		String line = "";
		String[] arr = new String[0];
		
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
		
		
		
	}
	
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
			this.ArtifactType = -1; //this is for later versions of the gdf
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
			if (arr.length < 1)
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
		else if (version == 5.1)
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
			if (arr.length < 1)
			{
				System.err.println("GDF->error: \n"
						+ "Artifact should have at least 1 Strings on the second line");
			}			
			
			//assess the type of Artifact given and call 
			
			int typeGiven = Integer.parseInt(arr[0]);
			if(typeGiven == 0)
			{
				//container
				Container c = new Container(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(c);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + c.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(c);
				else
					Place.getPlaceByID(placeID).addArtifact(c);
			}
			else if(typeGiven == 1)
			{
				//wand
				Wand w = new Wand(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(w);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + w.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(w);
				else
					Place.getPlaceByID(placeID).addArtifact(w);
			}
			else if(typeGiven == 2)
			{
				//book
				Book b = new Book(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(b);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + b.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(b);
				else
					Place.getPlaceByID(placeID).addArtifact(b);
			}
			else if(typeGiven == 3)
			{
				//treasure
				Treasure tr = new Treasure(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(tr);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + tr.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(tr);
				else
					Place.getPlaceByID(placeID).addArtifact(tr);
				
			}
			else if(typeGiven == 4)
			{
				//tool
				Tool t = new Tool(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(t);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + t.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(t);
				else
					Place.getPlaceByID(placeID).addArtifact(t);
				
			}
			else if(typeGiven == 5)
			{
				//bag
				Bag bg = new Bag(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(bg);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + bg.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(bg);
				else
					Place.getPlaceByID(placeID).addArtifact(bg);
			}
			else if(typeGiven == 6)
			{
				//key
				Key k = new Key(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(k);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + k.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(k);
				else
					Place.getPlaceByID(placeID).addArtifact(k);
					
			}
			else if(typeGiven == 7)
			{
				//potion
				Potion p = new Potion(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(p);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + p.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(p);
				else
					Place.getPlaceByID(placeID).addArtifact(p);
			}
			else if(typeGiven == 8)
			{
				//potion
				Food f = new Food(sc, placeID);
				
				if (placeID < 0)
				{
					Game.getCharacterByID(Math.abs(placeID)).addArtifact(f);
					Game.getCharacterByID(Math.abs(placeID)).currCap = Game.getCharacterByID(Math.abs(placeID)).currCap + f.getMobility();
				}
				else if (placeID == 0)
					Place.getRandomPlace().addArtifact(f);
				else
					Place.getPlaceByID(placeID).addArtifact(f);
			}
			
			
			
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
	
	public boolean isMasterKey()
	{
		return keyPattern < 0;
	}
	
	public String name()
	{
		return name;
	}
	
	public boolean isKey()
	{
		return keyPattern > 0;
	}
	
	public void use(Character c)
	{
		//this should get overriden but if it doesnt, this is default
		
		if (this.isKey())
		{
			c.current.useKey(this);
		}
		else
		{
			System.out.println("You have used: " + this.name);
			//remove from inventory
			c.removeArtByID(this.getID());
		}
	}
	
	public String getDesc()
	{
		return description;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public int getTypeID()
	{
		return ArtifactType;
	}
	
	public int getMobility()
	{
		return this.mobility;
	}
	
	public void increaseCapacity(Character c)
	{
		c.CarryCap = c.CarryCap + 20;
		System.out.println("CARRR: " + c.CarryCap);
		System.out.println("Your carry capacity was increased by 20!");
	}
}
