import java.util.*;

//Vanson Ho
//vho6

public class Place {

    protected int ID;
    protected String name;
    protected String description;
    public static HashMap<Integer, Place> allPlacesHM;	// Global and static to the Place class
    protected ArrayList<Direction> allDirections;
    protected ArrayList<Artifact> allArtifacts;
    protected ArrayList<Character> allCharacters;

    Place()
    {}
    Place(Scanner myScanner, double version)
    {
        // TODO
    	// Version support
    	if (version < 3.1)
		{
			System.err.println("Place->scanner constructor: \n"
					+ "Place building is from version 3.1 and up \n");
			return;
		}
		else if (version >= 3.1)
		{
            // Setup .gdf parsing
            String line = "";
            String[] arr = new String[0];

            // ID long_name_with_spaces
            // ID ->	unique integer > 0
            // name ->	resulting string with multiple spaces in it
            line = CleanLineScanner.getCleanLine(myScanner);
            arr = line.split("\\s+");

            // Error check
            if (arr.length < 2)
            {
                System.err.println("GDF->error: \n"
                        + "Place should have at least two strings in the first line \n");
                return;
            }

            // Assign
            this.ID = Integer.parseInt(arr[0]);
            this.name = "";
            for (int i = 1; i < arr.length; i++)
                this.name += arr[i] + " ";
            this.name = this.name.trim();

            // ndescr -> 		n lines of description
            // descripion ->	self explanatory
            line = CleanLineScanner.getCleanLine(myScanner);
            arr = line.split("\\s+");

            // Assign
            int ndescr = Integer.parseInt(arr[0]);
            this.description = "";
            for (int i = 0; i < ndescr; i++)
                this.description += CleanLineScanner.getCleanLine(myScanner) + "\n";
            this.description = this.description.trim();

            // PORTAL CLASS EXTENSION :  ONLY FOR ID 321
            if(ID == 321)
            {
                Portal thisPortal = new Portal(ID, name, description);
                return;
            }
        }

        // Remaining fields are initialized
        allDirections = new ArrayList<Direction>();
        allArtifacts = new ArrayList<Artifact>();
        allCharacters = new ArrayList<Character>();

        // Add our instance to the global collection
        allPlacesHM.put(this.ID, this);
    }

    public Place(int ID, String name, String description)  //constructor for hw1 / testing
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.allDirections = new ArrayList<Direction>();
        this.allArtifacts = new ArrayList<Artifact>();
        this.allCharacters = new ArrayList<Character>();

