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

    final static int MAX_BUTTONS = 25;

    public UserPanel() {

        super();
        
        layout = new GridLayout(5, 5);
        setLayout(layout);
        canAttack = false;

        battleSquare = new JButton[MAX_BUTTONS];

        for (int i = 0; i < MAX_BUTTONS; i++) {
            battleSquare[i] = new JButton();
            battleSquare[i].addActionListener(this);
            add(battleSquare[i]);
        }
    }

    public void selectTarget()
    {
        
        
        
        
        // Popup window: "Select leftmost space (if horizontal)
        //               or topmost space (if vertical)"
        
        
        // Set ship
    }
    
    public void handleEnemyAttack(int spaceToAttack)
    {
        selectTarget();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
    public void enemyAttacks() {
       
        // will choose a random square to attack in UserPanel
        // if there is no ship, canAttack is set to false
        
        if (canAttack) {
            enemyTarget = rand.nextInt(25);
            battleSquare[enemyTarget].setEnabled(false);
            System.out.println(enemyTarget);
            if (!battleSquare[enemyTarget].getText().equals("SHIP")) {
                canAttack = false;
            }
        }
    }
}
