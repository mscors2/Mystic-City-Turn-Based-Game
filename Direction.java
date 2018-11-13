import java.util.*;

//Vanson Ho
//vho6

public class Direction {


    private int ID;
    private Place from;
    private Place to;
    private DirType dir;
    private boolean lock;  // true is lock, false is unlock
    private int lockPattern;

    public Direction(int ID, Place from, Place to, String dir, int newLockPattern) {
        this.ID = ID;
        this.from = from;
        this.to = to;
        this.dir = DirType.valueOf(dir);
        this.lock = false;
        this.lockPattern = newLockPattern;
    }

    Direction(Scanner myScanner, double version)  //constructor with scanner for gdf files
    {
    	// TODO
    	// Version support
		if (version < 3.1)
		{
			System.err.println("Direction->scanner constructor: \n"
					+ "Direction building is from version 3.1 and up \n");
			return;
		}
		else if (version == 3.1 || version == 4.0 || version == 5.0)
		{
			// Setup .gdf parsing
			String line = "";
			String[] arr = new String[0];
			
			// ID source direction destination lockPattern
			// ID -> 			unique integer in the range of 32-bit signed ints
			// src + dst -> 	PlaceIDs (< 0 = locked, 1 = EXIT, 0 = NOWHERE)
			// direction ->		must be one of "N", "E", etc.
			// lockPattern ->	32-bit int (0 = unchangeable)
			line = CleanLineScanner.getCleanLine(myScanner);
			arr = line.split("\\s+");
			
			// Error check
			if (arr.length < 5)
			{
				System.err.println("GDF->error: \n"
						+ "Direction should have at least five strings in the first line \n");
				return;
			}

			// Assign
			this.ID = Integer.parseInt(arr[0]);
			this.from = Place.getPlaceByID(Math.abs(Integer.parseInt(arr[1])));
			this.to = Place.getPlaceByID(Math.abs(Integer.parseInt(arr[3])));
			this.dir = DirType.valueOf(arr[2]);
			this.lock = Integer.parseInt(arr[3]) < 0;
			this.lockPattern = Integer.parseInt(arr[4]);
			
			// Add our instance to corresponding collections
			this.from.addDirection(this);
		}
		else
		{
			// Invalid version
			System.err.println("Direction->scanner constructor: \n"
					+ "Direction building is only supported by version 3.1, 4.0 and 5.0");
			return;
		}

    }

    public void useKey(Artifact x)  // uses key with restrictions on toggling lock
    {
    	// TODO WITH MICHAEL FOR ARTIFACT
        if(x.match(lockPattern))
        {
            if(!x.isKey())
            {
                System.out.println("This key cannot be used.");
                return;
            }
            else if(lockPattern == 0)
            {
                System.out.println("Lock status may not be changed!");
                return;
            }
            else if(lock) {
                System.out.println("Unlocking direction to " + to.name());
                lock = false;
                return;
            }
            else {
                System.out.println("Locking direction to " + to.name());
                lock = true;
                return;
            }

        }
        else {
            System.out.println("The key does not match the lock to " + to.name());
            return;
        }
    }

    public boolean match(String s)
    {
        if (dir.match(s))
            return true;

        return false;
    }
    
    public void lock()
    {
        lock = true;
    }

    public void unlock()
    {
        lock = false;
    }

    public boolean isLocked()
    {
        if(lock)
            return true;

        return false;
    }

    public Place follow()
    {
        if(this.isLocked())
        {
            System.out.println("Sorry! This is locked.");
            return this.from;
        }

        System.out.println("Following direction "+ dir.toString());
        return this.to;
    }

    public void print()
    {
        System.out.println("Direction ID: " + ID);
        System.out.println("Came From: " + from.name());
        System.out.println("Goes To: " + to.name());
        System.out.println("Direction = " + dir.toString());
        System.out.println("Lock = " + lock);
    }

    // ADDED by Jack:
    public boolean match(Direction d)
    {
    	return this.dir.toString().equalsIgnoreCase(d.dir.toString());
    }
    
    private enum DirType  //ENUM subclass for directions
    {
        NONE("None", "None"),
        N("North", "N"),
        S("South", "S"),
        E("East", "E"),
        W("West", "W"),
        U("Up", "U"),
        D("Down", "D"),
        NE("Northeast", "NE"),
        NW("Northwest", "NW"),
        SE("Southeast", "SE"),
        SW("Southwest", "SW"),
        NNW("North-Northwest", "NNW"),
        NNE("North-Northeast", "NNE"),
        ENE("East-Northeast", "ENE"),
        WNW("West-Northwest", "WNW"),
        ESE("East-Southeast", "ESE"),
        WSW("West-Southwest", "WSW"),
        SSE("South-Southeast", "SSE"),
        SSW("South-Southwest", "SSW");

        private String text;
        private String abbreviation;

        DirType(String direction, String abr) {
            text = direction;
            abbreviation = abr;
        }


        public String toString()
        {
            return text;
        }

        public boolean match(String x)
        {
            if(x.equalsIgnoreCase(this.abbreviation))
                return true;
            else if(x.equalsIgnoreCase(this.text))
                return true;
            else
                return false;
        }


    } //end of DirType

}// end of class