        allPlacesHM.put(ID, this);
    }

    public static Place getPlaceByID(int x)  //gets place by id using hashmap
    {
        if(allPlacesHM.containsKey(x))
            return allPlacesHM.get(x);

        System.err.println("This id " + x + " is not associated with a place!");
        return null;
    }

    // ADDED by Jack Delaney to retrieve a random Place
    public static Place getRandomPlace()
    {
    	Object[] arr = allPlacesHM.keySet().toArray();
    	Object key = null;

    	// Random Place cannot be EXIT or NOWHERE
    	while (key == null || Integer.parseInt(key.toString()) <= 1)
    	{
    		key = arr[new Random().nextInt(arr.length)];
    	}

    	return allPlacesHM.get(key);
    }

    public void addArtifact(Artifact x)  //adds artifact
    {
    	// Avoid duplicates
    	for (Artifact i : allArtifacts)
    		if (i.match(x))
    			return;
    	
    	
        allArtifacts.add(x);
    }

    public String name()
    {
    	return name;
    }

    public void displayArtifacts()  // displays all artifacts in the room
    {
        System.out.println("Artifacts in this place:");
        if(allArtifacts.size() <= 0){
            System.out.println("No artifacts in this room");
        }

        for (int i =0; i < allArtifacts.size(); i++)
        {
            // TODO with Mike
            System.out.println(allArtifacts.get(i).name());
        }
    }

    public Artifact removeArtifactByName(String x)  //removes artifact from place
    {
        for(int i = 0; i < allArtifacts.size(); i++)
        {
        	// TODO with Mike
            if(allArtifacts.get(i).match(x))
            {
                return allArtifacts.remove(i);
            }
        }

        return null;
    }

    public void addCharacter(Character x)
    {
        allCharacters.add(x);
    }

    public void removeCharacter(Character x)
    {
        for(int i = 0; i < allCharacters.size(); i++)
        {
            if(x.match(allCharacters.get(i))) {
                allCharacters.remove(i);
                return;
            }
        }

        System.out.println("Character not in this place!");
        return;
    }

    public void addDirection(Direction newDirection)  //adds direction to the place
    {
        for(int i = 0; i < allDirections.size() ; i++)
        {
            if(allDirections.get(i).match(newDirection))
            {
            	// ERROR: ALREADY BEING USED
                return;
            }
        }
        allDirections.add(newDirection);
    }

    public void useKey(Artifact x)  // uses key on all directions
    {
        for(int i = 0;i < allDirections.size(); i++) {
        	if(x.isMasterKey())
        	{
        		//we have a master key unlock it
        		if(allDirections.get(i).isLocked())
        		{
        			allDirections.get(i).unlock();
        		}
        	}
        	else
        	{
        		//go through and check key patterns to unlock
        		allDirections.get(i).useKey(x);
        	}
        		
        }
    }

    public Place followDirection(String direction)  //follows the direction
    {
        for(int i = 0; i < allDirections.size() ; i++)
        {
            Direction x = allDirections.get(i);
            if(x.match(direction)) {
                return x.follow();
            }

        }
        System.out.println("ERROR: " + direction + " is invalid direction.");
        return this;
    }

    public void display()
    {
        System.out.println("Current Place: " + name);
        System.out.println(description);

        if(allArtifacts.size() == 0)
        {
            System.out.println("[ No Artifacts here ] \n");
        }


        else{
            System.out.println("All Artifacts:");
            String allNames = "";

            for (Artifact i : allArtifacts)
            	allNames += i.name() + ", ";
            allNames = allNames.substring(0, allNames.length() - 2);
            System.out.println("[ " + allNames + " ] \n");
          }
    }

    public Artifact getArtifact(String x)
    {
    	// TODO with Mike
        for(int i = 0; i < allArtifacts.size(); i++){
            if(allArtifacts.get(i).match(x))
                return allArtifacts.remove(i);
        }

        System.out.println("Not the right artifact");
        return null;
    }

    public void print()
    {
        System.out.println("This Place : " + name);
        System.out.println("-------------------------------");
        System.out.println("Place ID: " + ID);
        System.out.println("Place Name: " + name);
        System.out.println("Place Description " + description);

        for(int i = 0; i < allDirections.size() ; i++)
        {
            System.out.println("-----------------------------------");
            allDirections.get(i).print();
        }

        System.out.println("All Artifacts:");
        for(int j = 0; j < allArtifacts.size() ; j++) //hw2
        {
            System.out.println("-----------------------------------");
            allArtifacts.get(j).print();
        }

        System.out.println("END OF " + name);

    }

    public static void printAll()
    {
        for(Place x : allPlacesHM.values())
        {
            x.print();
        }
    }

    // ADDED by Jack Delaney to support matching objects
    public boolean match(String name)
    {
    	return this.name.equalsIgnoreCase(name);
    }

    public boolean match(Place p)
    {
    	return match(p.name);
    }

	public String getRandomArtifactName() {
		if (allArtifacts.size() < 1)
			return "";
		return allArtifacts.get(new Random().nextInt(allArtifacts.size())).name();
	}

	public Character getRandomCharacter(Character exception)
	{
		ArrayList<Character> copy = new ArrayList<Character>();
		for (Character c : allCharacters)
			if (!c.match(exception))
				copy.add(c);
		
		if (copy.size() < 1)
			return null;
		
		return copy.get(new Random().nextInt(copy.size()));
	}

}
