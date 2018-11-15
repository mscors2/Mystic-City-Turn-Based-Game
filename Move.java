
public class Move 
{
	// fields
	private MoveType type;
	private String argument;
	
	// constructors
	public Move()
	{
		type = MoveType.PASS;
		argument = "";
	}
	
	public Move(MoveType type, String argument)
	{
		this.type = type;
		this.argument = argument;
	}
	
	/*
	 * Enum values for Move (a list of move options)
	 */
	public enum MoveType
	{
		QUIT, LOOK, INVENTORY, GO, GET, DROP, USE, PASS, MIX, ATTACK , READ, EAT;
	}
	
	/*
	 * ADDED to support Character.makeMove()
	 */
	public MoveType type()
	{
		return type;
	}
	
	public String argument()
	{
		return argument;
	}

	/*
	 * A restricted move-set just for AI Characters
	 */
	public static MoveType[] getAIMoves() 
	{
		// Set up and fill array
		MoveType[] arr = {MoveType.GO, MoveType.GET, MoveType.DROP, MoveType.USE, MoveType.PASS, MoveType.MIX, MoveType.ATTACK, MoveType.READ, MoveType.EAT};
		return arr;
	}
}