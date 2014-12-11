package battleship;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

// EnemyPanel houses the enemies' ships, and handles how user attacks enemy
public class EnemyPanel extends JPanel implements ActionListener {

    
    static GridLayout layout;
    JButton[] battleSquare;
    final static int MAX_BUTTONS = 25;
    InfoPanel infoPanel;
    int[] enemyShips = new int[6];                                              // 0 and 1 are one ship, 2 and 3 are the second 
    //ship and 4 and 5 are the last ship

    ImageIcon boomIcon;

    public EnemyPanel(InfoPanel infoPanel) {

        super();
        layout = new GridLayout(5, 5);
        setLayout(layout);

        boomIcon = new ImageIcon("boom.png");

        this.infoPanel = infoPanel;

        battleSquare = new JButton[MAX_BUTTONS];

        for (int shipNumber = 0; shipNumber < MAX_BUTTONS; shipNumber++) {
            battleSquare[shipNumber] = new JButton("");
            battleSquare[shipNumber].addActionListener(this);
            add(battleSquare[shipNumber]);
        }
    }
    

    /*
     This method simply generates a space to attack and passes it
     back up to MainPanel, which will then pass it into UserPanel's 
     method for handling an enemy attack. It's kind of a backwards
     way to handle this, but it's the best we could come up with
     while sticking to the design.
     */
    public int selectTarget() {
        Random rand = new Random();
        return rand.nextInt(25) + 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < MAX_BUTTONS; i++) {

            if (e.getSource() == battleSquare[i]) {
                battleSquare[i].setEnabled(false);
                //   for (int z = 0; z < 6; z++) {
                if (battleSquare[i].getText().equals(" ")) {
                    battleSquare[i].setText("");
                    battleSquare[i].setIcon(boomIcon);
                    infoPanel.increaseHit();

                } else {
                    infoPanel.increaseMiss();
                }
            }
        }
    }
}
