Members:	Vanson Ho, Jack Delaney, Michael Scorsolini
Group #:	12
Proj #:		5

Notes:
	We ALL took a LATE DAY on this assignment (only one which should be penalty free)

GUI_1 = Jack Delaney    (jdelan7)
GUI_2 = Vanson Ho       (vho6)
GUI_3 = Michael Scorsolini (mscors2)

//--------------------------------------------------------------------------------------//

HOW TO RUN (for terminal users):
	1) Open up you OS specific terminal
	2) Go to the folder with all my files in it (.java, .gdf, makefile, .txt)
	3) type "make" 
	4) type "java GameTester" (There are NO required arguments for the main method)
	5) type "test2.gdf"
	6) Enjoy!
	7) When you're done, type "make clean"
//--------------------------------------------------------------------------------------//

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

//--------------------------------------------------------------------------------------//

Jack Delaney
jdelan7
SECTION Character, AI, UI

Extensions:
	+ Killer.java
	+ Joker.java
	+ Wizard.java
	+ SpecialAI.java

Note: Because Artifacts and Places were being extended to interact with the Player more, my extensions were only for NPC's with very behavioral AI's.
	
Killer.java: These specific NPC type (not limited to one NPC) will always prioritize killing a nearby Character (at a random Character child other than itself - not promoting suicide) before making a random move which IS limited to only Moving because it's main focus is to solely by "aggresive".

Joker.java: This character is only interested in moving from Place to Place and picking up Artifacts off the ground. Prior to making these types of Actions, everytime it gets a Turn it will attempt to steal ONE Artifact from another Character (other than itself of course) who unluckily shares the same Place as a Joker type. They also have a more "hoarder" like behavior as they don't have the ability to DROP artifacts, making them extremely unhelpful and sometimes game breaking (NOT the same as crashing).

Wizard.java: Only interested in messing with Artifacts with the additional ability to teleport anywhere in the Game, choosing it at random to scout out more Artifacts. 

SpecialAI.java: This was a more enhanced AI/desicionMaker that would restrict specific Types of NPCs to specific Moves that they could perform.
		
//--------------------------------------------------------------------------------------//
		
Michael Scorsolini
mscors2
Sections Character, Artifact, Move

All the extensions I made are as follows: Artifacts that have types (this is several java files like Food, Container, etc) and their uses have different features that override the parent class, some artifacts can only be used by certain commands ex: food must be eaten, some artifacts and commands can only be used in certain places like mixing can only be done in the potion store room, wands can only be waved at the pool of enchantment. 

With the artifacts having unique features I had to add several things to the character class to implement those features. These include: carrying capacity, use of bags to gain more capacity, use potions to raise max health, food to restore health, how to detect when a character should die and be removed from the game, Attack power which is raised by using wands. Also added potion mixing that creates a poison potion that kills the player if used. If a player dies I made sure their loot was dropped before removing them from the game

Extra commands that were added with move class include ATTACK which attacks all players in the room and subtracts their health based on damage, MIX for mixing potions in the potions lab, EAT for eating food to restore health, READ for reading books/scrolls. Again only certain commands can be used for certain artifacts and I have built in error checking for that.

Game ends if there is only one player standing as for the data file test2.gdf. This must be used in order to implement the extra features because we had to modify this file in order to implement them. Some additional items were added to each section in this update as well.
