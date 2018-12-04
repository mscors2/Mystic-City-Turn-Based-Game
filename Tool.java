import java.util.*;

public class Tool extends Artifact {

	public Tool(Scanner sc, int PlaceID)
	{
		super(sc, 4, PlaceID);
	}
	
	@Override
	public void use(Character c)
	{
		//tools break after use will be implemented for buliding possibly later on
		System.out.println("You broke the " + this.name() + "!");
		c.removeArtByID(this.getID());
	}
}
