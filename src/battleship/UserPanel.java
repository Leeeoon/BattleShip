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
    InfoPanel infoPanel;
    int[] userShips = new int[6];

    int enemyHits;

    ImageIcon boomIcon;

    final static int MAX_SCORE = 6;
    final static int MAX_BUTTONS = 25;

    public UserPanel(InfoPanel infoPanel) {

        super();

        layout = new GridLayout(5, 5);
        setLayout(layout);
        canAttack = true;
        
        boomIcon = new ImageIcon("boom.png");
        this.infoPanel = infoPanel;

        enemyHits = 0;

        battleSquare = new JButton[MAX_BUTTONS];

        for (int i = 0; i < MAX_BUTTONS; i++) {
            battleSquare[i] = new JButton();
            battleSquare[i].addActionListener(this);
            add(battleSquare[i]);
        }

        setup();
    }

    public void setup() {
        for (int shipNumber = 0; shipNumber < 6; shipNumber++) {
            double temp = Math.random() * 25;                                 //Finds the first random number out of all
            int shipLoc = (int) temp;
            int y = shipLoc;
            if (battleSquare[shipLoc].getText().equals("SHIP")) {//If the random number is already used then this will reset the 'shipNumber' to redo the attempt
                shipNumber--;
            } else {
                battleSquare[shipLoc].setText("SHIP");
                userShips[shipNumber] = shipLoc;                                              //Sets the first number of the enemyShip                                                    //Used so that 
//==============================================================================Setting the Ships in random locations        
                double cornerSpot = Math.random() * 2;                          //These are based on the number of options for each unique location
                double edgeSpot = Math.random() * 3;
                double middleSpot = Math.random() * 4;
                int cS = (int) cornerSpot;                                      //Changing to int
                int eS = (int) edgeSpot;
                int mS = (int) middleSpot;
                shipNumber++;
//------------------------------------------------------------------------------Four Corners of the gridlayout        
                if (shipLoc == 0) {                                                   //Top Left Corner
                    if (cS == 0) {
                        shipLoc++;
                    } else {
                        shipLoc = 5;
                    }
//------------------------------------------------------------------------------
                } else if (shipLoc == 4) {                                            //Top Right Corner
                    if (cS == 0) {
                        shipLoc--;
                    } else {
                        shipLoc = shipLoc + 5;
                    }
//------------------------------------------------------------------------------
                } else if (shipLoc == 20) {                                               //Bottom Left Corner
                    if (cS == 0) {
                        shipLoc++;
                    } else {
                        shipLoc = shipLoc - 5;
                    }
//------------------------------------------------------------------------------
                } else if (shipLoc == 24) {                                               //Bottom Right Corner
                    if (cS == 0) {
                        shipLoc--;
                    } else {
                        shipLoc = shipLoc - 5;
                    }
                } //------------------------------------------------------------------------------End of Four Corners
                //------------------------------------------------------------------------------Checks for Edge Spots
                else if (shipLoc <= 5 || shipLoc > 20) {                                        //Checks to see if it is in the top or bottom row
                    if (eS == 0) {
                        shipLoc++;
                    }
                    if (eS == 1) {
                        shipLoc--;
                    }
                    if (eS == 2) {
                        if (shipLoc <= 5) {
                            shipLoc = shipLoc + 5;
                        } else if (eS == 3) {
                            shipLoc = shipLoc - 5;
                        }
                    }
//------------------------------------------------------------------------------
                } else if (shipLoc % 5 == 0 || shipLoc == 9 || shipLoc == 14 || shipLoc == 19) { //Left or Right Column
                    if (eS == 0) {
                        shipLoc = shipLoc - 5;
                    }
                    if (eS == 1) {
                        shipLoc = shipLoc + 5;
                    }
                    if (eS == 2) {                                              //Adjusts the Veritical of the grid
                        if (shipLoc % 5 == 0) {
                            shipLoc++;
                        } else {
                            shipLoc--;
                        }
                    }
                } else {//------------------------------------------------------Middle of the board
                    if (mS == 0) { //To the right one spot
                        shipLoc++;
                    }
                    if (mS == 1) {//To the left one spot
                        shipLoc--;
                    }
                    if (mS == 2) {//Down one spot
                        shipLoc = shipLoc + 5;
                    }
                    if (mS == 3) {//Up one spot
                        shipLoc = shipLoc - 5;
                    }
                }
                if (battleSquare[shipLoc].getText().equals("SHIP")) {                 //if the second spot is used then 
                    shipNumber = shipNumber - 2;                                                  //this resets the whole ship so that it can be tried again
                    battleSquare[y].setText(" ");
                } else {
                    userShips[shipNumber] = shipLoc;
                    battleSquare[shipLoc].setText("SHIP");
                }
            }
        }
    }

    public void selectTarget() {
        infoPanel.whoseTurn.setText("Select an enemy space to attack.");
        // pause and wait for user input
        // user selects a spot to hit
    }

    public void enemyAttacks() {

        // will choose a random square to attack in UserPanel
        if (canAttack) {
            enemyTarget = rand.nextInt(25);                     // get a target
            while (!battleSquare[enemyTarget].isEnabled()) {
                // check to see if the JButton is enabled
                // if JButton is not enabled, get a new target
                enemyTarget = rand.nextInt(25);
            }
            battleSquare[enemyTarget].setEnabled(false);
            canAttack = false;

            if (battleSquare[enemyTarget].getText().equals("SHIP")) {
                enemyHits++;
                battleSquare[enemyTarget].setText("");
                battleSquare[enemyTarget].setIcon(boomIcon);
            }
            if (enemyHits == MAX_SCORE) {
                JOptionPane.showMessageDialog(null, "You Lost!");
                System.exit(0);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < MAX_BUTTONS; i++) {
            if (e.getSource() == battleSquare[i]) {

            }
        }

    }
}
