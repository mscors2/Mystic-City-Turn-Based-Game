import java.util.*;

public class Potion extends Artifact {
	public Potion(Scanner sc, int PlaceID)
	{
		super(sc, 7, PlaceID);
	}
	
	public Potion(int id, String name, int value, int weight, String desc, int keyPat)
	{
		super(id, name, value, weight, desc, keyPat, 7);
	}
	
	@Override
	public void use(Character c)
	{
		//drink potion, obtain power up
		//remove from inventory
		//check to see if its a deadly potion used
		if(this.name().equals("POISON"))
		{
			c.io.display("YOU DRANK THE POISON!!! You died.");
			//kill off player
			c.health = 0;
			c.isAlive = false;
			Game.nPlayersAlive = Game.nPlayersAlive - 1;
			//drop any items they had on the floor in the current location
			for(Artifact a: c.artifacts)
			{
				c.current.addArtifact(a);
				c.artifacts.remove(a);
			}
			
			c.current.removeCharacter(c);
		}
		else 
		{
			//increase health cap
			c.incrHealthCap();
			//remove artifact
			c.removeArtByID(this.getID());
		}
	}
	
	public static Potion mix(Character c, Artifact p1, Artifact p2)
	{
		//must be in potion lab
		if(c.current.name.equals("Potions Lab"))
		{
			
			c.artifacts.remove(p1);
			c.artifacts.remove(p2);
			//combine this object with another potion
			// return result
			Potion MIXED = new Potion(-9999, "POISON", 3, 1, "This will kill you if you drink it.", 0);
			c.io.display("You just made poison out of the potions. Do not use it.");
			return MIXED;
		}
		else 
		{
			c.io.display("You must be in the potions room in order to mix potions");
			return null;
		}
	}
}
