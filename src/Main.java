import Classes.InputArgs;
import Classes.FileWorker;
import Classes.Solutions;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Scanner;

public class Main extends JFrame {

    public Main() {
        // Create default elements
        JPanel panel = new JPanel();
        Font defaultFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);

        // Settings
        UIManager.put("defaultFont", defaultFont);
        setSize(800, 500);
        setTitle("Task8_SNU");
        setContentPane(panel);
        setFont(defaultFont);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create additional elements
        JTable table = new JTable(2,2);


        // Contain elements
        panel.add(table);

        // Finally draw GUI
        for(Component component : getComponents())
            component.setFont(defaultFont);

    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}