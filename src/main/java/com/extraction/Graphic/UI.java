package com.extraction.Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UI {
    JFrame window;
    Container con;
    GridBagConstraints gbc = new GridBagConstraints();
    String position = "";
    Color background = Color.BLACK;
    Game.ButtonsHandler bHandler;

    JToolBar toolbar;
    JMenuBar mb;
    JMenu menu;
    JMenuItem exit,save;
    JTextArea ta;

    JPanel bodyPanel;

    JPanel titlePanel;
    JLabel titleLabel;
    Font titleFont = new Font("Serif", Font.BOLD, 70);

    JPanel startPanel;
    JButton startButton;
    Font startFont = new Font("Serif", Font.BOLD, 40);

    JButton loadButton;
    Font loadFont = new Font("Serif", Font.BOLD, 40);

    JPanel topPanel;
    JLabel[] topLabel;
    JLabel topLabelCol1;
    JLabel topLabelCol2;
    Font topFont = new Font("Serif", Font.BOLD, 20);

    JPanel mapSpacePanel;
    JPanel mapPanel;
    JPanel[][] grid;
    String playerIconPath = System.getProperty("user.dir") + "/src/main/java/com/extraction/Graphic/imgs/user.png";
    String checkIconPath = System.getProperty("user.dir") + "/src/main/java/com/extraction/Graphic/imgs/check-mark.png";
    String exitIconPath = System.getProperty("user.dir") + "/src/main/java/com/extraction/Graphic/imgs/logout.png";

    JPanel mainTextPanel;
    JTextArea mainTextArea;
    Font mainTextFont = new Font("Serif", Font.BOLD, 20);

    JPanel actionPanel;
    JButton actionButton1;
    JButton actionButton2;
    JButton actionButton3;
    JButton actionButton4;
    Font actionFont = new Font("Serif", Font.BOLD, 20);

    JLabel messageLabel;

    protected UI(Game.ButtonsHandler bHandler) {
        this.bHandler = bHandler;
    }

    // Home GUI
    public void homeScreen() {
        window = new JFrame();
        window.setTitle("EDIDS_Proj2");
        window.setLayout(new BorderLayout());
        window.setSize(800, 650);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(background);
        window.setLocationRelativeTo(null);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(null);
        bodyPanel.setBackground(background);
        //con = window.getContentPane();
        //con.setLayout(null);

        createMenu();
        createTitlePanel();
        createStartButtonPanel();

        // Initial Frame
        window.add(mb, BorderLayout.NORTH);
        //window.setJMenuBar(mb);
        window.add(bodyPanel, BorderLayout.CENTER);

        bodyPanel.add(titlePanel);
        bodyPanel.add(startPanel);

        window.setVisible(true);
    }

    private void createMenu() {
        exit = new JMenuItem("Exit");
        save = new JMenuItem("Save");
        exit.setActionCommand("Exit");
        save.setActionCommand("Save");

        exit.addActionListener(bHandler);
        save.addActionListener(bHandler);

        mb = new JMenuBar();
        menu = new JMenu("Menu");

        menu.add(exit);
        menu.add(save);

        mb.add(menu);

        ta = new JTextArea();
        ta.setBounds(5,5,360,320);
    }

    /*private void actionPerformed(ActionEvent e) {
        if(e.getSource()== exit)
            ta.cut();
        if(e.getSource()==paste)
            ta.paste();
        if(e.getSource()==copy)
            ta.copy();
        if(e.getSource()==selectAll)
            ta.selectAll();
    }*/

    private void createTitlePanel() {
        // Title Panel
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBounds(20, 70, 745, 150);  // -15
        titlePanel.setBackground(background);

        titleLabel = new JLabel("Game Title");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        titlePanel.add(titleLabel, gbc);
    }

    private void createStartButtonPanel() {
        // Start Button Panel
        startPanel = new JPanel();
        //startPanel.setLayout(new GridBagLayout());
        LayoutManager mgr = new GridLayout(2, 2, 20, 20);
        startPanel.setLayout(mgr);
        startPanel.setBounds(220, 290, 345, 150);  // -15
        startPanel.setBackground(background);

        startButton = createButton("START", startFont, bHandler, "Start");
        startPanel.add(startButton, gbc);

        loadButton = createButton("LOAD", loadFont, bHandler, "Load");
        startPanel.add(loadButton, gbc);
    }

    // Game GUI
    public void gameScreen() {
        createTopBarPanel();
        createMapPanel();
        createMainTextPanel();
        createActionButtonsPanel();
        createMessageLabel();

        bodyPanel.add(topPanel);
        bodyPanel.add(mapSpacePanel);
        bodyPanel.add(mainTextPanel);
        bodyPanel.add(actionPanel);
        window.add(messageLabel, BorderLayout.SOUTH);
    }

    private void createTopBarPanel() {
        // Top Bar Panel
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.setBounds(20, 10, 745, 50);  // -15
        topPanel.setBackground(background);

        topLabelCol1 = new JLabel();
        topLabelCol1.setForeground(Color.WHITE);
        topLabelCol1.setFont(topFont);

        topPanel.add(topLabelCol1);

        topLabelCol2 = new JLabel();
        topLabelCol2.setForeground(Color.WHITE);
        topLabelCol2.setFont(topFont);

        topPanel.add(topLabelCol2);
    }

    public void newMap() {
        bodyPanel.remove(mapSpacePanel);
        createMapPanel();
        bodyPanel.add(mapSpacePanel);
    }

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
                grid[i][j] = createRoom((i + ", " + j), topFont);
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

    private void movePlayer(int fromRow, int fromCol, int toRow, int toCol, String fromIconPath) {
        setIcon(fromRow, fromCol, fromIconPath);
        setIcon(toRow, toCol, playerIconPath);
    }

    private void createMainTextPanel() {
        // Main Text Panel
        mainTextPanel = new JPanel();
        mainTextPanel.setLayout(null);
        mainTextPanel.setBounds(20, 80, 745, 350);  // -15
        mainTextPanel.setBackground(background);

        mainTextArea = new JTextArea();
        mainTextArea.setLayout(new GridBagLayout());
        mainTextArea.setBounds(20, 50, 700, 250);  // -15
        mainTextArea.setBackground(background);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(mainTextFont);
        mainTextArea.setLineWrap(true);

        mainTextPanel.add(mainTextArea);
    }

    private void createActionButtonsPanel() {
        //Action Buttons Panel
        actionPanel = new JPanel();
        LayoutManager mgr = new GridLayout(2, 2, 10, 10);
        actionPanel.setLayout(mgr);
        actionPanel.setBounds(20, 450, 745, 90);  // -15
        actionPanel.setBackground(background);

        actionButton1 = createButton("NORTH", actionFont, bHandler, "TopLeft");
        actionPanel.add(actionButton1, gbc);

        actionButton2 = createButton("EAST", actionFont, bHandler, "TopRight");
        actionPanel.add(actionButton2, gbc);

        actionButton3 = createButton("SOUTH", actionFont, bHandler, "BottomLeft");
        actionPanel.add(actionButton3, gbc);

        actionButton4 = createButton("WEST", actionFont, bHandler, "BottomRight");
        actionPanel.add(actionButton4, gbc);
    }

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

    private void createMessageLabel() {
        messageLabel = new JLabel();
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
