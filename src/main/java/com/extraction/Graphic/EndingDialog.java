package com.extraction.Graphic;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class EndingDialog {

    private List<String> dialogues;
    Iterator<String> iterator;


    public EndingDialog() {
        dialogues = new ArrayList<>();
        initializeDialogues();
        iterator = dialogues.iterator();

    }

    public void initializeDialogues() {
        dialogues.add("Aggiungere una conclusione");
    }

    public Iterator<String> getDialogueIterator() {
        return iterator;
    }

    public String nextDialogue() {
        return iterator.next();
    }
}
