# LDTS_T04_G02 - Lonely Earth Invader
  
## GAME DESCRIPTION

The **Lonely Earth Invader** is a shooting text based game inspired by the game *Space Invaders* where you have to kill a wave of enemies to win.

**Objective:**
To win, you (the player), on your little spaceship, have to kill all the green enemies that appear on the screen. For that you can shoot bullets that effectively kill the monster that make contact with it (only if you have good aiming skills). But be careful! The monsters can also shoot bullets at you. Besides, you only have 3 lives, and if you run out of lives, you loose. You also loose if you let the enemies reach you, so be quick to kill them all.

This project was developed by Ana Ramos (up201904969@edu.fe.up.pt), Gabriel Coelho (up201902223@edu.fc.up.pt) and Matias Vaz (up201900194@edu.fc.up.pt) for LDTS 2021-22.

## GAME FEATURES
### IMPLEMENTED FEATURES

- **Menu** - A menu iniciates the application where the user can choose to play from there, or to read the instructions, and lastly to exit the application.
<p align="center">
  <img src= "https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/main/images/menu1.jpg"
       width=407
       height=293
       />
  <img src= "https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/main/images/menu2.jpg"
       width=407
       height=293
       />
  </p>

- **Instructions** - From the menu of the game, the user is able to choose to see the instructions by pressing *Enter* when the **Instructions** are displayed green.
<p align="center">
  <img src= "https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/main/images/instructions.jpg"
       width=407
       height=293
       />
  </p>
  
- **Player Moves** - The game character will move to the left when the *Arrow Left* key is pressed and to the right when the *Arrow Right* key is pressed.
- **Player Shoots** - The game character will shoot a bullet when the *Arrow Up* key or the *Space Bar* key is pressed.
- **Monsters Move** - The enemies of the game move on its own, with a defined pattern.
- **Monsters Attack** - The enemies attack on their own, causing damage on the Player.
- **Player Attacks** - Following the previous feature, *Player Shooting*, the bullets that the character shoots now kill the enemies, once one of the bullets reaches one of the Monters.
- **Player's Lives Display** - The Player has 3 lives at the beginning of the game, losing one if the Player gets shot by a Monster. Also, the number of lives remaining are displayed on the bottom left corner of the screen.

<p align="center">
  <img src= "https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/main/images/game.jpg"
       width=407
       height=293
       />
  </p>
  
- **Game Over/Won Display** - When the Player looses the game (by running out of lives or letting the monsters reach him) the game stops and a screen with the words `GAME OVER` will appear. On the other hand, if the Player wins, the screen that will be displayed will have the words `YOU WIN!`.
<p align="center">
  <img src= "https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/main/images/gameover.jpg"
       width=407
       height=293
       />
  <img src= "https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/main/images/youwin.jpg"
       width=407
       height=293
       />
  </p>


### PLANNED FEATURES

- **Different Levels** - For now, the game only has one level. In the future, we plan to introduce more.
- **Different Monsters** - For now, the game only has one type of enemy. In the future, we plan to introduce more. 

## DESIGN

### THE GAME SHOULD WAIT FOR INPUT BY THE USER AND ONLY STOP WHEN A COMMAND TO STOP IS EXECUTED

- **Motivation:** The game should run in a loop, constantly reading the input provided by the user (using the keyboard), updating the game to execute the actions pretended and redering it. It also should controll the time passage of each update. 

- **The Pattern:** The pattern that we decided to apply for this was **Game Loop**, which mainly describes the problem at hand. **Game Loop** is a pattern that establishes a program in a loop, constantly waiting for input, updating the internal status and then rendering it. 

- **Implementation:** We implemented this pattern using threads. *Link to code snipet with the implementation of this pattern: https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/eb4c4ef417408859d8c19f906a26b06e2443be01/src/main/java/ldts/game/Console.java#L91-L129*

- **Consequences:** The usage of this patterns has benefits such as:
    - Being able to keep the game running and change as soon as a key is pressed.
    - The game refreshes and renders everytime an input is read.
    - The update of the game allows the movement of the objects in the game to continue with a defined velocity (time per frame).


### VARIOUS OBJECTS WITHIN THE GAME SHARE THE SAME METHODS OR CHARACTERISTICS

- **Motivation:** The game has a player, monsters and bullets. All of theese have similiar characteristics, for example, they all need an instance of the class `Position`, they all are defined by their `width`, `height`, `life`, etc. So, what we want is to create a superclass that defines a generic object with those characterics, so that the classes can extend that superclass to specify what makes them different.

- **The Pattern:** To resolve this, we resorted to the **Factory Method** pattern, which states that you should create an interface for creating objects in a superclass, but allowing subclasses to alter the type of objects that will be created.

- **Implementation:** Although we didn't create an interface in this case, we did create a superclass `GameObject` and subclasses such as `Player`, `Monster` and `Bullet`. All of the previous mentioned classes extend the superclass `GameObject`.

<p align="center">
  <img src= "https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/main/images/fabric.jpg"
       width=407
       height=293
       />
  </p>

## KNOWN CODE SMELLS AND REFACTORING SUGESTIONS

### LONG CLASS
The `Console` Class is a **Long Class**

We should have divided what the Class does in more classes.

## TESTING
Coverage report in html: https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0402/blob/main/src/main/resources/index.html

## SELF-EVALUATION

Ana - 45%
Matias - 35%
Gabriel - 10%
