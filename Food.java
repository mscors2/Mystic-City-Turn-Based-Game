import java.util.*;

public class Food extends Artifact {
	public Food(Scanner sc, int id)
	{
		super(sc, 8, id);
	}
	
	@Override
	public void use(Character c)
	{
		//check if health is full
		 if(c.isHealthFull())
		 {
			 //health is full
			 System.out.println("Your Health is already full. Cannot restore past capacity!");
			 return;
		 }
		 
		//consume food and recharge health
		//remove from inventory
		c.restoreHealth();
		 
		c.removeArtByID(this.getID());
	}
}
