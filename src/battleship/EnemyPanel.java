package battleship;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class EnemyPanel extends JPanel implements ActionListener {
    
    // EnemyPanel houses the enemies' ships, and handles how user attacks enemy

    static GridLayout layout;
    JButton[] battleSquare;
    
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
    
    /*
    This method simply generates a space to attack and passes it
    back up to MainPanel, which will then pass it into UserPanel's 
    method for handling an enemy attack. It's kind of a backwards
    way to handle this, but it's the best we could come up with
    while sticking to the design.
    */
    public int selectTarget()
    {
        Random rand = new Random();
        return rand.nextInt(25) + 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < MAX_BUTTONS; i++) {

            if (e.getSource() == battleSquare[i]) {
                battleSquare[i].setEnabled(false);
            }
        }
    }

}
