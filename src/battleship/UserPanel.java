package battleship;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.JComponent;

public class UserPanel extends JPanel implements ActionListener {

    // UserPanel houses the user's ships, and handles how the enemy attacks user
    Random rand = new Random();

    static GridLayout layout;
    JButton[] battleSquare;
    int enemyTarget;
    boolean canAttack;
    InfoPanel infoPanel;
    int[] userShips = new int[6];

    int enemyHits;

    ImageIcon boomIcon;

    final static int MAX_SCORE = 6;
    final static int MAX_BUTTONS = 25;

    public UserPanel(InfoPanel infoPanel) {

        super();

        layout = new GridLayout(5, 5);
        setLayout(layout);
        canAttack = true;
        
        boomIcon = new ImageIcon("boom.png");
        this.infoPanel = infoPanel;

        enemyHits = 0;

        battleSquare = new JButton[MAX_BUTTONS];

        for (int i = 0; i < MAX_BUTTONS; i++) {
            battleSquare[i] = new JButton();
            battleSquare[i].addActionListener(this);
            add(battleSquare[i]);
        }

    }

    /*
    Having a method like selectTarget is a procedural, rather than
    action-based, way of design; because we had no choice but to put
    the logic handling the selection of a target in actionPerformed,
    this method and returnClickCoordinates were unnecessary
    */
    public void selectTarget() {
        
    }

    public void enemyAttacks() {

        // will choose a random square to attack in UserPanel
        if (canAttack) {
            enemyTarget = rand.nextInt(25);                     // get a target
            while (!battleSquare[enemyTarget].isEnabled()) {
                // check to see if the JButton is enabled
                // if JButton is not enabled, get a new target
                enemyTarget = rand.nextInt(25);
            }
            battleSquare[enemyTarget].setEnabled(false);
            canAttack = false;

            if (battleSquare[enemyTarget].getText().equals("SHIP")) {
                enemyHits++;
                battleSquare[enemyTarget].setText("");
                battleSquare[enemyTarget].setIcon(boomIcon);
            }
            if (enemyHits == MAX_SCORE) {
                JOptionPane.showMessageDialog(null, "You Lost!");
                System.exit(0);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < MAX_BUTTONS; i++) {
            if (e.getSource() == battleSquare[i]) {

            }
        }

    }
}
