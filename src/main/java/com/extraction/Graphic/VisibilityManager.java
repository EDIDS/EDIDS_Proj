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

    private void everythingNotVisible() {
        ui.titlePanel.setVisible(false);
        ui.startPanel.setVisible(false);
        ui.loadPanel.setVisible(false);
        ui.topPanel.setVisible(false);
        ui.mapSpacePanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.itemsPanel.setVisible(false);
        ui.exitRoomBPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    public void showHomeScreen() {
        everythingNotVisible();
        // Shown
        ui.titlePanel.setVisible(true);
        ui.startPanel.setVisible(true);
    }

    public void showLoadScreen() {
        everythingNotVisible();
        // Shown
        ui.loadPanel.setVisible(true);
    }

    public void showMapScreen() {
        everythingNotVisible();
        // Shown
        ui.topPanel.setVisible(true);
        ui.mapSpacePanel.setVisible(true);
        ui.actionPanel.setVisible(true);

    }

    public void showDialogScreen() {
        everythingNotVisible();
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.dialogBPanel.setVisible(true);
    }

    public void showTextScreen() {
        everythingNotVisible();
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.exitRoomBPanel.setVisible(true);
        ui.actionPanel.setVisible(true);
    }

    public void showFightScreen() {
        everythingNotVisible();
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.itemsPanel.setVisible(true);
        ui.actionPanel.setVisible(true);
    }

    public void showEndScreen(String end) {
        everythingNotVisible();
        ui.titleLabel.setText(end);
        // Shown
        ui.titlePanel.setVisible(true);
    }

    public void showMessage(String message, int timerTime, Color borderColor) {
        ui.messageLabel.setText(message); // Imposta il messaggio
        ui.messageLabel.setBorder(BorderFactory.createLineBorder(borderColor));

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
