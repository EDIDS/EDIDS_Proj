# Extraction - Text adventure
### Introduzione
“Extraction” è un’avventura testuale che prende vita dal rinomato videogioco di Ubisoft, “**Rainbow Six Extraction**”. In un mondo sconvolto da un’invasione parassitaria aliena, il caos e la distruzione regnano sovrani. Tu, un valoroso guerriero dell’elite della squadra Rainbow, sei stato selezionato per una missione di vitale importanza. Il tuo compito è quello di infiltrarti in un edificio, affrontare e sconfiggere gli archei che ostacolano il tuo percorso, e portare in salvo il tuo compagno prigioniero. La tua missione è pericolosa, ma la speranza di un mondo migliore riposa sulle tue spalle.

## Tecnologie usate
| Name       | Version | Description                                                                                                                       |
| ---------- | ------- | --------------------------------------------------------------------------------------------------------------------------------- |
| Java       | 20      | Environment and programming language used to develop the application.                                                             |
| Java swing | ?       | A Graphic User Interface (GUI) widget toolkit for Java                                                                            |
| JUnit      | 5.10.2  | Framework for automated testing of Java classes                                                                                   |
| Amazon S3  |         | An Amazon service used to store our data into an Amazon Bucket                                                                    |
| Gson       | 2.11.0  | Gson is a Java library that can be used to convert Java Objects into their JSON representation. It's used to save or load a game. |

## Project Description and Implementation
Nelle fasi iniziali dello sviluppo del progetto, sono state discusse la trama e le specifiche del gioco. 
In seguito, ultimate le specifiche e le design phases, abbiamo iniziato a sviluppare il nostro gioco, dividendoci le mansioni ma lavorando comunque in sincronia.
La nostra idea era quella di implementare un semplice gioco rpg, basato in questo caso su un gioco già esistente, che consentisse l'esplorazione di un edificio occupato da dei nemici con l'obiettivo di salvare un ostaggio.

## Interfaccia grafica e movimento
L'interfaccia grafica è stata sviluppata attraverso un framework chiamato "Swing" (spiegare ulteriormente come  funziona)

L'interfaccia mostra la mappa dell'edificio, con le varie stanze in cui il giocatore può andare. In basso ci sono 4 bottoni: Nord, Sud, Est e Ovest, adibiti allo spostamento del giocatore. Ovviamente, se nella direzione scelta non c'è una stanza allora lo spostamento verrà negato.
Ogni stanza può contenere un alieno e una serie di oggetti, che eventualmente il giocatore potrà raccogliere.
Tutte queste informazioni verranno scritte a schermo.

## Il gioco
Il gioco è ambientato in un edificio conquistato da un parassita alieno. Un compagno del team Rainbow è stato catturato da questi alieni e il tuo obiettivo sarà quello di salvarlo, trovandolo e uscendo dall'edificio.
Nella ricerca, potrai incorrere in alcuni di questi alieni, che dovrai combattere o da cui potrai fuggire. Se durante il combattimento perderai tutta la vita, allora sarà game over.
Nelle varie stanze potrai trovare alcuni oggetti che potrai raccogliere.
Attenzione però che potrai portare un certo numero di oggetti e dovrai restare sotto un certo peso. Potrai rimuovere dal tuo inventario gli oggetti che non vuoi puoi tenere.
In qualsiasi momento della partita, il giocatore potrà salvare la partita. Dal menù iniziale, potrà infine caricare una partita tra quelle salvate in precedenza

---

## Specification Document
Questa sezione contiene gli Use Case Diagram. Sotto sono riportate le tabelle di ogni use case:

inserire tabelle degli use case

---

## Design document
---
