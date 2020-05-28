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


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SlotMachine {
    private Player player;
    private List<Double> gameRecordList;

    public SlotMachine(Player player) {
        this.player = player;
        gameRecordList = new ArrayList<Double>(); // a super set of the game
        // records
        gameRecordList.addAll(player.getWonList());
        gameRecordList.addAll(player.getLostList());
    }


    //saves the statistics of the current player
    public void saveStats() {
        try {
            FileWriter fw = new FileWriter(LocalDate.now() + ".txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Name: " + player.getName());
            pw.println("Total Wins: " + player.getWonList().size());
            pw.println("Total Loses: " + player.getLostList().size());
            pw.println("___________________________________________________");
            pw.println();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getter method of the player variable
    public Player getPlayer() {
        return player;
    }

    // variable
    public List<Double> getGameRecordList() {
        return gameRecordList;
    }

}
