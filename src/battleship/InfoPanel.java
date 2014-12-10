package battleship;

import java.awt.GridLayout;
import javax.swing.*;

public class InfoPanel extends JPanel {

    static GridLayout layout;
    JButton hitDisplay;
    JButton missDisplay;
    JButton whoseTurn;
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

        hitDisplay = new JButton("Hits: " + hit);
        missDisplay = new JButton("Misses: " + misses);
        whoseTurn = new JButton("DANA HERE YOU GO!");

        add(hitDisplay);
        add(missDisplay);
        add(whoseTurn);

        
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
