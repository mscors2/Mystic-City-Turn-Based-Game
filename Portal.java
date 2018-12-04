import java.util.ArrayList;
import java.util.List;

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
                    i.setPlace(Place.getPlaceByID(23));
                    i.io.display("Moving BOT " + i.name() + Place.getPlaceByID(23).name());

                }
                else
                {
                    i.io.display("Welcome to the special portal " + name + "!");
                    i.io.display("These are your room options: \n\n");

                    for(int x = 0 ; x < keyList.size(); x++)
                    {
                        i.io.display("Name: " + Place.getPlaceByID(keyList.get(x)).name());
                    }

                    i.io.display("Please enter a room name to teleport to:");

                    String myInput = i.io.getLine();

                    for(int j = 0; j < keyList.size(); j++)
                    {
                        if(myInput.equalsIgnoreCase(Place.getPlaceByID(keyList.get(j)).name()))
                        {
                            i.setPlace(Place.getPlaceByID(keyList.get(j)));
                            i.io.display("Success!! Moving " + i.name() + " to "
                                                + Place.getPlaceByID(keyList.get(j)).name());
                            i.current.display();
                            return;
                        }
                    }

                    i.io.display("You didn't enter a valid room!");
                    i.setPlace(Place.getPlaceByID(keyList.get(keyList.size() - 2)));
                    i.io.display("Moving " + i.name() + " to " +  Place.getPlaceByID(keyList.get(keyList.size() - 2)).name());
                    i.current.display();
                    return;


                }
                i.io.display("***Continue Move***");
            }
        }
    }




}