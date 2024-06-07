package com.extraction.Graphic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The IntroductionDialog class represents the dialog shown at the beginning of the game.
 * It manages a list of dialogues that are displayed in sequence.
 */
public class IntroductionDialog {

    private List<String> dialogues;
    Iterator<String> iterator;

    /**
     * Constructs a new IntroductionDialog.
     * Initializes the dialogues list and its iterator.
     */
    public IntroductionDialog() {
        dialogues = new ArrayList<>();
        initializeDialogues();
        iterator = dialogues.iterator();

    }

    /**
     * Initializes the dialogues list with the introduction dialogues of the game.
     */
    public void initializeDialogues() {
        dialogues.add("""
                    You are an operator of the Rainbow team,\s
                    an elite military unit specialized in infiltration and hostage rescue.\s
                    The world has been invaded by alien parasites known as Archeans,\s
                    causing chaos and terror.\s
                    The Rainbow team must intervene to restore peace.\s
                    """);

        dialogues.add("""
                    You are at the HQ, heading towards the operations room.\s
                    You meet the leader of the Rainbow team.\s
                    """);
        dialogues.add("...: \"You are SERGIO, the new recruit to the team.\"");
        dialogues.add("YOU: \"Yes, that's me.\"");
        dialogues.add("""
                    Mira: "Good, I'm Ash, the leader of Rainbow Six team.\s
                    I've read your files, you're a Navy Seal,\s
                    exactly what I need right now.\s
                    In a recent mission, one of our teammates fell in battle\s
                    and was taken prisoner by the Archeans.\s
                    Your objective will be to infiltrate the facility,\s
                    find him, and bring him back to the extraction point.\s
                    Is it clear what you need to do?"\s
                    """);
        dialogues.add("YOU: \"Yes, ma'am!\"");
        dialogues.add("""
                    Ash: "Good.\s
                    But be careful, the Archeans are tough.\s
                    Access to the room where Smoke has been imprisoned\s
                    probably won't be immediate.\s
                    Explore the building and gather anything that might help you get in.\s
                    Good luck."\s
                    """);
        dialogues.add("""
                    After gathering necessary information and equipment,\s
                    you head towards the designated area.\s
                    Once there, you enter the building and your mission begins,\s
                    with the objective of finding and rescuing your teammate.\s
                    """);
        dialogues.add("""
                    Each time you move from one room to another,\s
                    a description of what you see around you appears.\s
                    """);
    }

    /**
     * Returns an iterator for the dialogues list.
     * @return An Iterator for the dialogues list.
     */
    public Iterator<String> getDialogueIterator() {
        return iterator;
    }

    /**
     * Returns the next dialogue in the sequence.
     * @return The next dialogue in the sequence.
     */
    public String nextDialogue() {
        return iterator.next();
    }
}
