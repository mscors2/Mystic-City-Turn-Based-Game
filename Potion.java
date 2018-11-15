import java.util.*;
import java.io.*;

public class Potion extends Artifact {
	public Potion(Scanner sc, int PlaceID)
	{
		super(sc, 7, PlaceID);
	}
	
	@Override
	public void use(Character c)
	{
		//drink potion, obtain power up
		//remove from inventory
		//check to see if its a deadly potion used
	}
	
	public Potion mix(Potion p)
	{
		//combine this object with another potion
		// return result
		
		return null;
	}
}
