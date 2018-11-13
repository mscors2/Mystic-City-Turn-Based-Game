/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part III
 */

import java.util.*;

public class KeyboardScanner 
{	
	// fields
	private static Scanner sc;
	
	// constructors
	public KeyboardScanner()
	{
		sc = new Scanner(System.in);
	}
	
	// methods
	public Scanner getKeyboardScanner()
	{
		return sc;
	}
}
