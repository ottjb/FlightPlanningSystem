package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FlightPlanningSystemGUI extends JFrame implements ActionListener {

    JPanel panelLeft = new JPanel();

    Dimension btnSize = new Dimension(100, 50);

    // Initializing Main Menu Objects
    private JPanel mainMenuPanel;
    private JButton jbtFlightPlan;
    private JButton jbtAirplaneManager;
    private JButton jbtAirportManager;
    private JButton jbtExit;

    // Initializing Flight Plan Objects

    // Initializing Airplane Manager Objects
    private JPanel airplaneManagerPanel;
    private JButton jbtAddAirplane;
    private JButton jbtDeleteAirplane;
    private JButton jbtEditAirplane;
    private JButton jbtDisplayAirplane;
    private JButton jbtCancelAirplaneManager;

    // Initializing Airport Manager Objects
    private JPanel airportManagerPanel;
    private JButton jbtAddAirport;
    private JButton jbtDeleteAirport;
    private JButton jbtEditAirport;
    private JButton jbtDisplayAirport;
    private JButton jbtCancelAirportManager;

    public FlightPlanningSystemGUI() {

        setLayout(new GridLayout(1, 2, 3, 0));

        // Main Menu Panel
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jbtFlightPlan = new JButton("<html><center>New Flight Plan</center></html>");
        jbtAirplaneManager = new JButton("<html><center>Airplane Manager</center></html>");
        jbtAirportManager = new JButton("<html><center>Airport Manager</center></html>");
        jbtExit = new JButton("<html><center>Exit</center></html>");

        JButton[] mainMenuButtons = { jbtFlightPlan, jbtAirplaneManager, jbtAirportManager, jbtExit };

        for (JButton jbt : mainMenuButtons) {
            jbt.setPreferredSize(btnSize);
            jbt.addActionListener(this);
            mainMenuPanel.add(jbt);
        }


        // Airplane Manager Panel
        airplaneManagerPanel = new JPanel();
        airplaneManagerPanel.setLayout(new FlowLayout());
        jbtAddAirplane = new JButton("<html><center>Add Airplane</center></html>");
        jbtDeleteAirplane = new JButton("<html><center>Delete Airplane</center></html>");
        jbtEditAirplane = new JButton("<html><center>Edit Airplane</center></html>");
        jbtDisplayAirplane = new JButton("<html><center>Display Airplane</center></html>");
        jbtCancelAirplaneManager = new JButton("<html><center>Cancel</center></html>");

        JButton[] airplaneManagerButtons = { jbtAddAirplane, jbtDeleteAirplane, jbtEditAirplane, jbtDisplayAirplane,
                jbtCancelAirplaneManager };

        for (JButton jbt : airplaneManagerButtons) {
            jbt.setPreferredSize(btnSize);
            jbt.addActionListener(this);
            airplaneManagerPanel.add(jbt);
        }

        // Airport Manager Panel
        airportManagerPanel = new JPanel();
        airportManagerPanel.setLayout(new FlowLayout());
        jbtAddAirport = new JButton("<html><center>Add Airport</center></html>");
        jbtDeleteAirport = new JButton("<html><center>Delete Airport</center></html>");
        jbtEditAirport = new JButton("<html><center>Edit Airport</center></html>");
        jbtDisplayAirport = new JButton("<html><center>Display Airport</center></html>");
        jbtCancelAirportManager = new JButton("<html><center>Cancel</center></html>");

        JButton[] airportManagerButtons = { jbtAddAirport, jbtDeleteAirport, jbtEditAirport, jbtDisplayAirport,
                jbtCancelAirportManager };

        for (JButton jbt : airportManagerButtons) {
            jbt.setPreferredSize(btnSize);
            jbt.addActionListener(this);
            airportManagerPanel.add(jbt);
        }

        // Initializing the Main Menu
        add(panelLeft);
        add(mainMenuPanel);
    }

    public void actionPerformed(ActionEvent e) {
        // Main Menu Buttons
        if (e.getSource() == jbtFlightPlan) {
            System.out.println("Flight Plan");
        } else if (e.getSource() == jbtAirplaneManager) {
            openSubPanel(mainMenuPanel, airplaneManagerPanel);
        } else if (e.getSource() == jbtAirportManager) {
            openSubPanel(mainMenuPanel, airportManagerPanel);
        } else if (e.getSource() == jbtExit) {
            System.out.println("Exiting the program");
            System.exit(0);
        }
        // Airplane Manager Buttons
        else if (e.getSource() == jbtAddAirplane) {
            System.out.println("Add Airplane");
        } else if (e.getSource() == jbtDeleteAirplane) {
            System.out.println("Delete Airplane");
        } else if (e.getSource() == jbtEditAirplane) {
            System.out.println("Edit Airplane");
        } else if (e.getSource() == jbtDisplayAirplane) {
            System.out.println("Display Airplane");
        } else if (e.getSource() == jbtCancelAirplaneManager) {
            openSubPanel(airplaneManagerPanel, mainMenuPanel);
        }

        // Airport Manager Buttons
        else if (e.getSource() == jbtAddAirport) {
            System.out.println("Add Airport");
        } else if (e.getSource() == jbtDeleteAirport) {
            System.out.println("Delete Airport");
        } else if (e.getSource() == jbtEditAirport) {
            System.out.println("Edit Airport");
        } else if (e.getSource() == jbtDisplayAirport) {
            System.out.println("Display Airport");
        } else if (e.getSource() == jbtCancelAirportManager) {
            openSubPanel(airportManagerPanel, mainMenuPanel);
        }
    }

    public void openSubPanel(JPanel currentPanel, JPanel newPanel) {
        this.remove(currentPanel);
        this.add(newPanel);
        revalidate();
        repaint();
    }
}
