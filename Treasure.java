import java.util.*;
import java.io.*;

public class Treasure extends Artifact {
	
	public Treasure(Scanner sc, int PlaceID)
	{
		super(sc, 3, PlaceID);
	}
	
	@Override
	public void use(Character c)
	{
		System.out.println("You can't use treasure! You should collect as much as possible!");
		
	}
}
