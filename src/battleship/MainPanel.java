package battleship;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class MainPanel extends JPanel implements ActionListener {

    static GridLayout layout;

    UserPanel userPanel;
    EnemyPanel enemyPanel;
    InfoPanel infoPanel;
    ArrayList<Ship> player;
    ArrayList<Ship> enemy;
    boolean someoneWon = false;

    Timer tim;
    int delay;

    boolean isTimer;

    final static int MAX_HITS = 6;
    final static int MAX_BUTTONS = 25;

    public MainPanel() {

        super();

        layout = new GridLayout(1, 3);
        setLayout(layout);

        delay = 1;

        tim = new Timer(delay, this);

        infoPanel = new InfoPanel();
        userPanel = new UserPanel(infoPanel);
        enemyPanel = new EnemyPanel(infoPanel);
        add(userPanel);
        add(infoPanel);
        add(enemyPanel);
        player = new ArrayList();
        enemy = new ArrayList();
        enemyPanel.setEnemyShips();
        
        for (int i = 0; i < MAX_BUTTONS; i++) {
            enemyPanel.battleSquare[i].addActionListener(this);
        }
        
    }
    /*
     public void startGame() {

     while (!someoneWon) // this never changes
     {
     move();
     }
     }*/

    /*
     This method executes one round of moves - one move from
     the user and one move from the enemy.
     *//*
     public void move() {
     // User moves
     userPanel.selectTarget();

     if (!someoneWon) {
     // Enemy moves
     userPanel.enemyAttacks();
     }
     }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < MAX_BUTTONS; i++) {
            if (e.getSource() == enemyPanel.battleSquare[i]) {

                tim.start();
                userPanel.canAttack = true;

            }
            if (e.getSource() == tim) {
                userPanel.enemyAttacks();
                userPanel.canAttack = false;
                tim.stop();
            }
        }
    }
}
