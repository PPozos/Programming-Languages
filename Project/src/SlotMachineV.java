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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SlotMachineV extends JFrame{
    private JButton btnSave;
    private JLabel lblPlayerName;
    private JLabel lblTotalWIns;
    private JLabel lblTotalLoses;
    private JPanel buttonAreaPanel;
    private JPanel displayArePanel;
    private JPanel panel;
    private SlotMachine controller;


    public SlotMachineV(Player player) {
        // name of the window
        super("Game Statistics");
        controller = new SlotMachine(player);

        // setups GUI components
        prepareGUI();
        addListeners();
    }

    private void prepareGUI() {
        setLayout(new BorderLayout());

        // setting component texts
        lblPlayerName = new JLabel("Player Name: " + controller.getPlayer().getName());
        lblTotalLoses = new JLabel("Total Loses: " + controller.getPlayer().getLostList().size());
        lblTotalWIns = new JLabel("Total Wins: " + controller.getPlayer().getWonList().size());
        btnSave = new JButton("Save statistics");

        lblTotalWIns.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblTotalLoses.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblPlayerName.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        double totalCredits = 0.0;

        for (int i = 0; i < controller.getGameRecordList().size(); i++) {
            totalCredits += controller.getGameRecordList().get(i);
        }

        // setting component layouts
        buttonAreaPanel = new JPanel(new FlowLayout());
        displayArePanel = new JPanel(new GridLayout(0, 1));
        panel = new JPanel(new FlowLayout());

        displayArePanel.add(lblPlayerName);
        displayArePanel.add(lblTotalWIns);
        displayArePanel.add(lblTotalLoses);
        buttonAreaPanel.add(btnSave);

        setComponentFont();
        setComponentPadding();

        // adding components to the windows's layout
        add(displayArePanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(buttonAreaPanel, BorderLayout.SOUTH);

        setSize(800, 300);
        setLocationRelativeTo(this);// appears the windows in the center of the
        // screen
        pack();// removes unnecessary space in the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    private void addListeners() {
        btnSave.addActionListener(event -> {
            controller.saveStats();
            JOptionPane.showMessageDialog(this, "Statistics successfully saved!");
        });
    }

    private void setComponentPadding() {
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        displayArePanel.setBorder(padding);
        buttonAreaPanel.setBorder(padding);
        padding = BorderFactory.createEmptyBorder(0, 30, 5, 30);
        lblPlayerName.setBorder(padding);
        lblTotalWIns.setBorder(padding);
        lblTotalLoses.setBorder(padding);
    }


    private void setComponentFont() {
        Font font = new Font("Comic Sans MS", Font.BOLD, 20);
        lblTotalLoses.setFont(font);
        lblTotalWIns.setFont(font);
        btnSave.setFont(font);
        font = new Font("Comic Sans MS", Font.BOLD, 25);
        lblPlayerName.setFont(font);
        font = new Font("Comic Sans MS", Font.BOLD, 15);
    }
}
