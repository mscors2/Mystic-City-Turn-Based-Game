import java.util.*;
import java.io.*;

public class Container extends Artifact {
	
	public Container(Scanner sc, int placeID)
	{
		super(sc, 0, placeID);
	}
	
	@Override
	public void use(Character c)
	{
		//cant use containers to store things when in use
	}
	
}
