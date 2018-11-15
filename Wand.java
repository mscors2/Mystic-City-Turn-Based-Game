import java.util.*;
import java.io.*;

public class Wand extends Artifact
{
	public Wand(Scanner sc, int PlaceID)
	{
		super(sc, 1, PlaceID);
	}
	
	public void use(Character c)
	{
		//check to see if player is in pool of enchantment to wave wand
		Place p = c.current;
		if(p.name().equals("Pool of Enchantment"))
		{
			//wave wand increase attack
			c.incrAttack();
			
			System.out.println("You increased your attack damage by 1! Too bad the wand disinegrated :( ");
			//remove artifact from inventory
			c.removeArtByID(this.getID());
			
			
			
		}
		else
		{
			System.out.println("ERROR: Player must be in the\nPool of Enchantment room to wave wand");
			return;
		}
		
	}
	
}
