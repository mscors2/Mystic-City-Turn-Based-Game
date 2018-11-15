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
			//wave wand increase health
			//create function in character class to increase health cap
			
			//remove from inventory
			//create a function to remove by id in character class
			
			
		}
		else
		{
			System.out.println("ERROR: Player must be in the\nPool of Enchantment room to wave wand");
			return;
		}
		
	}
	
}
