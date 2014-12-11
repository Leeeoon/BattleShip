package battleship;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class MainPanel extends JPanel implements ActionListener {
 
    UserPanel userPanel;
    EnemyPanel enemyPanel;
    InfoPanel infoPanel;
    ArrayList<Ship> player;
    ArrayList<Ship> enemy;

    // These were not in the design but ended up being necessary
    static GridLayout layout;
    Timer tim;
    int delay;
    boolean isTimer;

    // These were not in the design but ended up being incredibly useful
    final static int MAX_HITS = 6;
    final static int MAX_BUTTONS = 25;

    public MainPanel() {

        super();

        layout = new GridLayout(1, 3);
        setLayout(layout);

        delay = 1;

        tim = new Timer(delay, this);

        infoPanel = new InfoPanel();
        userPanel = new UserPanel(infoPanel);
        setUserShips();
        enemyPanel = new EnemyPanel(infoPanel);
        setEnemyShips();
        add(userPanel);
        add(infoPanel);
        add(enemyPanel);
        
        player = new ArrayList();
        enemy = new ArrayList();
        
        for (int i = 0; i < MAX_BUTTONS; i++) {
            enemyPanel.battleSquare[i].addActionListener(this);
        }
        
    }
    
    public void setUserShips() {
        for (int shipNumber = 0; shipNumber < 6; shipNumber++) {
            double temp = Math.random() * 25;                                 //Finds the first random number out of all
            int shipLoc = (int) temp;
            int y = shipLoc;
            if (userPanel.battleSquare[shipLoc].getText().equals("SHIP")) {//If the random number is already used then this will reset the 'shipNumber' to redo the attempt
                shipNumber--;
            } else {
                userPanel.battleSquare[shipLoc].setText("SHIP");
                userPanel.userShips[shipNumber] = shipLoc;                                              //Sets the first number of the enemyShip                                                    //Used so that 
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
                if (userPanel.battleSquare[shipLoc].getText().equals("SHIP")) {                 //if the second spot is used then 
                    shipNumber = shipNumber - 2;                                                  //this resets the whole ship so that it can be tried again
                    userPanel.battleSquare[y].setText(" ");
                } else {
                    userPanel.userShips[shipNumber] = shipLoc;
                    userPanel.battleSquare[shipLoc].setText("SHIP");
                }
            }
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
            if (enemyPanel.battleSquare[shipLoc].getText().equals(" ")) {//If the random number is already used then this will reset the 'shipNumber' to redo the attempt
                shipNumber--;
            } else {
                enemyPanel.battleSquare[shipLoc].setText(" ");
                enemyPanel.enemyShips[shipNumber] = shipLoc;                                              //Sets the first number of the enemyShip                                                    //Used so that 
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
                if (enemyPanel.battleSquare[shipLoc].getText().equals(" ")) {                 //if the second spot is used then 
                    shipNumber = shipNumber - 2;                                                  //this resets the whole ship so that it can be tried again
                    enemyPanel.battleSquare[y].setText("  ");
                } else {
                    enemyPanel.enemyShips[shipNumber] = shipLoc;
                    enemyPanel.battleSquare[shipLoc].setText(" ");
                }
            }
        }
    }
    
    /*
    This was in the design but we didn't end up using it
    */
    public void checkHits()
    {
        
    }
    
    /*
    This was in the design but we didn't end up using it
    */
    public void checkSunk()
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < MAX_BUTTONS; i++) {
            if (e.getSource() == enemyPanel.battleSquare[i]) {

                tim.start();
                userPanel.canAttack = true;

            }
            if (e.getSource() == tim) {
                userPanel.enemyAttacks();
                userPanel.canAttack = false;
                tim.stop();
            }
        }
    }
}
