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
        
        hitDisplay = new JButton();
        missDisplay = new JButton();
        
        add(hitDisplay);
        add(missDisplay);
        
        hit = 0;
        misses = 0;
        
        
    }
}
