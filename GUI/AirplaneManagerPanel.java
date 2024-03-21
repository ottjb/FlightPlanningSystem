package GUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AirplaneManagerPanel extends JPanel implements ActionListener{
    private JButton jbtAdd;
    private JButton jbtDelete;
    private JButton jbtEdit;
    private JButton jbtDisplay;
    private JButton jbtCancel;

    public AirplaneManagerPanel() {
        setBackground(java.awt.Color.BLUE);
        jbtAdd = new JButton("Add airplane");
        jbtDelete = new JButton("Delete airplane");
        jbtEdit = new JButton("Edit airplane");
        jbtDisplay = new JButton("Display airplane");
        jbtCancel = new JButton("Cancel");

        jbtAdd.setBounds(250, 0, 200, 200);

        jbtAdd.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(jbtAdd);
        add(jbtDelete);
        add(jbtEdit);
        add(jbtDisplay);
        add(jbtCancel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtAdd) {
            System.out.println("Add airplane");
        }
    }
}
