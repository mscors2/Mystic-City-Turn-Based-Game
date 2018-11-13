import java.util.Scanner;

/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part IV
 */

public class CleanLineScanner 
{
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Fields */
	
	private static String line;
	
	/* ----------------------------------------------------------------------------------------------------------- */
	/* Main Methods */
	
	/*
	 * Returns a non-empty line from the given Scanner
	 */
	public static String getCleanLine(Scanner sc)
	{
		// Read until valid or end
		line = "";
		while (sc.hasNext())
		{
			// Retrieve line by line
			line = sc.nextLine().trim();
			String[] arr = line.split("\\s+");
			
			// Skip empty lines or comments
			if (line.isEmpty() || arr[0].contains("//"))
				continue;
			else
				break;
		}
		
		// We found a valid line
		return line.split("//")[0];
	}
}
