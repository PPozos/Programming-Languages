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

public class Symbol implements ISymbols {
    private String imagePath;
    private int value;

    public Symbol(String imageName, int value) {
        imagePath = "images/" + imageName;
        this.value = value;
    }

    public int[] compareValues(Symbol rhs) {
        int[] result = new int[2];
        result[1] = value;
        if (this.value == rhs.getValue()) {
            result[0] = 1;
        } else {
            result[0] = 0;
        }
        return result;
    }

    // getters and setter
    @Override
    public void setImage(String imageName) {
        imagePath = "images/" + imageName;
    }

    @Override
    public String getImage() {
        return imagePath;
    }

    @Override
    public void setValue(int v) {
        value = v;
    }
    @Override
    public int getValue() {
        return value;
    }

}

