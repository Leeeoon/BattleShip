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
    
    ImageIcon boomIcon;

    public EnemyPanel(InfoPanel infoPanel) {

        super();
        layout = new GridLayout(5, 5);
        setLayout(layout);
        
        boomIcon = new ImageIcon("boom.png");

        this.infoPanel = infoPanel;

        battleSquare = new JButton[MAX_BUTTONS];

        for (int shipNumber = 0; shipNumber < MAX_BUTTONS; shipNumber++) {
            battleSquare[shipNumber] = new JButton();
            battleSquare[shipNumber].addActionListener(this);
            add(battleSquare[shipNumber]);
        }
    }
/*
 * setEnemyShips uses the random double function, converts it to an integer
 * USes this to pick a spot on the board and check if it is in use
 * If it is it resets the 'shipNumber' counter and does that 'shipNumber' over again.
 * If the location is clean then it is set to the enemyShip array. (Might be useless)
 * The next step is to get the next portion of the ship on the board. This is where
 * the cS,eS, and mS, variables come to play. The corner's have two options
 * the edges have 3 and the center 4.
 * 
 * If a second space is used then the ship as a whole is redone(shipNumber.e. two spaces)
 */
    public void setEnemyShips() {
        for (int shipNumber = 0; shipNumber < 6; shipNumber++) {                
            double temp = Math.random() * 25;                                 //Finds the first random number out of all
            int shipLoc = (int) temp;
            int y = shipLoc;
            if (battleSquare[shipLoc].getText().equals("SHIP")) {//If the random number is already used then this will reset the 'shipNumber' to redo the attempt
                shipNumber--;
            } else {
                battleSquare[shipLoc].setText("SHIP");
                enemyShips[shipNumber] = shipLoc;                                              //Sets the first number of the enemyShip                                                    //Used so that 
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
                } 
//------------------------------------------------------------------------------End of Four Corners
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
                    enemyShips[shipNumber] = shipLoc;
                    battleSquare[shipLoc].setText("SHIP");
                }
            }
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
                if (battleSquare[i].getText().equals("SHIP")) {
                    infoPanel.increaseHit();
                    battleSquare[i].setText("");
                    battleSquare[i].setIcon(boomIcon);
                } else {
                    infoPanel.increaseMiss();
                }
            }
        }
    }
}
