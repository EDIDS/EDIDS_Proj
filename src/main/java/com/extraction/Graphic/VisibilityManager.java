package com.extraction.Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisibilityManager {
    UI ui;

    public VisibilityManager(UI ui) {
        this.ui = ui;
    }

    public void showHomeScreen() {
        // Shown
        ui.titlePanel.setVisible(true);
        ui.startPanel.setVisible(true);
        // Hide
        ui.loadPanel.setVisible(false);
        ui.topPanel.setVisible(false);
        ui.mapSpacePanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.itemsPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    public void showLoadScreen() {
        // Shown
        ui.loadPanel.setVisible(true);
        // Hide
        ui.titlePanel.setVisible(false);
        ui.startPanel.setVisible(false);
        ui.topPanel.setVisible(false);
        ui.mapSpacePanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.itemsPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    public void showMapScreen() {
        // Shown
        ui.topPanel.setVisible(true);
        ui.mapSpacePanel.setVisible(true);
        ui.actionPanel.setVisible(true);
        // Hide
        ui.loadPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.itemsPanel.setVisible(false);
        ui.titlePanel.setVisible(false);
        ui.startPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    public void showTextScreen() {
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.itemsPanel.setVisible(true);
        ui.dialogBPanel.setVisible(true);
        // Hide
        ui.loadPanel.setVisible(false);
        ui.mapSpacePanel.setVisible(false);
        ui.titlePanel.setVisible(false);
        ui.startPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
    }

    public void showFightScreen() {
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.itemsPanel.setVisible(true);
        ui.actionPanel.setVisible(true);
        // Hide
        ui.loadPanel.setVisible(false);
        ui.mapSpacePanel.setVisible(false);
        ui.titlePanel.setVisible(false);
        ui.startPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    public void showMessage(String message, int timerTime) {
        ui.messageLabel.setText(message); // Imposta il messaggio
        ui.messageLabel.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Crea un timer per far scomparire automaticamente il messaggio dopo 3 secondi
        Timer timer = new Timer(timerTime, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ui.messageLabel.setText(""); // Cancella il messaggio
                ui.messageLabel.setBorder(null);
            }
        });
        timer.setRepeats(false); // Imposta il timer per eseguire una sola volta
        timer.restart(); // Avvia il timer
    }
}
