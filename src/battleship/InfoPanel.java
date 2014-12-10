package battleship;

import java.awt.GridLayout;
import javax.swing.*;

public class InfoPanel extends JPanel {

    static GridLayout layout;
    JButton hitDisplay;
    JButton missDisplay;
    JButton instructionsDisplay;
    int hit;
    int misses;
    final static int MAX_SCORE = 6;
    int computerHits;

    public InfoPanel() {

        super();

        layout = new GridLayout(3, 1);
        setLayout(layout);

        hit = 0;
        misses = 0;

        String instructions = "<html><center>Welcome to Battleship!<br>"
                + "Your ships are on your left, "
                + "and your enemy's board is on your right.<br>"
                + "Select an enemy ship to attack, "
                + "and the enemy will attack one of your ships.<br>"
                + "The game ends when one player's ships have sunk!</center></html>";
        
        instructionsDisplay = new JButton(instructions);
        hitDisplay = new JButton("Hits: " + hit);
        missDisplay = new JButton("Misses: " + misses);

        add(instructionsDisplay);
        add(hitDisplay);
        add(missDisplay);

        
    }

    public void increaseHit() {
        hit++;
        hitDisplay.setText("Hit: " + hit);
        if(hit == MAX_SCORE){
            JOptionPane.showMessageDialog(null, "You Won!");
            System.exit(0);
        }
    }

    public void increaseMiss() {
        misses++;
        missDisplay.setText("Misses: " + misses);
    }
}
