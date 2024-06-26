package com.extraction.Graphic;

import javax.swing.*;
import java.awt.*;

/**
 * The VisibilityManager class manages the visibility of various UI components in the game.
 * It provides methods to show and hide different screens in the game.
 */
public class VisibilityManager {
    UI ui;
    private static VisibilityManager instance;

    /**
     * Singleton Constructs a new VisibilityManager with the given UI.
     * @param ui The UI instance.
     */
    private VisibilityManager(UI ui) {
        this.ui = ui;
    }

    public static VisibilityManager getInstance(UI ui) {
        if (instance == null) {
            instance = new VisibilityManager(ui);
        }
        return instance;
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

    /**
     * Shows the home screen and hides all other screens.
     */
    public void showHomeScreen() {
        everythingNotVisible();
        // Shown
        ui.titlePanel.setVisible(true);
        ui.startPanel.setVisible(true);
    }

    /**
     * Shows the load screen and hides all other screens.
     */
    public void showLoadScreen() {
        everythingNotVisible();
        // Shown
        ui.loadPanel.setVisible(true);
    }

    /**
     * Shows the map screen and hides all other screens.
     */
    public void showMapScreen() {
        everythingNotVisible();
        // Shown
        ui.topPanel.setVisible(true);
        ui.mapSpacePanel.setVisible(true);
        ui.actionPanel.setVisible(true);

    }

    /**
     * Shows the dialog screen and hides all other screens.
     */
    public void showDialogScreen() {
        everythingNotVisible();
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.dialogBPanel.setVisible(true);
    }

    /**
     * Shows the text screen and hides all other screens.
     */
    public void showTextScreen() {
        everythingNotVisible();
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.exitRoomBPanel.setVisible(true);
        ui.actionPanel.setVisible(true);
    }

    /**
     * Shows the fight screen and hides all other screens.
     */
    public void showFightScreen() {
        everythingNotVisible();
        // Shown
        ui.topPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.itemsPanel.setVisible(true);
        ui.actionPanel.setVisible(true);
    }

    /**
     * Shows the end screen and hides all other screens.
     * @param end The end message to display.
     */
    public void showEndScreen(String end) {
        everythingNotVisible();
        ui.setTitleLabel(end);
        // Shown
        ui.titlePanel.setVisible(true);
    }

    /**
     * Shows a message for a specified amount of time.
     * @param message The message to display.
     * @param timerTime The time in milliseconds to display the message.
     * @param borderColor The border color of the message.
     */
    public void showMessage(String message, int timerTime, Color borderColor) {
        ui.setMessageVisible(message, borderColor);

        Timer timer = new Timer(timerTime, e -> ui.setMessageNotVisible());
        timer.setRepeats(false);
        timer.restart();
    }
}
