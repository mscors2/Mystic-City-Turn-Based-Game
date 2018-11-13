/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part IV
 */

/*
 * This is unfortunately required because of Bell but useless in terms of design efficiency
 *  and should be designed differently since most projects will only have Player/NPC anyway without any more inheritance
 */
public interface DecisionMaker 
{
	
	/*
	 * Declares a single public method, getMove( Character, Place ), whose job
	 * is to determine the next move to make for the given Character in the given Place
	 */
	public Move getMove(Character character, Place place);
}
