/** Slot Machine Videogame (Representation by means of a video game of the operation of the slot machines)
 Copyright (C) 2020  Pablo Pozos Aguilar
 This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 This is free software, and you are welcome to redistribute it
 under certain conditions; type `show c' for details.

 The hypothetical commands `show w' and `show c' should show the appropriate
 parts of the General Public License.  Of course, your program's commands
 might be different; for a GUI interface, you would use an "about box".

 You should also get your employer (if you work as a programmer) or school,
 if any, to sign a "copyright disclaimer" for the program, if necessary.
 For more information on this, and how to apply and follow the GNU GPL, see
 <https://www.gnu.org/licenses/>.

 The GNU General Public License does not permit incorporating your program
 into proprietary programs.  If your program is a subroutine library, you
 may consider it more useful to permit linking proprietary applications with
 the library.  If this is what you want to do, use the GNU Lesser General
 Public License instead of this License.  But first, please read
 <https://www.gnu.org/licenses/why-not-lgpl.html>. **/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class UI extends JFrame{
    private JButton btnReel1;
    private JButton btnReel2;
    private JButton btnReel3;
    private JPanel buttonAreaPanel;
    private JPanel displayArePanel;
    private JPanel reel1Panel;
    private JPanel reel2Panel;
    private JPanel reel3Panel;
    private JButton btnAddCoin;
    private JButton btnBetOne;
    private JButton btnBetMax;
    private JButton btnReset;
    private JButton btnStats;
    private JButton btnNewGame;
    private JLabel lblCreditArea;
    private JLabel lblBetArea;
    private JButton btnSpin;
    private SlotMachineM controller;
    private Timer time1;
    private Timer time2;
    private Timer time3;

    // Monitor the spinning
    private int round1, round2, round3;
    // count of each reel
    // Checks the reels are spinning
    private boolean isSpinning;


    public UI() {
        super("Slot Machine");
        // initializes the controller
        controller = new SlotMachineM();
        // setups GUI components
        prepare_GUI();
        addListeners();
        // sets the name of the initial player
        String playerName = JOptionPane.showInputDialog(this, "Enter your name to continue");
        controller.getPlayer().setName(playerName);
    }

    private void prepare_GUI() {
        setLayout(new BorderLayout());
        // creating the main sections of this windows
        createButtonAreaPannel();
        createReels();
        createDisplayAreaPannel();
        // customizing components
        setComponentPadding();
        setComponentFont();
        // adding components to the windows layout
        add(buttonAreaPanel, BorderLayout.NORTH);
        add(reel1Panel, BorderLayout.WEST);
        add(reel2Panel, BorderLayout.CENTER);
        add(reel3Panel, BorderLayout.EAST);
        add(displayArePanel, BorderLayout.SOUTH);

        setSize(900, 700);
        setLocationRelativeTo(null); // appears the windows in the center of the
        // screen
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack(); // removes unnecessary space in the window
        setVisible(true);
    }


    private void addListeners() {
        // listener for the "Statistics" button
        btnStats.addActionListener(event -> {
            // Last game Played
            if (controller.getPlayer().getWonList().size() == 0 && controller.getPlayer().getLostList().size() == 0) {
                showMsgBox("No game records available",
                        "You must have played at least once in order to view the statistics", 0);
            } else {
                new SlotMachineV(controller.getPlayer()); // Display the statistics game
            }
        });

        // listener for the "Add Coin" button
        btnAddCoin.addActionListener(event -> updateCreditAndBetLabels(1, 0));

        // listener for the "Bet One" button
        btnBetOne.addActionListener(event -> {
            updateCreditAndBetLabels(0, 1);
        });

        // listener for the "Bet Max" button
        btnBetMax.addActionListener(event -> {
            updateCreditAndBetLabels(0, 5);
        });

        // listener for the "Reset" button
        btnReset.addActionListener(event -> {
            // ensures that the player has bet at least one coin
            if (controller.getPlayer().getBetAmount() != 0) {
                controller.resetBet();
                updateCreditAndBetLabels(0, 0);
            } else {
                showMsgBox("Unable to reset coins", "You haven't bet any coins yet!", 0);
            }
        });

        // listener for the "New Game" button
        btnNewGame.addActionListener(event -> {
            // setting new player's name
            String playerName = JOptionPane.showInputDialog(this, "Enter your name to continue");
            controller.startNewGame(playerName);

            // loading the defaults
            updateCreditAndBetLabels(0, 0);
            btnReel1.setIcon(controller.createIcon("images/mango.png"));
            btnReel2.setIcon(controller.createIcon("images/mango.png"));
            btnReel3.setIcon(controller.createIcon("images/mango.png"));
        });

        // listener for the "Spin" button
        btnSpin.addActionListener(event -> beginSpinning());

        // listeners for the three reels
        btnReel1.addActionListener(event -> stopSpinning(1));
        btnReel2.addActionListener(event -> stopSpinning(2));
        btnReel3.addActionListener(event -> stopSpinning(3));

        // listener for the window
        addWindowListener(new WindowAdapter() {

            // capturing the window closing event
            @Override
            public void windowClosing(WindowEvent e) {
                int isConfirmed = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to exit?",
                        "This application is about to be closed", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (isConfirmed == JOptionPane.YES_OPTION) {// checks player's
                    // choice
                    dispose(); // destroys the window object
                }
            }
        });
    }

    // Create Bottons In the panel
    private void createButtonAreaPannel() {
        buttonAreaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        btnAddCoin = new JButton("Add Coin");
        btnBetOne = new JButton("Bet");
        btnBetMax = new JButton("Bet Max");
        btnReset = new JButton("Reset");
        btnStats = new JButton("Inform");
        btnNewGame = new JButton("New Game");

        buttonAreaPanel.add(btnAddCoin);
        buttonAreaPanel.add(btnBetOne);
        buttonAreaPanel.add(btnBetMax);
        buttonAreaPanel.add(btnReset);
        buttonAreaPanel.add(btnStats);
        buttonAreaPanel.add(btnNewGame);
    }

    // Create the reels
    private void createReels() {
        reel1Panel = new JPanel(new FlowLayout());
        reel2Panel = new JPanel(new FlowLayout());
        reel3Panel = new JPanel(new FlowLayout());

        btnReel1 = new JButton();
        btnReel2 = new JButton();
        btnReel3 = new JButton();

        // setting initial symbols for the reels
        btnReel1.setIcon(controller.createIcon("images/mango.png"));
        btnReel2.setIcon(controller.createIcon("images/mango.png"));
        btnReel3.setIcon(controller.createIcon("images/mango.png"));

        btnReel1.setBackground(Color.LIGHT_GRAY);
        btnReel2.setBackground(Color.LIGHT_GRAY);
        btnReel3.setBackground(Color.LIGHT_GRAY);

        reel1Panel.add(btnReel1);
        reel2Panel.add(btnReel2);
        reel3Panel.add(btnReel3);
    }

    // Create Display
    private void createDisplayAreaPannel() {
        displayArePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 10));

        lblCreditArea = new JLabel("Credit: 10");
        lblBetArea = new JLabel("Bet: 0");
        btnSpin = new JButton("Spin");

        displayArePanel.add(lblCreditArea);
        displayArePanel.add(btnSpin);
        displayArePanel.add(lblBetArea);
    }

    //  UI Components
    private void setComponentPadding() {
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        reel1Panel.setBorder(padding);
        reel2Panel.setBorder(padding);
        reel3Panel.setBorder(padding);

        displayArePanel.setBorder(padding);
        buttonAreaPanel.setBorder(padding);
    }


    private void setComponentFont() {
        Font font = new Font("Comic Sans MS", Font.BOLD, 20);

        btnAddCoin.setFont(font);
        btnBetOne.setFont(font);
        btnBetMax.setFont(font);
        btnReset.setFont(font);
        btnStats.setFont(font);
        btnNewGame.setFont(font);
        btnSpin.setFont(font);

        font = new Font("Comic Sans MS", Font.BOLD, 30);

        lblCreditArea.setFont(font);
        lblBetArea.setFont(font);

        font = new Font("Arial", Font.PLAIN, 15);

        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.messageFont", font);
    }



    // Stars the Spinning
    // Using Threads
    private void beginSpinning() {
        // check for the minimum bet amount and whether the reels are spinning
        if (controller.getPlayer().getBetAmount() > 0 && !isSpinning) {
            // setting to default values
            // Reel per Spin
            round1 = 0;
            round2 = 0;
            round3 = 0;
            isSpinning = true;


            // Defining threads and thread jobs with timers for the spinning of the three reels
            controller.Thread1 = new Thread(() -> {
                // defining the timer
                time1 = new Timer(100, event -> {
                    // that fires each time
                    // the timer stops
                    btnReel1.setIcon(// changing the symbol
                            controller.createIcon(controller.reelList.get(0).spin().get(round1).getImage()));

                    // assigns the currently stuck symbol in the reel to a
                    // variable
                    controller.setSymbolreel1(controller.reelList.get(0).spin().get(round1));

                    // if the 6 symbol sequence is over, resets the counter
                    if (round1 == controller.reelList.get(0).spin().size() - 1) {
                        round1 = 0;
                    } else {
                        round1++;
                    }
                });

                // removing the initial delay and starting the timer
                time1.setInitialDelay(0);
                time1.start();
            });
            controller.Thread1.start(); // starting the new thread

            controller.Thread2 = new Thread(() -> {
                time2 = new Timer(100, event -> {
                    btnReel2.setIcon(
                            controller.createIcon(controller.reelList.get(1).spin().get(round2).getImage()));
                    controller.setSymbolreel2(controller.reelList.get(1).spin().get(round2));
                    if (round2 == controller.reelList.get(1).spin().size() - 1) {
                        round2 = 0;
                    } else {
                        round2++;
                    }
                });
                time2.setInitialDelay(0);
                time2.start();
            });
            controller.Thread2.start();

            controller.Thread3 = new Thread(() -> {
                time3 = new Timer(100, event -> {
                    btnReel3.setIcon(
                            controller.createIcon(controller.reelList.get(2).spin().get(round3).getImage()));
                    controller.setSymbolreel3(controller.reelList.get(2).spin().get(round3));
                    if (round3 == controller.reelList.get(2).spin().size() - 1) {
                        round3 = 0;
                    } else {
                        round3++;
                    }
                });
                time3.setInitialDelay(0);
                time3.start();
            });
            controller.Thread3.start();
        } else {
            // identifying the relevant error message
            if (isSpinning) {
                showMsgBox("No Actions", "The reels are already spinning!", 0);
            } else {
                showMsgBox("No credits for spin", "Insert 1 coin to spin", 0);
            }
        }
    }


    private void stopSpinning(int reelNo) {
        if (isSpinning) { // checks if that reel has already stopped
            try {
                if (reelNo == 1) {
                    time1.stop();
                } else if (reelNo == 2) {
                    time2.stop();
                } else if (reelNo == 3) {
                    time3.stop();
                }

                // checks whether all the three reels have stopped spinning
                if (!time1.isRunning() && !time2.isRunning() && !time3.isRunning()) {
                    try {
                        // waiting for all the spinning threads to finish
                        controller.Thread1.join();
                        controller.Thread2.join();
                        controller.Thread3.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // decide and save the result of the game
                        int wonCredits = controller.saveGameResult();
                        if (wonCredits > 0) { // game won
                            showMsgBox("Congratulations", "You won " + wonCredits + " credits!", 1);
                        } else if (wonCredits == 0)  { // game lost
                            showMsgBox("Game over", "You lost! Try again.", 1);
                        }else{ // game tied
                            wonCredits = 0;
                            showMsgBox("Congratulations", "You won, but only two symbols matched!", 1);
                        }
                        updateCreditAndBetLabels(wonCredits, 0);
                        isSpinning = false;
                    }
                }
            } catch (NullPointerException e) {// occurs when spinning threads
                // and timers haven't been
                // initialized
                showMsgBox("Problems", "You haven't started spinning the reels yet!", 2);
            }
        }

    }


    private void updateCreditAndBetLabels(int creditAmount, int betAmount) {
        lblBetArea.setText("Bet: " + controller.addBet(betAmount));
        lblCreditArea.setText("Credit: " + controller.addCoin(creditAmount));
    }


    private void showMsgBox(String title, String message, int messageType) {
        switch (messageType) {
            case 1:
                messageType = JOptionPane.INFORMATION_MESSAGE;
                break;
            case 2:
                messageType = JOptionPane.WARNING_MESSAGE;
                break;
            default:
                messageType = JOptionPane.ERROR_MESSAGE;
                break;
        }
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }


}
