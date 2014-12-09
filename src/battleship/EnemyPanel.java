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
    InfoPanel infoPanel;
    int[] enemyShips = new int[6];                                              // 0 and 1 are one ship, 2 and 3 are the second 
    //ship and 4 and 5 are the last ship

    public EnemyPanel(InfoPanel infoPanel) {

        super();
        layout = new GridLayout(5, 5);
        setLayout(layout);

        this.infoPanel = infoPanel;

        battleSquare = new JButton[MAX_BUTTONS];

        for (int i = 0; i < MAX_BUTTONS; i++) {
            battleSquare[i] = new JButton();
            battleSquare[i].addActionListener(this);
            add(battleSquare[i]);
        }
    }

    public void setEnemyShips() {
        //'i' is the array number
        for (int i = 0; i < 6; i++) {                                           //'x' is the button number
            System.out.println("i = " + i);
            double temp_x = Math.random() * 25;                                 //Finds the first random number out of all
            int x = (int) temp_x;
            System.out.println("x = " + x);

            if (battleSquare[x].getText().equals("SHIP")) {

                i--;
                //for (int u = 0; u < i; u++) {
                //       System.out.println("enemyShips[" + u + "]" + " = " + x);
                //   }
                //   System.out.println("Recursion i = " + i);

                setEnemyShips();
            }
            battleSquare[x].setText("SHIP");


            enemyShips[i] = x;                                                  //Sets the first number of the enemyShip                                                    //Used so that 
//==========================================================Setting the Ships          
            double cornerSpot = Math.random() * 2;                               //These are based on the number of options
            double edgeSpot = Math.random() * 3;
            double middleSpot = Math.random() * 4;
            int cS = (int) cornerSpot;                                           //Used for setting the second spot
            int eS = (int) edgeSpot;
            int mS = (int) middleSpot;

            i++;
//------------------------------------------------------------------------------Four Corners of the gridlayout        
            if (x == 0) {                                                       //Top Left Corner
                if (cS == 0) {//To the right 1 button
                    x++;
                    enemyShips[i] = x;
                } else {//Vertically down 1 button
                    x = 5;
                    enemyShips[i] = x;
                }
//------------------------------------------------------------------------------
            } else if (x == 4) {                                                //Top Right Corner
                if (cS == 0) {//To the left 1 button
                    x--;
                    enemyShips[i] = x;
                } else {//Vertically down 1 button
                    x = x + 5;
                    enemyShips[i] = x;
                }
//------------------------------------------------------------------------------
            } else if (x == 20) {                                               //Bottom Left Corner
                if (cS == 0) {//To the left 1 button
                    x++;
                    enemyShips[i] = x;
                } else {//Vertically down 1 button
                    x = x - 5;
                    enemyShips[i] = x;
                }
//------------------------------------------------------------------------------
            } else if (x == 24) {                                               //Bottom Right Corner
                if (cS == 0) {//To the left 1 button
                    x--;
                    enemyShips[i] = x;
                } else {//Vertically down 1 button
                    x = x - 5;
                    enemyShips[i] = x;
                }
            } //------------------------------------------------------------------------------End of Four Corners
            //------------------------------------------------------------------------------Checks for Edge Spots
            else if (x <= 5 || x > 20) {                                        //Checks to see if it is in the top or bottom row
                if (eS == 0) { //To the left one spot
                    x++;
                    enemyShips[i] = x;
                }
                if (eS == 1) {//To the right one spot
                    x--;
                    enemyShips[i] = x;
                }
                if (eS == 2) {                                                  //Adjusts the Veritical of the grid
                    if (x <= 5) {
                        x = x + 5;
                        enemyShips[i] = x;
                    } else {
                        x = x - 5;
                        enemyShips[i] = x;
                    }
                }
//------------------------------------------------------------------------------
            } else if (x % 5 == 0 || x == 9 || x == 14 || x == 19) {            //Left or Right Column
                if (eS == 0) { //To the left one spot
                    x = x - 5;
                    enemyShips[i] = x;
                }
                if (eS == 1) {//To the right one spot
                    x = x + 5;
                }
                enemyShips[i] = x;

                if (eS == 2) {                                                  //Adjusts the Veritical of the grid
                    if (x % 5 == 0) {
                        x++;//Selects button to the right
                        enemyShips[i] = x;
                    } else {
                        x--;        //Selects the button to the left
                        enemyShips[i] = x;
                    }
                }
            } else {//Middle of the board
                if (eS == 0) { //To the right one spot
                    x++;
                }
                if (eS == 1) {//To the left one spot
                    x--;
                }
                if (eS == 2) {//Down one spot
                    x = x + 5;
                }
            }
            if (eS == 3) {
                x = x - 5;
            }
            enemyShips[i] = x;

            if (battleSquare[x].getText().equals("SHIP")) {
                System.out.println("Battle Square[x] = " + battleSquare[x]);
                i = i - 2;
                setEnemyShips();
            }
            enemyShips[i] = x;
            battleSquare[x].setText("SHIP");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < MAX_BUTTONS; i++) {

            if (e.getSource() == battleSquare[i]) {
                battleSquare[i].setEnabled(false);
                //   for (int z = 0; z < 6; z++) {
                if (battleSquare[i].getText().equals("SHIP")) {
                    infoPanel.increaseHit();

                } else {
                    infoPanel.increaseMiss();
                }
            }
        }
    }
}
