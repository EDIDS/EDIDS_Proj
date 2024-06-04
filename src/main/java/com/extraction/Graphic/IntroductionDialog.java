package com.extraction.Graphic;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class IntroductionDialog {

    private List<String> dialogues;
    Iterator<String> iterator;


    public IntroductionDialog() {
        dialogues = new ArrayList<>();
        initializeDialogues();
        iterator = dialogues.iterator();

    }

    public void initializeDialogues() {
        dialogues.add("Sei un operatore della squadra Rainbow, un'élite militare specializzata in infiltrazioni e salvataggio di ostaggi. Il mondo è stato invaso da parassiti alieni noti come archei, generando caos e terrore. La squadra Rainbow deve intervenire per riportare la pace.");
        dialogues.add("Inserisci il tuo nome:");
        dialogues.add("Ti trovi agli HQ, ti dirigi verso la sala operativa. Incontri il capo del team Rainbow.");
        dialogues.add("...: “Tu sei nome, il nuovo ingresso nel team”");
        dialogues.add("TU: “Sì sono proprio io”");
        dialogues.add("Mira: “Bene, io sono Ash, il capo della squadra Rainbow Six. Ho letto i tuoi fascicoli, sei un Navy Seal, esattamente ciò di cui ho bisogno ora in una recente missione, un nostro compagno è caduto in battaglia ed è stato preso prigioniero dagli archei. Il tuo obiettivo sarà quello di infiltrarti all’interno della struttura, trovarlo e riportarlo al punto di estrazione. È chiaro cosa devi fare?”");
        dialogues.add("TU: “Sì signora!”");
        dialogues.add("Ash: “Bene. Fa attenzione però, gli archei ci vanno giù pesante. Inoltre, probabilmente l’accesso alla stanza dove Smoke è stato imprigionato non sarà immediato. Esplora l’edificio e raccogli tutto ciò che può esserti utile ad entrare. Buona fortuna.”");
        dialogues.add("Dopo aver raccolto informazioni e attrezzature necessarie, ti dirigi verso la zona designata. Una volta arrivato, entri nell'edificio e la tua missione comincia, con l'obiettivo di trovare e salvare il tuo compagno.");
        dialogues.add("Ogni volta che ti sposti da una stanza ad un’altra, ti appare la descrizione di cosa vedi attorno");
    }

    public Iterator<String> getDialogueIterator() {
        return iterator;
    }

    public String nextDialogue() {
        return iterator.next();
    }
}
