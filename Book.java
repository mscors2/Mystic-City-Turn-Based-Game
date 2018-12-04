import java.util.*;
import java.util.concurrent.TimeUnit;

public class Book extends Artifact {

	public Book(Scanner sc, int PlaceID)
	{
		super(sc, 2, PlaceID);
		
	}
	
	@Override
	public void use(Character c)
	{
		c.io.display("You decided to read the artifact:");
		//delay the game for reading
		try 
		{
			TimeUnit.SECONDS.sleep((long) 3);
			c.io.display("The Artifact reads: " + this.getDesc());
			return;
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
