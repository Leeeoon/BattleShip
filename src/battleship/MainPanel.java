package battleship;

import static battleship.UserPanel.layout;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    static GridLayout layout;

    UserPanel userPanel;
    EnemyPanel enemyPanel;
    InfoPanel infoPanel;
    ArrayList<Ship> player;
    ArrayList<Ship> enemy;

    public MainPanel() {

        super();

        layout = new GridLayout(1, 3);
        setLayout(layout);

        userPanel = new UserPanel();
        infoPanel = new InfoPanel();
        enemyPanel = new EnemyPanel();
        add(userPanel);
        add(infoPanel);
        add(enemyPanel);
        infoPanel = new InfoPanel();
        player = new ArrayList();
        enemy = new ArrayList();

    }

}
