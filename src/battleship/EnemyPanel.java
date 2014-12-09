package battleship;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class EnemyPanel extends JPanel implements ActionListener {

    static GridLayout layout;
    JButton[] battleSquare;

    Random rand = new Random();
    int shipTracker;
    int enemyTarget;

    final static int MAX_BUTTONS = 25;

    public EnemyPanel() {

        super();
        layout = new GridLayout(5, 5);
        setLayout(layout);

        battleSquare = new JButton[MAX_BUTTONS];

        for (int i = 0; i < MAX_BUTTONS; i++) {
            battleSquare[i] = new JButton();
            battleSquare[i].addActionListener(this);
            add(battleSquare[i]);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
