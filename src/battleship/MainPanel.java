package battleship;

import javax.swing.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    
    UserPanel userPanel;
    EnemyPanel userInterfacePanel;
    InfoPanel infoPanel;
    ArrayList<Ship> player;
    ArrayList<Ship> enemy;
    
    public MainPanel() {
        
        userPanel = new UserPanel();
        userInterfacePanel = new EnemyPanel();
        infoPanel = new InfoPanel();
        player = new ArrayList();
        enemy = new ArrayList();
    }
    
}
