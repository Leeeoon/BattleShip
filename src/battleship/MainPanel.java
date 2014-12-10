package battleship;

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

        infoPanel = new InfoPanel();
        userPanel = new UserPanel(infoPanel);
        enemyPanel = new EnemyPanel(infoPanel);
        add(userPanel);
        add(infoPanel);
        add(enemyPanel);
        player = new ArrayList();
        enemy = new ArrayList();
        enemyPanel.setEnemyShips();

    }

    public void startGame() {

        /*
         do
         move
         while nobody has won yet
         */
        move();
    }

    /*
     This method executes one round of moves - one move from
     the user and one move from the enemy.
     */
    public void move() {
        int spaceToAttack = enemyPanel.selectTarget();
        userPanel.handleEnemyAttack(spaceToAttack);
    }

}
