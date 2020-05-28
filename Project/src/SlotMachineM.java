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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SlotMachineM {
    public List<Reel> reelList;
    private Player player;
    public Thread Thread1;
    public Thread Thread2;
    public Thread Thread3;
    private Symbol Symbolreel1;
    private Symbol Symbolreel2;
    private Symbol Symbolreel3;
    private int matchSymbol;
    private boolean isTie;


    public SlotMachineM() {
        reelList = new ArrayList<Reel>();

        reelList.add(new Reel());
        reelList.add(new Reel());
        reelList.add(new Reel());

        player = new Player(null);
    }

    // Add Coins
    public int addCoin(int amount) {
        player.setCreditAmount(amount);
        return player.getCreditAmount();
    }

    // Add Bet (Apuesta)
    public int addBet(int amount) {
        // checks whether the player has enough credit coins
        if (player.getCreditAmount() >= amount) {
            player.setBetAmount(amount);
            player.setCreditAmount(-amount);
        } else {
            JOptionPane.showMessageDialog(null, "No te alcanzaaaa",
                    "No credits", JOptionPane.ERROR_MESSAGE);
        }
        return player.getBetAmount();
    }

    // Reset Apuesta
    public int resetBet() {
        player.setCreditAmount(player.getBetAmount());
        player.setBetAmount(-player.getBetAmount());
        return player.getCreditAmount();
    }

    // getter method of the player variable
    public Player getPlayer() {
        return player;
    }

    public void startNewGame(String playerName) {
        this.player = new Player(playerName);
    }

    public double calculateNettedAmount() {
        return (player.getBetAmount() / (player.getBetAmount() * 1.0 + player.getCreditAmount())) * 100;
    }

    // Calculate Credits
    public int calculateWonCredits() {
        return player.getBetAmount() * matchSymbol;
    }

    // Save Statics
    public int saveGameResult() {
        int wonCredits = 0;
        // Lost = 0, Tie = -1
        boolean isWon = isWon();
        if (isWon) {
            wonCredits = calculateWonCredits();
            player.getWonList().add((double) wonCredits);
            player.setBetAmount(-player.getBetAmount());
        } else if (!isWon && isTie) {
            wonCredits = -1;
            player.getWonList().add((double) wonCredits);
            isTie = false;
        } else {
            player.getLostList().add((double) -player.getBetAmount());
            player.setBetAmount(-player.getBetAmount());
        }

        return wonCredits;
    }

    // Result of the Game
    public boolean isWon() {

        // comparing the values of currently stuck stuck symbols in all the
        // three reels
        int[] result1 = Symbolreel1.compareValues(Symbolreel2);
        int[] result2 = Symbolreel1.compareValues(Symbolreel3);
        int[] result3 = Symbolreel3.compareValues(Symbolreel2);

        // finding the matching symbol if any
        if (result1[0] == 1 && result2[0] == 1 && result3[0] == 1) {
            if (result1[0] == 1) {
                matchSymbol = result1[1];
            } else if (result2[0] == 1) {
                matchSymbol = result2[1];
            } else if (result3[0] == 1) {
                matchSymbol = result3[1];
            }
            return true;
        } else if ((result1[0] == 1 || result2[0] == 1 || result3[0] == 1)
                && !(result1[0] == 1 && result2[0] == 1 && result3[0] == 1)) {
            isTie = true;
            return false;
        } else {
            return false;
        }
    }

    public ImageIcon createIcon(String path) {
        URL url = getClass().getResource(path);
        if (url == null) { // if the image is not found
            System.err.println("error opening: " + path);
        }
        ImageIcon icon = new ImageIcon(url);
        return icon;
    }

    // Getters and Seters
    public Symbol getSymbolreel1() {
        return Symbolreel1;
    }

    public void setSymbolreel1(Symbol Symbolreel1) {
        this.Symbolreel1 = Symbolreel1;
    }

    public Symbol getSymbolreel2() {
        return Symbolreel2;
    }

    public void setSymbolreel2(Symbol Symbolreel2) {
        this.Symbolreel2 = Symbolreel2;
    }

    public Symbol getSymbolreel3() {
        return Symbolreel3;
    }

    public void setSymbolreel3(Symbol Symbolreel3) {
        this.Symbolreel3 = Symbolreel3;
    }

}