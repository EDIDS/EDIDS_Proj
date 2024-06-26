package com.extraction.Graphic;

import com.extraction.S3Uploader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * The UI class represents the user interface of the game.
 * It manages the game window, panels, buttons, and other UI components.
 */
public class UI {
    private static UI instance;

    private JFrame window;
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final Color background = Color.BLACK;
    private final Game.ButtonsHandler bHandler;
    private final Font textFont = new Font("Serif", Font.BOLD, 20);

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem exit, save;

    private JPanel bodyPanel;

    JPanel titlePanel;
    private JLabel titleLabel;
    private String title = "Extraction";
    private final Font titleFont = new Font("Serif", Font.BOLD, 70);

    JPanel startPanel;
    private JButton startButton;
    private final Font startFont = new Font("Serif", Font.BOLD, 40);

    private JButton loadButton;
    private final Font loadFont = new Font("Serif", Font.BOLD, 40);

    private JScrollPane scroll;
    JPanel loadPanel;
    JPanel loadListPanel;

    JPanel topPanel;
    private JLabel topLabelCol1;
    private JLabel topLabelCol2;
    private JLabel topLabelCol3;

    JPanel mapSpacePanel;
    private JPanel mapPanel;
    private JPanel[][] grid;
    String playerIconPath = System.getProperty("user.dir") + "/src/main/java/com/extraction/Graphic/imgs/user.png";
    String checkIconPath = System.getProperty("user.dir") + "/src/main/java/com/extraction/Graphic/imgs/check-mark.png";
    String exitIconPath = System.getProperty("user.dir") + "/src/main/java/com/extraction/Graphic/imgs/logout.png";

    JPanel mainTextPanel;
    public JTextArea mainTextArea;

    JPanel itemsPanel;
    private JButton itemButton1;
    private JButton itemButton2;

    JPanel actionPanel;
    private JButton actionButton1;
    private JButton actionButton2;
    private JButton actionButton3;
    private JButton actionButton4;

    JPanel exitRoomBPanel;
    private JButton exitRoomButton;
    private JButton throwButton;

    JPanel dialogBPanel;
    private JButton dialogButton;

    private JLabel messageLabel;

    /**
     * Singleton Constructs a new UI with the given ButtonsHandler.
     * @param bHandler The ButtonsHandler instance.
     */
    private UI(Game.ButtonsHandler bHandler) {
        this.bHandler = bHandler;
    }

    protected static UI getInstance(Game.ButtonsHandler bHandler) {
        if (instance == null) {
            instance = new UI(bHandler);
        }
        return instance;
    }

    // Home GUI
    /**
     * Sets up the home screen of the game.
     */
    public void homeScreen(String gameTitle) {
        window = new JFrame();
        window.setTitle("EDIDS_Proj");
        window.setLayout(new BorderLayout());
        window.setSize(800, 650);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(background);
        window.setLocationRelativeTo(null);
        this.title = gameTitle;

        bodyPanel = new JPanel();
        bodyPanel.setLayout(null);
        bodyPanel.setBackground(background);

        createMenu();
        createTitlePanel(gameTitle);
        createStartPanel();
        createLoadPanel();

        // Initial Frame
        window.add(menuBar, BorderLayout.NORTH);
        window.add(bodyPanel, BorderLayout.CENTER);

        bodyPanel.add(titlePanel);
        bodyPanel.add(startPanel);
        bodyPanel.add(loadPanel);

        window.setVisible(true);
    }

    /**
     * Creates a menu for the game.
     */
    private void createMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");

        exit = new JMenuItem("Exit");
        save = new JMenuItem("Save & Exit");

        exit.setActionCommand("Exit");
        save.setActionCommand("Save");

        exit.addActionListener(bHandler);
        save.addActionListener(bHandler);

        menu.add(exit);
        menu.add(save);

