import java.util.*;

public class Container extends Artifact {
	
	public Container(Scanner sc, int placeID)
	{
		super(sc, 0, placeID);
	}
	
	@Override
	public void use(Character c)
	{
		System.out.println("Container cannot be used");
	}
}
