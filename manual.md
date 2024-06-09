---
permalink: /manual/
---

# Extraction Manual

[**Back to home page**](/Klotski/)

## Project Description and Implementation
During the initial stages of the project, we discussed the plot and specifications of the game. Once the specification and design phases were completed, we began developing the game, dividing the tasks but working in sync. Our goal was to create a simple role-playing game (RPG), based on a pre-existing game, that would allow the player to explore a building full of enemies with the ultimate goal of saving a hostage.

---

## How to play Extraction



---

## Cloning this repository


``` batch
git clone --branch master --recurse-submodules https://github.com/...
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


[**Back to home page**](/Klotski/)
