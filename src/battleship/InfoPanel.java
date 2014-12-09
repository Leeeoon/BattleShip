package battleship;

import java.awt.GridLayout;
import javax.swing.*;

public class InfoPanel extends JPanel {

    static GridLayout layout;
    JButton hitDisplay;
    JButton missDisplay;
    int hit;
    int misses;

    public InfoPanel() {

        super();

        layout = new GridLayout(2, 1);
        setLayout(layout);

        hit = 0;
        misses = 0;

        hitDisplay = new JButton("Hits: " + hit);
        missDisplay = new JButton("Misses: " + misses);

        add(hitDisplay);
        add(missDisplay);


    }

    public void increaseHit() {
        hit++;
        hitDisplay.setText("Hit: " + hit);
    }

    public void increaseMiss() {
        misses++;
        missDisplay.setText("Misses: " + misses);
    }
}
