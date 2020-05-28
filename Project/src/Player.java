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

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int creditAmount;
    private int betAmount;
    private List<Double> wonList;
    private List<Double> lostList;

    public Player(String name) {
        creditAmount = 10;
        this.name = name;
        wonList = new ArrayList<Double>();
        lostList = new ArrayList<Double>();
    }


    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount += creditAmount;
    }

    public List<Double> getWonList() {
        return wonList;
    }

    public List<Double> getLostList() {
        return lostList;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {

        this.betAmount += betAmount;
    }

    public String getName() {
        // checking whether the name variable is null or empty
        return (name == null || name.equals("")) ? "Enter one name Please" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
