package battleship;

import javax.swing.*;

public class MainFrame extends JFrame {
    
    MainPanel mainPanel;
    
    public MainFrame() {
        
        mainPanel = new MainPanel();
        JFrame application = new JFrame();
        application.setTitle("BattleShip");

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(mainPanel);

        application.setSize(1000, 700);
        application.setVisible(true);
    }
}
