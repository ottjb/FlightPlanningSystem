import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AirplaneManagerPanel extends JPanel{
    JButton jbtAdd = new JButton("Add airplane");
    JButton jbtDelete = new JButton("Delete airplane");
    JButton jbtEdit = new JButton("Edit airplane");
    JButton jbtDisplay = new JButton("Display airplane");
    JButton jbtCancel = new JButton("Cancel");

    public AirplaneManagerPanel() {
        setLayout(new GridLayout(5, 1, 0, 75));
        add(jbtAdd);
        add(jbtDelete);
        add(jbtEdit);
        add(jbtDisplay);
        add(jbtCancel);
    }
}
