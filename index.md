# Documentation

This page contains all the documentation associated for the **Text adventure** project.

Please note that, due to the large size of some diagrams, sometimes you'll need to use the zoom function of your browser
of choice.

## Index of contents

Below is reported a clickable list of all the section of this page:

1) [Introduction](#Introduction) <br>
2) [Graphic interface and movement control](#Graphic-interface-and-movement-control) <br>
3) [The Game](#The-Game) <br>
4) [Diagrams](#Diagrams)<br>
5) [System Sequence Diagram](#System-Sequence-Diagram) <br>
6) [Internal Sequence Diagrams](#Internal-Sequence-Diagrams) <br>
7) [Code](#Code) <br>
8) [System Test Document](#System-Test-Document) <br>
9) [Unit Test Report](#Unit-Test-Report) <br>
10) [Manual](#Manual)
11) [Contributors](#Contributors) 


---

# Introduction
“Extraction” is a text adventure that comes to life from the renowned Ubisoft video game, “**Rainbow Six Extraction**”. In a world upset by an alien parasitic invasion, chaos and destruction reign supreme. You, a valiant warrior of the elite Rainbow team, have been selected for a mission of vital importance. Your task is to infiltrate a building, confront and defeat the archons that hinder your path, and save your imprisoned companion. Your mission is dangerous, but the hope of a better world rests on your shoulders.

---

# Graphic interface and movement control
The graphic interface was created using a framework known as “Swing”.
Java Swing is a part of Java Foundation Classes (JFC) used for building graphical user interfaces (GUIs) in Java applications. It provides a set of "lightweight" (all-Java language) components that, unlike the Abstract Window Toolkit (AWT) components, do not rely on native peers (platform-specific code). Swing components are platform-independent and more flexible, offering a richer set of GUI components than AWT.

The interface displays the building’s map, highlighting the different rooms that the player can visit. At the bottom of the interface, there are four buttons: North, South, East, and West, which guide the player’s movement. If the chosen direction does not lead to a room, the movement is prevented. Each room can host an alien and a variety of objects that the player can eventually collect. All this information is displayed on the screen.

---

# The Game
The game is set in a building overrun by an alien parasite. A member of the Rainbow team has been taken hostage by these aliens, and your primary goal will be to locate and rescue him, exiting the building. During your mission, you may encounter some of these aliens, whom you will have to fight or from whom you will have to escape. If you lose all your life during a fight, the game will end.

Throughout the game, you will have the opportunity to:

- Explore various rooms where you can find useful items to collect.
- Be mindful of the number of items you can carry and the total weight you can bear. If necessary, you can remove unnecessary items from your inventory.
- Save the game at any point. From the initial menu, you will also have the option to load a previously saved game.


---
## Diagrams
In this section, the main diagrams required by the assignment are provided.

### Domain Model
The Domain Model represents the main concepts of the system and the relationships between them. It is a high-level representation of the system's structure and is used to define the scope of the system.
Here are defined the main classes of the system, such as Player, Room, Item, and Alien, their attributes, operations, and the relationships between them
![DomainModel](resources/documents/Domain-Model-Diagram.png)

## Design class model
A Design Class Model is a detailed, structured representation of the software system's classes, interfaces, and their relationships.
For readability reasons, we decided to split the Design Class Model into thee parts: Core, Graphics, and Upload.

### Design Class Model Core
This part of the Design Class Model represents the core classes of the system, such as Player, Room, Item, Alien, and the main controller, Game.
![DesignClassCore](resources/documents/Design-Class-Model-Core.png)

### Design Class Model Graphics
This part of the Design Class Model represents the classes related to the graphic interface.
![DesignClassGraphics](resources/documents/Design-Class-Model-Graphics-Design_Class_Model.png)

### Design-Class-Model-Upload
This part of the Design Class Model represents the classes related to the upload of the game's state.
![DesignClassGraphics](resources/documents/Design-Class-Model-Graphics-Design_Class_Model.png)

---

## System Sequence Diagram
This diagram describes the sequence of interactions between external actors and a system for a particular scenario of a use case.

![SSD](resources/documents/System-Sequence-Diagram-System_Sequence_Diagram.png)

---

## Internal Sequence Diagrams
Unlike the System Sequence Diagram, the Internal Sequence Diagram describes the interactions between internal components or objects within a system for a specific scenario of a use case. Here are some of the main ISD:

### Attack
This diagram describes the sequence of interactions between the player and the alien during an attack.
![Attack](resources/documents/Internal-Sequence-Diagram/Attack-Attack.png)

### Collect Item/Add Item
This diagram describes the sequence of interactions between the player and the item during the collection.
![CollectItem](resources/documents/Internal-Sequence-Diagram/Collect-Item-Add_Item.png)

### Defend
This diagram describes the sequence of interactions between the player and the alien during a defense.
![Defend](resources/documents/Internal-Sequence-Diagram/Defend-Defend.png)

### Fight
This diagram describes the sequence of interactions between the player and the alien during a fight.
![Fight](resources/documents/Internal-Sequence-Diagram/Fight-Fight.png)

### Heal
This diagram describes the sequence of interactions between the player and the medikit during a heal.
![Heal](resources/documents/Internal-Sequence-Diagram/Heal-Heal.png)

### Throw Item
This diagram describes the sequence of interactions between the player and the alien during a throw item.
![ThrowItem](resources/documents/Internal-Sequence-Diagram/Throw-Item-Throw_Item.png)

### Throw TNT
This diagram describes the sequence of interactions between the player and the alien during a throw TNT.
![ThrowTNT](resources/documents/Internal-Sequence-Diagram/ThrowTNT.png)



---

## Code

The entire **code** of this project is hosted on GitHub and the repository can be found at [this page](https://github.com/EDIDS/EDIDS_Proj).

[**Back to Index**](#index-of-contents)

---

## System Test Document

You can find the system test document at [this page](resources/documents/System-test-document.pdf).

[**Back to Index**](#index-of-contents)

---

## Unit Test Report

The auto-generated JUnit test report can be found at [this page](/resources/UnitTestReport/surefire-report.html).

[**Back to Index**](#index-of-contents)

---

## Manual

If you want to consult the **Manual** click [here](manual.md). 

[**Back to Index**](#index-of-contents)

---

## Contributors

This project was carried out by:

|     **Name**      | **Badge** |
| :---------------: | --------- |
| Alessandro Vulcu  | 2041835   |
|  Gabriele Zolla   |  2066629  |
| Giorgio Prizzon | 2066587   |
| Gabriele Posenato |   2066668   |


[**Back to Index**](#index-of-contents)
