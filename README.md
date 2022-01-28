# LDTS_T04_G02 - Lonely Earth Invader
  
## GAME DESCRIPTION

The **Lonely Earth Invader** is a shooting text based game inspired by the game *Space Invaders* where you have to kill a wave of residents from that planet to win.

This project was developed by Ana Ramos (up201904969@edu.fe.up.pt), Gabriel Coelho (up201902223@edu.fc.up.pt) and Matias Vaz (up201900194@edu.fc.up.pt) for LDTS 2021-22.

## GAME FEATURES
### IMPLEMENTED FEATURES

- **Player Moves** - The game character will move to the left when the *Arrow Left* key is pressed and to the right when the *Arrow Right* key is pressed.
- **Player Shoots** - The game character will shoot a bullet when the *Arrow Up* key or the *Space Bar* key is pressed.
- **Monsters Move** - The enemies of the game move on its own, with a defined pattern.
- **Monsters Attack** - The enemies attack on their own, causing damage on the Player.
- **Player Attacks** - Following the previous feature, *Player Shooting*, the bullets that the character shoots now kill the enemies, once one of the bullets reaches one of the Monters.
- **Player's Lives Display** - The Player has 3 lives at the beginning of the game, losing one if the Player gets shot by a Monster. Also, the number of lives remaining are displayed on the bottom left corner of the screen.


<p align="center">
  <img src= "https://user-images.githubusercontent.com/93398637/149397642-7b15a90d-8aac-48a9-8eb0-64ee81e54654.png"
       width=570
       height=410
       />
</p>

### PLANNED FEATURES

- **Different Levels** - For now, the game only has one level. In the future, we plan to introduce more.
- **Different Monsters** - For now, the game only has one type of enemy. In the future, we plan to introduce more.
- **Menu** - A menu iniciates the app, to be able to choose to play from there. 

## DESIGN

### THE GAME SHOULD WAIT FOR INPUT BY THE USER AND ONLY STOP WHEN A COMMAND TO STOP IS EXECUTED
- **Motivation:** The game should run in a loop, constantly reading the input provided by the user (using the keyboard or mouse), updating the game to execute the actions pretended and redering it. It also should controll the time passage of each update. 
- **The Pattern:** The pattern that we decided to apply for this was **Game Loop**, which mainly describes the problem at hand. **Game Loop** is a pattern that establishes a program in a loop, constantly waiting for input, updating the internal status and then rendering it. 
- **Implementation:** We implemented this pattern using threads. *Link to code snipet with the implementation of this pattern: https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/ee91661b51821bb427a5833946f1612a291ce158/src/main/java/Game/Console.java#L90-L115*
- **Consequences:** The usage of this patterns has benefits such as:
    - Being able to keep the game running and change as soon as an input is detected.
    - The game refreshes and renders everytime an input is read.
    - The update of the game allows the movement of the objects in the game to continue with a defined velocity (time per frame).
### VARIOUS OBJECTS WITHIN THE GAME SHARE THE SAME METHODS OR CHARACTERISTICS
- **Motivation:** The game has a player, monsters and bullets. All of theese have similiar characteristics, for example, they all need an instance of the class `Position`, they all are defined by their `width`, `height`, `life`, etc. So, what we want is to define a super class that defines a generic object with those characterics, so that the classes can extend that super class to specify what makes them different.
- **The Pattern:** To resolve this, we implemented the **Factory Method** pattern, which 
- **Implementation:**
- **Consequences:**
### NEXT

## KNOWN CODE SMELLS AND REFACTORING SUGESTIONS

### LONG CLASS
The `Level` Class is a **Long Class**
> I have to change this but I need sugestions!

## TESTING
> **INSERT** Screenshot of coverage report **AND** Link to mutation testing report.
> 
## SELF-EVALUATION

<!---
> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:
- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.
**Example of one of such subsections**:
------
#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE
**Problem in Context**
There was a lot of scattered conditional logic when deciding how the KangarooBoy should behave when jumping, as the jumps should be different depending on the items that came to his possession during the game (an helix will alow him to fly, driking a potion will allow him to jump double the height, etc.). This is a violation of the **Single Responsability Principle**. We could concentrate all the conditional logic in the same method to circumscribe the issue to that one method but the **Single Responsability Principle** would still be violated.
**The Pattern**
We have applied the **State** pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].
**Implementation**
The following figure shows how the pattern’s roles were mapped to the application classes.
![img](https://www.fe.up.pt/~arestivo/page/img/examples/lpoo/state.svg)
These classes can be found in the following files:
- [Character](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/Character.java)
- [JumpAbilityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/JumpAbilityState.java)
- [DoubleJumpState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/DoubleJumpState.java)
- [HelicopterState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/HelicopterState.java)
- [IncreasedGravityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/IncreasedGravityState.java)
**Consequences**

The use of the State Pattern in the current design allows the following benefits:
- The several states that represent the character’s hability to jump become explicit in the code, instead of relying on a series of flags.
- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.
- There are now more classes and instances to manage, but still in a reasonable number.
#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS
> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.
**Example of such a subsection**:
------
#### DATA CLASS
The `PlatformSegment` class is a **Data Class**, as it contains only fields, and no behavior. This is problematic because […].
A way to improve the code would be to move the `isPlatformSegmentSolid()` method to the `PlatformSegment` class, as this logic is purely concerned with the `PlatformSegment` class.
### TESTING
- Screenshot of coverage report.
- Link to mutation testing report.
### SELF-EVALUATION
> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.
**Example**:
- John Doe: 40%
- Jane Doe: 60%
--->
