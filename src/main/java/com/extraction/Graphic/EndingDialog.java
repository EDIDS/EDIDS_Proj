package com.extraction.Graphic;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * The EndingDialog class represents the dialog shown at the end of the game.
 * It displays the final score and other game statistics to the user.
 * It manages a list of dialogues that are displayed in sequence.
 */
public class EndingDialog {

    private final List<String> dialogues;
    Iterator<String> iterator;

    /**
     * Constructs a new EndingDialog.
     * Initializes the dialogues list and its iterator.
     */
    public EndingDialog() {
        dialogues = new ArrayList<>();
        initializeDialogues();
        iterator = dialogues.iterator();

    }

    /**
     * Initializes the dialogues list with the ending dialogues of the game.
     */
    public void initializeDialogues() {
        dialogues.add("THE MISSION WAS A SUCCESS, WELL DONE SOLDIER!");
    }

    /**
     * Returns the next dialogue in the sequence.
     * @return The next dialogue in the sequence.
     */
    public String nextDialogue() {
        return iterator.next();
    }
}
