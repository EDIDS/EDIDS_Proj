# Extraction - Text adventure
### Introduction
“Extraction” is a text adventure that comes to life from the renowned Ubisoft video game, “**Rainbow Six Extraction**”. In a world upset by an alien parasitic invasion, chaos and destruction reign supreme. You, a valiant warrior of the elite Rainbow team, have been selected for a mission of vital importance. Your task is to infiltrate a building, confront and defeat the archons that hinder your path, and save your imprisoned companion. Your mission is dangerous, but the hope of a better world rests on your shoulders.

### Index
- [[#Project Description and Implementation|Project Description and Implementation]]
- [[#How to play Extraction|How to play Extraction]]
- [[#Cloning this repository|Cloning this repository]]
- [[#Technologies used in this project|Technologies used in this project]]
- [[#Dependencies|Dependencies]]
- [[#IDE|IDE]]
- [[#Executables and platforms|Executables and platforms]]
- [[#Saved files|Saved files]]


## Project Description and Implementation
During the initial stages of the project, we discussed the plot and specifications of the game. Once the specification and design phases were completed, we began developing the game, dividing the tasks but working in sync. Our goal was to create a simple role-playing game (RPG), based on a pre-existing game, that would allow the player to explore a building full of enemies with the ultimate goal of saving a hostage.

---

## How to play Extraction

When you start the game, this window appears:
![[startmenu.png]]

Here you have two options: you can click start and begin a new game or you can load a saved game. If you chose the second option, you will see a list of your saved files:

![[loadList.png]]

After clicking the desired file, the game starts, and you will see this window:


Here you can visualize the map and move to North, South, East and West. If you have already visisted a room, a checkmark will appear over the room.

Everytime you move, a description of the room will appear on screen. This description includes
- Name of the room
- a short visual description
- eventual enemies or items

During the game, you can encounter three different types of Alien: Runner, Clicker and Shambler, and the fight window will be:

![[fight.png]]

Here you have the options to:
- Attack: the damage depends on what weapon you have
- Leave the fight
- Protect yourself with a **Shielf** if you find one
- **Elude**
- Heal yourself using the **Medikit** if you have one
- Throw a **TNT** to deal more damage

If you successfully won the battle, you will gain a **Score**.

Sometimes you will find some items inside the rooms: those items could be:
- A **weapon**
	- Revolver
	- AK47
	- USPSWORM
- A **key** to open the locked mate room
- **TNTs** to fight the enemies
- A **Shield** 
- A **Medikit**
- A **Torch** to access dark rooms

![[itemPick.png]]

To win the game, the only way is to find the keys to open the room where the hostage is trapped and go back to the starting point, the **Extraction point**

---

## Cloning this repository


``` batch
git clone --branch master https://github.com/EDIDS/EDIDS_Proj.git
```

---

## Technologies used in this project

| Name       | Version | Description                                                                                                                       |
| ---------- | ------- | --------------------------------------------------------------------------------------------------------------------------------- |
| Java       | 20      | Environment and programming language used to develop the application.                                                             |
| Java swing | ?       | A Graphic User Interface (GUI) widget toolkit for Java                                                                            |
| JUnit      | 5.10.2  | Framework for automated testing of Java classes                                                                                   |
| Amazon S3  |         | An Amazon service used to store our data into an Amazon Bucket                                                                    |
| Gson       | 2.11.0  | Gson is a Java library that can be used to convert Java Objects into their JSON representation. It's used to save or load a game. |


## Dependencies

This projects uses some external libraries to enhance the standard Java capabilities, these include:

- **Gson**

  A simple library made by **Google** that allows us to interact with external files in the ```.json``` format <br>
  - **AmazonS3 Buckets**

All the above-mentioned dependencies are downloaded form the [**Maven Central Repository**](https://central.sonatype.com/?smo=true)
and managed by **Gradle**.

---

## IDE

This project was developed entirely in JetBrain's **IntelliJ IDEA 2024**.

If you want to clone this repository and take a look at the code we strongly suggest to use the same IDE as the
repository already contains all the configuration files to run gradle scripts.

---

## Executables and platforms


---

## Saved files

The Extraction game includes a **save** features that allows the player to export all the current game data to a ```.json``` file. In this way a game can be resumed at any time and even on a different computer.

Any alteration of the exported files may result in unwanted behaviour or sometimes even complete crashes of the application.
