import java.util.*;

public class Key extends Artifact {
	public Key(Scanner sc, int PlaceID)
	{
		super(sc, 6, PlaceID);
	}
	
	@Override
	public void use(Character c)
	{
		c.current.useKey(this);
	}
	
	
}
