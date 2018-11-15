import java.util.*;
import java.io.*;

public class Bag extends Artifact {

	public Bag(Scanner sc, int PlaceID)
	{
		super(sc, 5, PlaceID);
	}
	
	@Override
	public void use(Character c)
	{
		//increase capacity
		//c.increaseCapacity()
		//remove bag from inventory and game
		this.increaseCapacity(c);
		c.removeArtByID(this.getID());
		c.currCap = c.currCap - this.getMobility();
	}
}
