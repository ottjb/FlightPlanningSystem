package GUI;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlightPlanningSystemGUI extends JFrame implements ActionListener {

    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();

    JButton jbtFlightPlan = new JButton("New Flight Plan");
    private JButton jbtAirplaneManager;
    JButton jbtAirportManager = new JButton("Airport Manager");
    private JButton jbtExit;

    public FlightPlanningSystemGUI() {

        setLayout(new GridLayout(1, 2, 3, 0));
        panelRight.setLayout(new GridLayout(4, 1, 0, 100));

        jbtAirplaneManager = new JButton("Airplane Manager");
        jbtExit = new JButton("Exit");

        jbtAirplaneManager.addActionListener(this);
        jbtExit.addActionListener(this);

        panelRight.add(jbtFlightPlan);
        panelRight.add(jbtAirplaneManager);
        panelRight.add(jbtAirportManager);
        panelRight.add(jbtExit);

        add(panelLeft);
        add(panelRight);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtAirplaneManager) {
            panelRight.removeAll();
            // remove layout
            AirplaneManagerPanel airplaneManagerPanel = new AirplaneManagerPanel();
            panelRight.add(airplaneManagerPanel);
            panelRight.revalidate();
            panelRight.repaint();
        } else if (e.getSource() == jbtExit) {
            System.out.println("Exiting the program");
            System.exit(0);
        }
    }
}
