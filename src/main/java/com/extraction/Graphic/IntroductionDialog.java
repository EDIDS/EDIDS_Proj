package com.extraction.Graphic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntroductionDialog {

    private List<String> dialogues;
    Iterator<String> iterator;


    public IntroductionDialog() {
        dialogues = new ArrayList<>();
        initializeDialogues();
        iterator = dialogues.iterator();

    }

    public void initializeDialogues() {
        dialogues.add("""
                    Sei un operatore della squadra Rainbow,
                    un'élite militare specializzata in infiltrazioni e salvataggio di ostaggi.\s
                    Il mondo è stato invaso da parassiti alieni noti come archei,\s
                    generando caos e terrore.\s
                    La squadra Rainbow deve intervenire per riportare la pace.
                    """);
        //dialogues.add("Inserisci il tuo nome:");
        dialogues.add("""
                    Ti trovi agli HQ, ti dirigi verso la sala operativa.\s
                    Incontri il capo del team Rainbow.
                    """);
        dialogues.add("...: “Tu sei NOME, il nuovo ingresso nel team”");
        dialogues.add("TU: “Sì sono proprio io”");
        dialogues.add("""
                    Mira: “Bene, io sono Ash, il capo della squadra Rainbow Six.\s
                                 Ho letto i tuoi fascicoli, sei un Navy Seal,\s
                                 esattamente ciò di cui ho bisogno ora.\s
                                 In una recente missione, un nostro compagno è caduto in battaglia\s
                                 ed è stato preso prigioniero dagli archei.\s
                                 Il tuo obiettivo sarà quello di infiltrarti all’interno della struttura,\s
                                 trovarlo e riportarlo al punto di estrazione.\s
                                 È chiaro cosa devi fare?”
                    """);
        dialogues.add("TU: “Sì signora!”");
        dialogues.add("""
                    Ash: “Bene.\s
                               Fa attenzione però, gli archei ci vanno giù pesante.\s
                               Probabilmente l’accesso alla stanza dove Smoke è stato imprigionato\s
                               non sarà immediato.\s
                               Esplora l’edificio e raccogli tutto ciò che può esserti utile ad entrare.\s
                               Buona fortuna.”
                    """);
        dialogues.add("""
                    Dopo aver raccolto informazioni e attrezzature necessarie,\s
                    ti dirigi verso la zona designata.\s
                    Una volta arrivato, entri nell'edificio e la tua missione comincia,\s
                    con l'obiettivo di trovare e salvare il tuo compagno.

                    """);
        dialogues.add("""
                    Ogni volta che ti sposti da una stanza ad un’altra,\s
                    ti appare la descrizione di cosa vedi attorno
                    """);
    }

    public Iterator<String> getDialogueIterator() {
        return iterator;
    }

    public String nextDialogue() {
        return iterator.next();
    }
}
