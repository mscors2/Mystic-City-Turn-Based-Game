README:
Vanson (vho6)
I was in charge of the place section along with the direction class.  We collaborated at first to change the constructors so they would be simliar and work smoothly.
In the Place class we added a few methods:
1. public Character getRandomCharacter()
	- this returns a random Char that is currently in this place.
2. public String getRandomArtifactName()
	-We use this in the AI and special AI class so the AI can grab an artifact.
3. match()
	-We added match functions just to make the code more readable.
4. Constructor
	-We add implementation of two descendents.  I chose to go out of the box with the places
Portal Class
- This class is sort of a secret room.  It is located when someone is in the entrance hall(for easier testing purposes) and enters "go up".  This place allows give you a list of all available places and lets you choose where you want to go by entering the name.
- If a bot is sent to this place, it is sent to the treasure store room .  I put the hashmap into a key set in order to get change the location.
- If a player enters the name incorrectly, it gets sent where the bot is sent.  This is not the best implementation right now but this depends greatly on the gdf file.
RockPaperScissors Class
- Extends from the Place class
- This class forces the player to play RPS with a bot.  If the player wins, it gets teleported to the portal class as a reward.
- If the player loses, it dies.  We then send the dead player to nowhere and go to the next character.  Harsh isn't it?
- If a bot goes to this room, we don't have it play rock paper scizzors and just sits in the room.  It  can then move into different rooms provided the RPS room has directions out of it.
NOTE:  We alter both of these classes by changing how display works, as that is always called before every move in the game.  This makes it easier to manipulate how the game is player.
The Direction Class was basically left unchanged.





