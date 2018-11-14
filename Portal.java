import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Vanson on 11/13/2018.
 */
public class Portal extends Place {


    Portal(int id, String name, String description)
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

        if(allCharacters.size() > 0)
        {
            for(Character i : allCharacters)
            {

                if(!(i.type().equalsIgnoreCase("Player")))
                {
                    i.setPlace(Place.getPlaceByID(keyList.get(keyList.size() - 1)));
                    System.out.println("Moving " + i.name() + Place.getPlaceByID(keyList.get(keyList.size() - 1)).name());

                }
                else
                {
                    System.out.println("Welcome to the special portal!");
                    System.out.println("These are your room options: \n\n");

                    for(int x = 0 ; x < keyList.size(); x++)
                    {
                        System.out.println("Name: " + Place.getPlaceByID(keyList.get(x)).name());
                    }

                    System.out.println("Please enter a room name to teleport to:");

                    Scanner input = new Scanner(System.in);
                    String myInput = input.nextLine();

                    for(int j = 0; j < keyList.size(); j++)
                    {
                        if(myInput.equalsIgnoreCase(Place.getPlaceByID(keyList.get(j)).name()))
                        {
                            i.setPlace(Place.getPlaceByID(keyList.get(j)));
                            System.out.println("Success!! Moving " + i.name() + " to "
                                                + Place.getPlaceByID(keyList.get(j)).name());
                            return;
                        }
                    }

                    System.out.println("You didn't enter a valid room!");
                    i.setPlace(Place.getPlaceByID(keyList.get(keyList.size() - 1)));
                    System.out.println("Moving " + i.name() + " to " +  Place.getPlaceByID(keyList.get(keyList.size() - 1)).name());


                }
                System.out.println("***Continue Move***");
            }
        }
    }




}