import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Vanson on 11/14/2018.
 */
public class RockPaperScissorsRoom extends Place {

    RockPaperScissorsRoom(int id, String name, String description)
    {
        this.ID = id;
        this.name = name;
        this.description = description;

        allDirections = new ArrayList<Direction>();
        allArtifacts = new ArrayList<Artifact>();
        allCharacters = new ArrayList<Character>();

        // Add our instance to the global collection
        allPlacesHM.put(this.ID, this);

    }



    public void display()
    {
        List<Integer> keyList = new ArrayList<Integer>(Place.allPlacesHM.keySet());

        if(allCharacters.size() > 0) {
            for (Character i : allCharacters) {


                if (!(i.type().equalsIgnoreCase("Player"))) {

                    //bots get stuck in the rock paper scissors room
                }

                else
                {
                    System.out.println(i.name() + " has entered the room");
                    System.out.println("... \n...");

                    System.out.println("Anonymous voice:  Do you wanna play a game?");

                    System.out.println("Anonymous voice:  Lets play Rock Paper Scissors, you win, you get to live" +
                                       " another day ");
                    System.out.println("Anonymous voice:  You Lose, you DIE.  Choose wisely :)");

                    boolean liveOrDie = rockPaperScissors();  //true = live

                    if(liveOrDie == true || liveOrDie == false)
                    {
                        System.out.println("Anonymous voice:  You win this time... as a gift you get to go to the portal");
                        i.setPlace(getPlaceByID(321));
                        i.current.display();
                        return;

                    }
                    else
                    {
                        System.out.println("Anonymous voice:  YOU LOSE YOU DIE. BYE");
                        i.kill();
                        i.setPlace(getPlaceByID(0));
                    }
                }

            }
        }




    }

    //plays RPS
    public boolean rockPaperScissors()
    {
        Scanner input = new Scanner(System.in);

        Random rand =  new Random();

        int randRPS = rand.nextInt(3) + 1;  // 1 = rock 2 = paper 3 = scissors
        int userRPS = 0;
        boolean userWon = false;

        System.out.println("Please choose Rock, Paper or Scissors");
        String myInput = input.nextLine();

        if(myInput.equalsIgnoreCase("rock"))
            userRPS = 1;
        else if (myInput.equalsIgnoreCase("paper"))
            userRPS = 2;
        else if (myInput.equalsIgnoreCase("scissors"))
            userRPS = 3;
        else
        {
            System.out.println("Learn to spell N0Ob.  Try again");
            rockPaperScissors();  //  recursion!
        }

        // user is rock
        if(userRPS == 1)
        {
            if(randRPS == 1)
            {
                System.out.println("Anonymous voice:   I chose Rock! Tie! Lets play again!");
                rockPaperScissors();
            }
            if(randRPS == 2)
            {
                System.out.println("Anonymous voice:    I choose Paper!");
                userWon = false;
            }
            if(randRPS == 3)
            {
                System.out.println("Anonymous voice:   I choose Scissors!");
                userWon = true;
            }
        }

        //user is paper
        if(userRPS == 2)
        {
            if(randRPS == 2)
            {
                System.out.println("Anonymous voice:   I choose Paper! Tie! Lets play again!");
                rockPaperScissors();
            }
            if(randRPS == 1)
            {
                System.out.println("Anonymous voice:   I choose Scissors!");
                userWon = false;
            }
            if(randRPS == 3)
            {
                System.out.println("Anonymous voice:  I choose Rock!");
                userWon = true;
            }
        }

        // user is scissors
        if(userRPS == 3)
        {
            if(randRPS == 3)
            {
                System.out.println("Anonymous voice:   I choose Scissors! Tie! Lets play again!");
                rockPaperScissors();
            }
            if(randRPS == 1)
            {
                System.out.println("Anonymous voice:   I choose Rock!");
                userWon = false;
            }
            if(randRPS == 2)
            {
                System.out.println("Anonymous voice:  I choose Paper!");
                userWon = true;
            }
        }

        return userWon;



    }


}