        menuBar.add(menu);
    }

    /**
     * Creates a menu for the game.
     */
    private void createTitlePanel(String title) {
        // Title Panel
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBounds(20, 70, 745, 150);  // -15 rispetto alla larghezza pagina
        titlePanel.setBackground(background);

        titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        titlePanel.add(titleLabel, gbc);
    }

    public void setTitleLabel(String text) {
        titleLabel.setText(text);
    }

    public void resetTitle() {
        titleLabel.setText(title);
    }

    /**
     * Creates a start panel for the game.
     */
    private void createStartPanel() {
        // Start Button Panel
        startPanel = new JPanel();
        LayoutManager mgr = new GridLayout(2, 1, 20, 20);
        startPanel.setLayout(mgr);
        startPanel.setBounds(220, 290, 345, 150);  // -15
        startPanel.setBackground(background);

        startButton = createButton("START", startFont, bHandler, "Start");
        startPanel.add(startButton, gbc);

        loadButton = createButton("LOAD", loadFont, bHandler, "Load");
        startPanel.add(loadButton, gbc);
    }

    /**
     * Creates a load panel for the game.
     */
    public void createLoadPanel() {
        loadPanel = new JPanel();
        loadPanel.setBounds(50, 50, 685, 500);
        loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.Y_AXIS));
        loadPanel.setBackground(Color.BLACK);

        //JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setBackground(background);
        loadListPanel = new JPanel();
        loadListPanel.setLayout(new BoxLayout(loadListPanel, BoxLayout.Y_AXIS));
        loadListPanel.setBackground(background);

        loadList();

        //loadListPanel = new JPanel();
        scroll = new JScrollPane(loadListPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        loadPanel.add(scroll);
    }

    /**
     * Fills the load list with saved games.
     */
    public boolean loadList() {
        loadListPanel.removeAll();
        try {
            S3Uploader s3Uploader = new S3Uploader("default", "eu-north-1", "edidsgamesave");
            List<String> gameList = s3Uploader.downloadGameList();

            if (gameList.isEmpty()) return false;
            for (String name : gameList) {
                JPanel p = new JPanel();
                p.setLayout(new GridBagLayout());
                p.setBackground(background);
                p.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

                JButton b = createButton(name, startFont, bHandler, "LoadFile");
                b.setPreferredSize(new Dimension(650, 50));
                p.add(b, gbc);
                loadListPanel.add(p, BorderLayout.CENTER);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Game GUI
    /**
     * Sets up the game screen of the game.
     */
    public void gameScreen() {
        createTopBarPanel();
        createMapPanel();
        createMainTextPanel();
        createItemsPanel();
        createExitRoomButtonPanel();
        createActionButtonsPanel();
        createDialogButtonsPanel();
        createMessageLabel();

        bodyPanel.add(topPanel);
        bodyPanel.add(mapSpacePanel);
        bodyPanel.add(mainTextPanel);
        bodyPanel.add(itemsPanel);
        bodyPanel.add(exitRoomBPanel);
        bodyPanel.add(actionPanel);
        bodyPanel.add(dialogBPanel);
        window.add(messageLabel, BorderLayout.SOUTH);
    }

    /**
     * Creates a top bar panel for the game.
     */
    private void createTopBarPanel() {
        // Top Bar Panel
        topPanel = new JPanel();
        LayoutManager mgr = new GridLayout(1, 3, 5, 0);
        topPanel.setLayout(mgr);
        topPanel.setBounds(20, 10, 745, 50);  // -15
        topPanel.setBackground(background);

        topLabelCol1 = new JLabel();
        topLabelCol1.setForeground(Color.WHITE);
        topLabelCol1.setFont(textFont);

        topPanel.add(topLabelCol1);

        topLabelCol2 = new JLabel();
        topLabelCol2.setForeground(Color.WHITE);
        topLabelCol2.setFont(textFont);

        topPanel.add(topLabelCol2);

        topLabelCol3 = new JLabel();
        topLabelCol3.setForeground(Color.WHITE);
        topLabelCol3.setFont(textFont);

        topPanel.add(topLabelCol3);
    }

    public void setCol1(String text) {
        topLabelCol1.setText("HP: " + text);
    }

    public void setCol2(String text) {
        topLabelCol2.setText("Weapon: " + text);
    }

    public void setCol3(String text) {
        topLabelCol3.setText("Score: " + text);
    }

    /**
     * Creates a new map for the game.
     */
    public void newMap() {
        bodyPanel.remove(mapSpacePanel);
        createMapPanel();
        bodyPanel.add(mapSpacePanel);
    }

    /**
     * Creates a map panel for the game.
     */
    private void createMapPanel() {
        mapSpacePanel = new JPanel();
        mapSpacePanel.setLayout(new GridBagLayout());
        mapSpacePanel.setBounds(20, 80, 745, 350);  // -15
        mapSpacePanel.setBackground(background);

        mapPanel = new JPanel();
        // Create a 5x5 grid layout
        LayoutManager mgr = new GridLayout(5, 5);
        mapPanel.setLayout(mgr);

        // Initialize the grid
        grid = new JPanel[5][5];

        // Populate the grid with buttons (or panels)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = createRoom(/*(i + ", " + j)*/ "", textFont);
                mapPanel.add(grid[i][j]);
            }
        }

        grid[4][2].setBorder(BorderFactory.createLineBorder(Color.GREEN));

        grid[0][0].setBackground(background);
        grid[0][1].setBackground(background);
        grid[0][3].setBackground(background);
        grid[0][4].setBackground(background);
        grid[1][1].setBackground(background);
        grid[1][4].setBackground(background);
        grid[3][0].setBackground(background);
        grid[3][2].setBackground(background);
        grid[3][3].setBackground(background);
        grid[4][0].setBackground(background);
        grid[4][3].setBackground(background);
        grid[4][4].setBackground(background);

        mapSpacePanel.add(mapPanel);
    }

    /**
     * Creates a room for the game map.
     * @param text The text to display in the room.
     * @param font The font to use for the text.
     * @return The created room.
     */
    private JPanel createRoom (String text, Font font) {
        JPanel room = new JPanel();
        room.setLayout(new GridBagLayout());
        room.setBackground(Color.LIGHT_GRAY);
        // Set preferred size
        Dimension size = new Dimension(70, 70); // Adjust the size as needed
        room.setPreferredSize(size);
        // Set border color
        room.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border color is black

        JLabel roomLabel = new JLabel(text);
        roomLabel.setForeground(Color.WHITE);
        roomLabel.setFont(font);

        room.add(roomLabel, gbc);

        return room;
    }

    /**
     * Sets the icon for a room on the map.
     * @param row The row of the room.
     * @param col The column of the room.
     * @param iconPath The path of the icon.
     */
    public void setIcon(int row, int col, String iconPath) {
        try {
            //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iconPath));
            ImageIcon originalIcon = new ImageIcon(iconPath);
            Image img = originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);

            JLabel label = new JLabel();
            label.setIcon(icon);
            grid[row][col].removeAll(); // Remove previous components from the JPanel
            grid[row][col].add(label); // Add new JLabel with the new icon
            grid[row][col].revalidate(); // Revalidate the JPanel to reflect changes
            grid[row][col].repaint(); // Repaint the JPanel
        } catch (Exception e) {
            System.out.println(getClass().getClassLoader());
        }

    }

    /**
     * Creates a main text panel for the game.
     */
    private void createMainTextPanel() {
        // Main Text Panel
        mainTextPanel = new JPanel();
        mainTextPanel.setLayout(null);
        mainTextPanel.setBounds(20, 80, 745, 300);  // -15
        mainTextPanel.setBackground(background);

        mainTextArea = new JTextArea();
        mainTextArea.setEditable(false);
        mainTextArea.setLayout(new GridBagLayout());
        mainTextArea.setBounds(20, 50, 700, 250);  // -15
        mainTextArea.setBackground(background);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);

        mainTextPanel.add(mainTextArea);
    }

    /**
     * Creates an items panel for the game.
     */
    private void createItemsPanel() {
        //Items Panel
        itemsPanel = new JPanel();
        LayoutManager mgr = new GridLayout(1, 2, 10, 0);
        itemsPanel.setLayout(mgr);
        itemsPanel.setBounds(120, 400, 545, 40);  // -15
        itemsPanel.setBackground(background);

        itemButton1 = createButton("MedKit", textFont, bHandler, "MedKit");
        itemsPanel.add(itemButton1, gbc);

        itemButton2 = createButton("TNT", textFont, bHandler, "TNT");
        itemsPanel.add(itemButton2, gbc);
    }

    /**
     * Creates an exit items button panel for the game.
     */
    private void createExitRoomButtonPanel() {
        //Exit Items Button Panel
        exitRoomBPanel = new JPanel();
        LayoutManager mgr = new GridLayout(1, 2, 10, 10);
        exitRoomBPanel.setLayout(mgr);
        exitRoomBPanel.setBounds(270, 400, 245, 40);  // -15
        exitRoomBPanel.setBackground(background);

        exitRoomButton = createButton("Exit", textFont, bHandler, "ExitItems");
        throwButton = createButton("Throw", textFont, bHandler, "ThrowItems");

        exitRoomBPanel.add(exitRoomButton, gbc);
        exitRoomBPanel.add(throwButton, gbc);
    }

    public void enableForShow() {
        exitRoomButton.setEnabled(true);
        throwButton.setEnabled(true);
    }

    public void enableForThrow() {
        exitRoomButton.setEnabled(false);
        throwButton.setEnabled(false);
    }

    /**
     * Creates an action buttons panel for the game.
     */
    private void createActionButtonsPanel() {
        //Action Buttons Panel
        actionPanel = new JPanel();
        LayoutManager mgr = new GridLayout(2, 2, 10, 10);
        actionPanel.setLayout(mgr);
        actionPanel.setBounds(20, 450, 745, 90);  // -15
        actionPanel.setBackground(background);

        actionButton1 = createButton("NORTH", textFont, bHandler, "TopLeft");
        actionPanel.add(actionButton1, gbc);

        actionButton2 = createButton("EAST", textFont, bHandler, "TopRight");
        actionPanel.add(actionButton2, gbc);

        actionButton3 = createButton("SOUTH", textFont, bHandler, "BottomLeft");
        actionPanel.add(actionButton3, gbc);

        actionButton4 = createButton("WEST", textFont, bHandler, "BottomRight");
        actionPanel.add(actionButton4, gbc);
    }

    public void resetActionButtons() {
        for (ActionListener al : actionButton1.getActionListeners()) {
            actionButton1.removeActionListener(al);
        }
        for (ActionListener al : actionButton2.getActionListeners()) {
            actionButton2.removeActionListener(al);
        }
        for (ActionListener al : actionButton3.getActionListeners()) {
            actionButton3.removeActionListener(al);
        }
        for (ActionListener al : actionButton4.getActionListeners()) {
            actionButton4.removeActionListener(al);
        }

        actionButton1.addActionListener(bHandler);
        actionButton2.addActionListener(bHandler);
        actionButton3.addActionListener(bHandler);
        actionButton4.addActionListener(bHandler);
    }

    public void setActionBText(String text1, String text2, String text3, String text4) {
        actionButton1.setText(text1);
        actionButton2.setText(text2);
        actionButton3.setText(text3);
        actionButton4.setText(text4);
    }

    public void setActionButtons(JButton button, String text, ActionListener al) {
        button.setText(text);
        button.setEnabled(true);
        button.removeActionListener(bHandler);
        button.addActionListener(al);
    }

    public JButton getActionButton1() {
        return actionButton1;
    }

    public JButton getActionButton2() {
        return actionButton2;
    }

    public JButton getActionButton3() {
        return actionButton3;
    }

    public JButton getActionButton4() {
        return actionButton4;
    }

    /**
     * Sets the buttons for the player's turn.
     */
    public void setMyTurnButton() {
        actionButton1.setEnabled(true);
        actionButton2.setEnabled(true);
        actionButton3.setEnabled(false);
        actionButton4.setEnabled(false);
        itemButton1.setEnabled(true);
        itemButton2.setEnabled(true);
    }

    /**
     * Sets the buttons for the alien's turn.
     */
    public void setAlienTurnButton(boolean hasShield) {
        actionButton1.setEnabled(false);
        actionButton2.setEnabled(false);
        actionButton3.setEnabled(hasShield);
        actionButton4.setEnabled(true);
        itemButton1.setEnabled(false);
        itemButton2.setEnabled(false);
    }

    /**
     * Enables all buttons.
     */
    public void setEnableButtons() {
        actionButton1.setEnabled(true);
        actionButton2.setEnabled(true);
        actionButton3.setEnabled(true);
        actionButton4.setEnabled(true);
        itemButton1.setEnabled(true);
        itemButton2.setEnabled(true);
    }

    /**
     * Disables all buttons.
     */
    public void setUnenableButtons() {
        actionButton1.setEnabled(false);
        actionButton2.setEnabled(false);
        actionButton3.setEnabled(false);
        actionButton4.setEnabled(false);
        itemButton1.setEnabled(false);
        itemButton2.setEnabled(false);
    }

    /**
     * Creates a dialog buttons panel for the game.
     */
    private void createDialogButtonsPanel() {
        //Dialog Button Panel
        dialogBPanel = new JPanel();
        LayoutManager mgr = new GridBagLayout();
        dialogBPanel.setLayout(mgr);
        dialogBPanel.setBounds(20, 450, 745, 90);  // -15
        dialogBPanel.setBackground(background);

        dialogButton = createButton("Continue...", textFont, bHandler, "NextDialog");
        dialogBPanel.add(dialogButton, gbc);
    }

    public void setDialogBText(String text) {
        dialogButton.setText(text);
    }

    /**
     * Creates a button for the game.
     * @param initialLabel The initial label for the button.
     * @param font The font to use for the button.
     * @param handler The action listener for the button.
     * @param command The action command for the button.
     * @return The created button.
     */
    private JButton createButton(String initialLabel, Font font, ActionListener handler, String command) {
        JButton button = new JButton(initialLabel);
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.setFocusPainted(false);
        button.addActionListener(handler);
        button.setActionCommand(command);

        return button;
    }

    /**
     * Creates a message label for the game.
     */
    private void createMessageLabel() {
        messageLabel = new JLabel();
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setMessageVisible(String message, Color borderColor) {
        messageLabel.setText(message); // Imposta il messaggio
        messageLabel.setBorder(BorderFactory.createLineBorder(borderColor));
    }

    public void setMessageNotVisible() {
        messageLabel.setText(""); // Cancella il messaggio
        messageLabel.setBorder(null);
    }
}
