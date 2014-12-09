package battleship;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserPanel extends JPanel implements ActionListener {

    static GridLayout layout;
    JButton[] battleSquare;

    final static int MAX_BUTTONS = 25;

    public UserPanel() {

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
        for (int i = 0; i < MAX_BUTTONS; i++) {

            if (e.getSource() == battleSquare[i]) {
                battleSquare[i].setEnabled(false);
            }
        }
    }
}
