package com.extraction.Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The VisibilityManager class manages the visibility of various UI components in the game.
 * It provides methods to show and hide different screens in the game.
 */
public class VisibilityManager {
    UI ui;

    /**
     * Constructs a new VisibilityManager with the given UI.
     * @param ui The UI instance.
     */
    public VisibilityManager(UI ui) {
        this.ui = ui;
    }

    /**
     * Shows the home screen and hides all other screens.
     */
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
        ui.exitItemBPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    /**
     * Shows the load screen and hides all other screens.
     */
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
        ui.exitItemBPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    /**
     * Shows the map screen and hides all other screens.
     */
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
        ui.exitItemBPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    /**
     * Shows the dialog screen and hides all other screens.
     */
    public void showDialogScreen() {
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.dialogBPanel.setVisible(true);
        // Hide
        ui.loadPanel.setVisible(false);
        ui.mapSpacePanel.setVisible(false);
        ui.itemsPanel.setVisible(false);
        ui.titlePanel.setVisible(false);
        ui.startPanel.setVisible(false);
        ui.exitItemBPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
    }

    /**
     * Shows the text screen and hides all other screens.
     */
    public void showTextScreen() {
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.exitItemBPanel.setVisible(true);
        ui.actionPanel.setVisible(true);
        // Hide
        ui.loadPanel.setVisible(false);
        ui.mapSpacePanel.setVisible(false);
        ui.itemsPanel.setVisible(false);
        ui.titlePanel.setVisible(false);
        ui.startPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    /**
     * Shows the fight screen and hides all other screens.
     */
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
        ui.exitItemBPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    /**
     * Shows the end screen and hides all other screens.
     * @param end The end message to display.
     */
    public void showEndScreen(String end) {
        ui.titleLabel.setText(end);
        // Shown
        ui.titlePanel.setVisible(true);
        // Hide
        ui.startPanel.setVisible(false);
        ui.loadPanel.setVisible(false);
        ui.topPanel.setVisible(false);
        ui.mapSpacePanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.itemsPanel.setVisible(false);
        ui.actionPanel.setVisible(false);
        ui.exitItemBPanel.setVisible(false);
        ui.dialogBPanel.setVisible(false);
    }

    /**
     * Shows a message for a specified amount of time.
     * @param message The message to display.
     * @param timerTime The time in milliseconds to display the message.
     * @param borderColor The border color of the message.
     */
    public void showMessage(String message, int timerTime, Color borderColor) {
        ui.messageLabel.setText(message);
        ui.messageLabel.setBorder(BorderFactory.createLineBorder(borderColor));

        Timer timer = new Timer(timerTime, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ui.messageLabel.setText("");
                ui.messageLabel.setBorder(null);
            }
        });
        timer.setRepeats(false);
        timer.restart();
    }


}
