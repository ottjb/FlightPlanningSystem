package GUI;

import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Managers.AirplaneManager;
import Managers.AirportManager;
import Objects.Airplane;
import Objects.Airport;

public class FlightPlanningSystemGUIV2 extends JFrame {
    private int screenHeight = 438;
    private int screenWidth = 615;

    AirplaneManager airplaneManager;
    AirportManager airportManager;

    Color sidebarBackgroundColor = new Color(0x4D4D4D);
    Color sidebarTextColor = new Color(0xFFFFFF);

    private boolean airplaneManagerOpen = false;
    private boolean airportManagerOpen = false;

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JButton airportManagerButton;
    private JButton addAirplaneButton;
    private JButton editAirplaneButton;
    private JButton deleteAirplaneButton;
    private JButton displayAirplaneButton;

    private JButton addAirportButton;
    private JButton editAirportButton;
    private JButton deleteAirportButton;
    private JButton displayAirportButton;

    public FlightPlanningSystemGUIV2() {
        setTitle("Flight Planning System");
        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);

        // Menu Sidebar
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBounds(0, 0, 130, 400);
        menuPanel.setBackground(sidebarBackgroundColor);

        JButton flightPlannerButton = new JButton("Flight Planner");
        flightPlannerButton.setHorizontalAlignment(JButton.LEFT);
        flightPlannerButton.setBounds(0, 0, 132, 50);
        flightPlannerButton.addActionListener(e -> {
            tabbedPane.setSelectedIndex(1);
        });
        flightPlannerButton.setBorderPainted(false);
        flightPlannerButton.setBackground(sidebarBackgroundColor);
        flightPlannerButton.setForeground(sidebarTextColor);
        menuPanel.add(flightPlannerButton);

        addAirplaneButton = new JButton("Add Airplane");
        addAirplaneButton.setHorizontalAlignment(JButton.LEFT);
        addAirplaneButton.setBounds(15, 100, 117, 25);
        addAirplaneButton.setFont(addAirplaneButton.getFont().deriveFont(10.9f));
        addAirplaneButton.setBorderPainted(false);
        addAirplaneButton.setBackground(sidebarBackgroundColor);
        addAirplaneButton.setForeground(sidebarTextColor);
        addAirplaneButton.addActionListener(e -> {
            tabbedPane.setSelectedIndex(2);
        });

        editAirplaneButton = new JButton("Edit Airplane");
        editAirplaneButton.setHorizontalAlignment(JButton.LEFT);
        editAirplaneButton.setBounds(15, 125, 117, 25);
        editAirplaneButton.setFont(editAirplaneButton.getFont().deriveFont(10.9f));
        editAirplaneButton.setBorderPainted(false);
        editAirplaneButton.setBackground(sidebarBackgroundColor);
        editAirplaneButton.setForeground(sidebarTextColor);

        deleteAirplaneButton = new JButton("Delete Airplane");
        deleteAirplaneButton.setHorizontalAlignment(JButton.LEFT);
        deleteAirplaneButton.setBounds(15, 150, 117, 25);
        deleteAirplaneButton.setFont(deleteAirplaneButton.getFont().deriveFont(10.9f));
        deleteAirplaneButton.setBorderPainted(false);
        deleteAirplaneButton.setBackground(sidebarBackgroundColor);
        deleteAirplaneButton.setForeground(sidebarTextColor);

        displayAirplaneButton = new JButton("Display Airplane");
        displayAirplaneButton.setHorizontalAlignment(JButton.LEFT);
        displayAirplaneButton.setBounds(15, 175, 117, 25);
        displayAirplaneButton.setFont(displayAirplaneButton.getFont().deriveFont(10.9f));
        displayAirplaneButton.setBorderPainted(false);
        displayAirplaneButton.setBackground(sidebarBackgroundColor);
        displayAirplaneButton.setForeground(sidebarTextColor);

        JButton airplaneManagerButton = new JButton("Airplane Manager");
        airplaneManagerButton.setHorizontalAlignment(JButton.LEFT);
        airplaneManagerButton.setBounds(-5, 50, 142, 50);
        airplaneManagerButton.addActionListener(e -> {
            if (airportManagerOpen) {
                menuPanel.remove(addAirportButton);
                menuPanel.remove(editAirportButton);
                menuPanel.remove(deleteAirportButton);
                menuPanel.remove(displayAirportButton);
                airportManagerOpen = false;
                //airportManager.close();
                revalidate();
                repaint();}
            if (!airplaneManagerOpen) {
                airplaneManager = new AirplaneManager();
                menuPanel.add(addAirplaneButton);
                menuPanel.add(editAirplaneButton);
                menuPanel.add(deleteAirplaneButton);
                menuPanel.add(displayAirplaneButton);
                airplaneManagerOpen = true;
                airportManagerButton.setBounds(0, 200, 132, 50);
                revalidate();
                repaint();
            } else {
                menuPanel.remove(addAirplaneButton);
                menuPanel.remove(editAirplaneButton);
                menuPanel.remove(deleteAirplaneButton);
                menuPanel.remove(displayAirplaneButton);
                airplaneManagerOpen = false;
                airplaneManager.close();
                airportManagerButton.setBounds(0, 100, 132, 50);
                revalidate();
                repaint();
            }
        });
        airplaneManagerButton.setBorderPainted(false);
        airplaneManagerButton.setBackground(sidebarBackgroundColor);
        airplaneManagerButton.setForeground(sidebarTextColor);
        menuPanel.add(airplaneManagerButton);

