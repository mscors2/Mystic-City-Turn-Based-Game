/*
 * Name: 	Jack Delaney
 * NetID:   jdelan7
 * Class: 	CS 342 (Software Design)
 * ProjecT: Term Project Part III
 */

import java.io.*;
import java.util.*;

public class GameTester 
{
	public static int nPlayers;
	
	public static void main(String[] args)
	{
		// Print the basics
		System.out.println("Names: Jack Delaney, Vanson Ho, Michael Scorsolini");
		System.out.println();
		
		// first handle argument input
		nPlayers = -999;
		Scanner sc = new Scanner(System.in);
		String filename = "";
		Scanner fileScanner = new Scanner(System.in);
		if (args.length < 1)
		{
			System.out.println("*One attempt allowed, please input a valid .gdf filename (3.1, 4.0, 5.0)*");
			System.out.print(" >> ");
			
			// allow the user to type in a valid filename
			try
			{
				fileScanner = new Scanner(new File(sc.next()));
			}
			catch(FileNotFoundException e2)
			{
				e2.printStackTrace();
				System.err.print("*You done messed up, try again later*");
				System.exit(1);
			}
		}
		else
		{
			// Store valid file input
			filename = args[0];
			try
			{
				fileScanner = new Scanner(new File(filename));
			} 
			catch(FileNotFoundException e)
			{
				System.out.println("*One attempt allowed, please input a valid .gdf filename (3.1 and 4.0)*");
				System.out.print(" >> ");
				
				// allow the user to type in a valid filename
				try
				{
					fileScanner = new Scanner(new File(sc.next()));
				}
				catch(FileNotFoundException e2)
				{
					e2.printStackTrace();
					System.err.print("*You done messed up, try again later*");
					System.exit(1);
				}
			}
						
			// check for players loaded
			if (args.length > 1)
			{
				nPlayers = Integer.parseInt(args[1]);
			}
		}
		
		// play the game?
		System.out.println();
		Game myGame = new Game(fileScanner);
		myGame.play();
		
		sc.close();
	}
}
