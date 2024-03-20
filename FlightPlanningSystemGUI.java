import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlightPlanningSystemGUI extends JFrame{

    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();

    JButton jbtFlightPlan = new JButton("New Flight Plan");
    JButton jbtAirportManager = new JButton("Airport Manager");

    public FlightPlanningSystemGUI() {

        setLayout(new GridLayout(1, 2, 3, 0));
        //panelRight.setLayout(new GridLayout(4, 1, 0, 100));
        
        JButton jbtAirplaneManager = new JButton("Airplane Manager");
        jbtAirplaneManager.addActionListener(e -> {
            panelRight.removeAll();
            AirplaneManagerPanel airplaneManagerGUI = new AirplaneManagerPanel();
            panelRight.add(airplaneManagerGUI);
            panelRight.revalidate();
            panelRight.repaint();
        });

        JButton jbtExit = new JButton("Exit");

        panelRight.add(jbtFlightPlan);
        panelRight.add(jbtAirplaneManager);
        panelRight.add(jbtAirportManager);
        panelRight.add(jbtExit);

        add(panelLeft);
        add(panelRight);
    }
}