        addAirportButton = new JButton("Add Airport");
        addAirportButton.setHorizontalAlignment(JButton.LEFT);
        addAirportButton.setBounds(15, 150, 117, 25);
        addAirportButton.setFont(addAirportButton.getFont().deriveFont(10.9f));
        addAirportButton.setBorderPainted(false);
        addAirportButton.setBackground(sidebarBackgroundColor);
        addAirportButton.setForeground(sidebarTextColor);

        editAirportButton = new JButton("Edit Airport");
        editAirportButton.setHorizontalAlignment(JButton.LEFT);
        editAirportButton.setBounds(15, 175, 117, 25);
        editAirportButton.setFont(editAirportButton.getFont().deriveFont(10.9f));
        editAirportButton.setBorderPainted(false);
        editAirportButton.setBackground(sidebarBackgroundColor);
        editAirportButton.setForeground(sidebarTextColor);

        deleteAirportButton = new JButton("Delete Airport");
        deleteAirportButton.setHorizontalAlignment(JButton.LEFT);
        deleteAirportButton.setBounds(15, 200, 117, 25);
        deleteAirportButton.setFont(deleteAirportButton.getFont().deriveFont(10.9f));
        deleteAirportButton.setBorderPainted(false);
        deleteAirportButton.setBackground(sidebarBackgroundColor);
        deleteAirportButton.setForeground(sidebarTextColor);

        displayAirportButton = new JButton("Display Airport");
        displayAirportButton.setHorizontalAlignment(JButton.LEFT);
        displayAirportButton.setBounds(15, 225, 117, 25);
        displayAirportButton.setFont(displayAirportButton.getFont().deriveFont(10.9f));
        displayAirportButton.setBorderPainted(false);
        displayAirportButton.setBackground(sidebarBackgroundColor);
        displayAirportButton.setForeground(sidebarTextColor);

        airportManagerButton = new JButton("Airport Manager");
        airportManagerButton.setHorizontalAlignment(JButton.LEFT);
        airportManagerButton.setBounds(0, 100, 132, 50);
        airportManagerButton.addActionListener(e -> {
            if (airplaneManagerOpen) {
                menuPanel.remove(addAirplaneButton);
                menuPanel.remove(editAirplaneButton);
                menuPanel.remove(deleteAirplaneButton);
                menuPanel.remove(displayAirplaneButton);
                airportManagerButton.setBounds(0, 100, 132, 50);
                airplaneManagerOpen = false;
                airplaneManager.close();
                revalidate();
                repaint();
            }
            if(!airportManagerOpen) {
                menuPanel.add(addAirportButton);
                menuPanel.add(editAirportButton);
                menuPanel.add(deleteAirportButton);
                menuPanel.add(displayAirportButton);
                airportManagerOpen = true;
                revalidate();
                repaint();
            } else {
                menuPanel.remove(addAirportButton);
                menuPanel.remove(editAirportButton);
                menuPanel.remove(deleteAirportButton);
                menuPanel.remove(displayAirportButton);
                airportManagerOpen = false;
                revalidate();
                repaint();
            }
        });
        airportManagerButton.setBorderPainted(false);
        airportManagerButton.setBackground(sidebarBackgroundColor);
        airportManagerButton.setForeground(sidebarTextColor);
        menuPanel.add(airportManagerButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(0, screenHeight - 87, 132, 50);
        exitButton.addActionListener(e -> {
            if (airplaneManagerOpen) {
                airplaneManager.close();
            }
            if (airportManagerOpen) {
                //airportManager.close();
            }
            System.exit(0);
        });
        exitButton.setBorderPainted(false);
        exitButton.setBackground(sidebarBackgroundColor);
        exitButton.setForeground(sidebarTextColor);
        menuPanel.add(exitButton);

        add(menuPanel);

        // Flight Planner Panel
        JPanel flightPlanPanel = new JPanel();
        flightPlanPanel.setLayout(null);

        JLabel departureLabel = new JLabel("Departure Airport:");
        JLabel arrivalLabel = new JLabel("Arrival Airport:");

        departureLabel.setBounds(50, 50, 200, 50);
        arrivalLabel.setBounds(50, 100, 200, 50);

        // JTextField departureField = new JTextField("Deparute Airport: ");
        // JTextField arrivalField = new JTextField("Arrival Airport: ");

        // flightPlannerPanel.add(departureField);
        // flightPlannerPanel.add(arrivalField);
        flightPlanPanel.add(departureLabel);
        flightPlanPanel.add(arrivalLabel);

        // Add Airplane Panel
        JPanel addAirplanePanel = new JPanel();
        addAirplanePanel.setLayout(null);
        JLabel addAirplaneLabel = new JLabel("Add Airplane");
        addAirplaneLabel.setBounds(50, 20, 200, 50);
        JLabel airplaneMakeLabel = new JLabel("Airplane Make:");
        airplaneMakeLabel.setBounds(50, 50, 200, 50);
        JTextField airplaneMakeField = new JTextField();
        airplaneMakeField.setBounds(50, 100, 180, 30);
        JLabel airplaneModelLabel = new JLabel("Airplane Model:");
        airplaneModelLabel.setBounds(50, 130, 200, 50);
        JTextField airplaneModelField = new JTextField();
        airplaneModelField.setBounds(50, 180, 180, 30);
        JLabel airplaneTypeLabel = new JLabel("Airplane Type:");
        airplaneTypeLabel.setBounds(50, 210, 200, 50);
        JTextField airplaneTypeField = new JTextField();
        airplaneTypeField.setBounds(50, 260, 180, 30);
        JLabel fuelTankSizeLabel = new JLabel("Fuel Tank Size:");
        fuelTankSizeLabel.setBounds(250, 50, 200, 50);
        JTextField fuelTankSizeField = new JTextField();
        fuelTankSizeField.setBounds(250, 100, 180, 30);
        JLabel fuelBurnLabel = new JLabel("Fuel Burn:");
        fuelBurnLabel.setBounds(250, 130, 200, 50);
        JTextField fuelBurnField = new JTextField();
        fuelBurnField.setBounds(250, 180, 180, 30);
        JLabel airSpeedLabel = new JLabel("Air Speed:");
        airSpeedLabel.setBounds(250, 210, 200, 50);
        JTextField airSpeedField = new JTextField();
        airSpeedField.setBounds(250, 260, 180, 30);
        JButton addAirplaneSubmitButton = new JButton("Submit");
        addAirplaneSubmitButton.setBounds(50, 310, 150, 40);
        Component[] addAirplaneComponents = {addAirplaneLabel, airplaneMakeLabel, airplaneMakeField, airplaneModelLabel,
                airplaneModelField, airplaneTypeLabel, airplaneTypeField, fuelTankSizeLabel, fuelTankSizeField,
                fuelBurnLabel, fuelBurnField, airSpeedLabel, airSpeedField, addAirplaneSubmitButton};
        for (Component component : addAirplaneComponents) {
            addAirplanePanel.add(component);
        }
        addAirplaneSubmitButton.addActionListener(e -> {
            Airplane airplane = new Airplane(airplaneMakeField.getText(), airplaneModelField.getText(),
                    airplaneTypeField.getText(), fuelTankSizeField.getText(), fuelBurnField.getText(),
                    airSpeedField.getText());
            airplaneManager.add(airplane);
        });


        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        JLabel titleLabel = new JLabel("Flight Planning System");
        titleLabel.setBounds(200, 0, 473, 50);
        JLabel creatorsLabel = new JLabel("Created by: Jadon Ott, Mary "
                + "Alice Woolington, Brecken Merrill, and Cheyenne Kirby");
        creatorsLabel.setBounds(200, 50, 473, 50);
        JLabel warningLabel = new JLabel("THIS SOFTWARE IS NOT TO BE USED FOR FLIGHT PLANNING OR NAVIGATIONAL PURPOSE");
        warningLabel.setBounds(200, 100, 473, 50);

        titlePanel.add(titleLabel);
        titlePanel.add(creatorsLabel);
        titlePanel.add(warningLabel);

        // Main Content
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(130, -25, 473, 428);
        tabbedPane.setBackground(java.awt.Color.BLUE);
        tabbedPane.setBorder(null);

        JPanel airplaneManagerPanel = new JPanel();
        airplaneManagerPanel.setBackground(java.awt.Color.GREEN);

        JPanel airportManagerPanel = new JPanel();
        airportManagerPanel.setBackground(java.awt.Color.YELLOW);

        tabbedPane.addTab("Title", titlePanel);
        tabbedPane.addTab("Flight Planner", flightPlanPanel);
        tabbedPane.addTab("Add Airplane", addAirplanePanel);
        tabbedPane.addTab("Airport Manager", airportManagerPanel);

        add(tabbedPane);
        tabbedPane.setSelectedIndex(0);
    }

}
