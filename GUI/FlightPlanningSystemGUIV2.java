package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Managers.AirplaneManager;
import Managers.AirportManager;
import Managers.FlightPlanner;
import Objects.Airplane;
import Objects.Airport;

public class FlightPlanningSystemGUIV2 extends JFrame {
    private int screenHeight = 438;
    private int screenWidth = 615;

    AirplaneManager airplaneManager;
    AirportManager airportManager;
    FlightPlanner flightPlanner;

    Color sidebarBackgroundColor = new Color(0x4D4D4D);
    Color sidebarTextColor = new Color(0xFFFFFF);

    // Toggle Variables
    private boolean airplaneManagerOpen = false;
    private boolean airportManagerOpen = false;
    boolean editingAirplane = false;
    boolean editingAirplanePanel = false;
    boolean deletingAirplanePanel = false;
    boolean displayingAirplanePanel = false;

    boolean editingAirport = false;
    boolean editingAirportPanel = false;
    boolean deletingAirportPanel = false;
    boolean displayingAirportPanel = false;

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JButton airportManagerButton;
    private JButton addAirplaneButton;
    private JButton editAirplaneButton;
    private JButton deleteAirplaneButton;
    private JButton displayAirplaneButton;

    private JPanel addAirplanePanel;
    private JPanel editAirplanePanel;

    private AirplaneTable airplaneTable;
    private AirportTable airportTable;

    private JButton addAirportButton;
    private JButton editAirportButton;
    private JButton deleteAirportButton;
    private JButton displayAirportButton;

    private JPanel addAirportPanel;
    private JPanel editAirportPanel;

    JLabel editdeleteAirplaneLabel;
    JButton editdeleteAirplaneButton;

    JLabel editdeleteAirportLabel;
    JButton editdeleteAirportButton;

    public FlightPlanningSystemGUIV2(AirplaneManager airplaneManager, AirportManager airportManager,
            FlightPlanner flightPlanner) {

        this.airplaneManager = airplaneManager;
        this.airportManager = airportManager;

        setTitle("Flight Planning System");
        setSize(screenWidth, screenHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        // Loading Airplane Table that will be used in Edit, Delete, and Display
        // Airplane
        String[][] airplanes = airplaneManager.getAirplaneMakeModelStrings();
        airplaneTable = new AirplaneTable(airplanes);

        // Loading Airport Table that will be used in Edit, Delete, and Display Airport
        String[][] airports = airportManager.getAirportICAONameStrings();
        airportTable = new AirportTable(airports);

        //////////////////
        // Menu Sidebar //
        //////////////////
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

        //////////////////////////////
        // Airplane Manager Submenu //
        //////////////////////////////
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
        editAirplaneButton.addActionListener(e -> {
            editdeleteAirplaneLabel.setText("Edit Airplane");
            editdeleteAirplaneButton.setText("Edit");
            editingAirplanePanel = true;
            deletingAirplanePanel = false;
            displayingAirplanePanel = false;
            airplaneTable.clearSelection();
            tabbedPane.setSelectedIndex(3);
        });

        deleteAirplaneButton = new JButton("Delete Airplane");
        deleteAirplaneButton.setHorizontalAlignment(JButton.LEFT);
        deleteAirplaneButton.setBounds(15, 150, 117, 25);
        deleteAirplaneButton.setFont(deleteAirplaneButton.getFont().deriveFont(10.9f));
        deleteAirplaneButton.setBorderPainted(false);
        deleteAirplaneButton.setBackground(sidebarBackgroundColor);
        deleteAirplaneButton.setForeground(sidebarTextColor);
        deleteAirplaneButton.addActionListener(e -> {
            editdeleteAirplaneLabel.setText("Delete Airplane");
            editdeleteAirplaneButton.setText("Delete");
            deletingAirplanePanel = true;
            editingAirplanePanel = false;
            displayingAirplanePanel = false;
            airplaneTable.clearSelection();
            tabbedPane.setSelectedIndex(3);
        });

        displayAirplaneButton = new JButton("Display Airplane");
        displayAirplaneButton.setHorizontalAlignment(JButton.LEFT);
        displayAirplaneButton.setBounds(15, 175, 117, 25);
        displayAirplaneButton.setFont(displayAirplaneButton.getFont().deriveFont(10.9f));
        displayAirplaneButton.setBorderPainted(false);
        displayAirplaneButton.setBackground(sidebarBackgroundColor);
        displayAirplaneButton.setForeground(sidebarTextColor);
        displayAirplaneButton.addActionListener(e -> {
            editdeleteAirplaneLabel.setText("Display Airplane");
            editdeleteAirplaneButton.setText("Display");
            displayingAirplanePanel = true;
            editingAirplanePanel = false;
            deletingAirplanePanel = false;
            airplaneTable.clearSelection();
            tabbedPane.setSelectedIndex(3);
        });

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
                revalidate();
                repaint();
            }
            if (!airplaneManagerOpen) {
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
                airportManagerButton.setBounds(0, 100, 132, 50);
                revalidate();
                repaint();
            }
        });
        airplaneManagerButton.setBorderPainted(false);
        airplaneManagerButton.setBackground(sidebarBackgroundColor);
        airplaneManagerButton.setForeground(sidebarTextColor);
        menuPanel.add(airplaneManagerButton);

        /////////////////////////////
        // Airport Manager Submenu //
        /////////////////////////////
        addAirportButton = new JButton("Add Airport");
        addAirportButton.setHorizontalAlignment(JButton.LEFT);
        addAirportButton.setBounds(15, 150, 117, 25);
        addAirportButton.setFont(addAirportButton.getFont().deriveFont(10.9f));
        addAirportButton.setBorderPainted(false);
        addAirportButton.setBackground(sidebarBackgroundColor);
        addAirportButton.setForeground(sidebarTextColor);
        addAirportButton.addActionListener(e -> {
            tabbedPane.setSelectedIndex(4);
        });

        editAirportButton = new JButton("Edit Airport");
        editAirportButton.setHorizontalAlignment(JButton.LEFT);
        editAirportButton.setBounds(15, 175, 117, 25);
        editAirportButton.setFont(editAirportButton.getFont().deriveFont(10.9f));
        editAirportButton.setBorderPainted(false);
        editAirportButton.setBackground(sidebarBackgroundColor);
        editAirportButton.setForeground(sidebarTextColor);
        editAirportButton.addActionListener(e -> {
            editingAirportPanel = true;
            deletingAirportPanel = false;
            displayingAirportPanel = false;
            editdeleteAirportLabel.setText("Edit Airport");
            editdeleteAirportButton.setText("Edit");
            airportTable.clearSelection();
            tabbedPane.setSelectedIndex(5);
        });

        deleteAirportButton = new JButton("Delete Airport");
        deleteAirportButton.setHorizontalAlignment(JButton.LEFT);
        deleteAirportButton.setBounds(15, 200, 117, 25);
        deleteAirportButton.setFont(deleteAirportButton.getFont().deriveFont(10.9f));
        deleteAirportButton.setBorderPainted(false);
        deleteAirportButton.setBackground(sidebarBackgroundColor);
        deleteAirportButton.setForeground(sidebarTextColor);
        deleteAirportButton.addActionListener(e -> {
            deletingAirportPanel = true;
            editingAirportPanel = false;
            displayingAirportPanel = false;
            editdeleteAirportLabel.setText("Delete Airport");
            editdeleteAirportButton.setText("Delete");
            airportTable.clearSelection();
            tabbedPane.setSelectedIndex(5);
        });

        displayAirportButton = new JButton("Display Airport");
        displayAirportButton.setHorizontalAlignment(JButton.LEFT);
        displayAirportButton.setBounds(15, 225, 117, 25);
        displayAirportButton.setFont(displayAirportButton.getFont().deriveFont(10.9f));
        displayAirportButton.setBorderPainted(false);
        displayAirportButton.setBackground(sidebarBackgroundColor);
        displayAirportButton.setForeground(sidebarTextColor);
        displayAirportButton.addActionListener(e -> {
            displayingAirportPanel = true;
            editingAirportPanel = false;
            deletingAirportPanel = false;
            editdeleteAirportLabel.setText("Display Airport");
            editdeleteAirportButton.setText("Display");
            airportTable.clearSelection();
            tabbedPane.setSelectedIndex(5);
        });

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
                revalidate();
                repaint();
            }
            if (!airportManagerOpen) {
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
            airplaneManager.save();
            // airportManager.save();
            System.exit(0);
        });
        exitButton.setBorderPainted(false);
        exitButton.setBackground(sidebarBackgroundColor);
        exitButton.setForeground(sidebarTextColor);
        menuPanel.add(exitButton);

        add(menuPanel);

        //////////////////////////
        // Flight Planner Panel // ITS FUCKED
        //////////////////////////
        JPanel flightPlanPanel = new JPanel();
        flightPlanPanel.setLayout(null);

        JLabel flightPlannerLabel = new JLabel("Flight Planner");
        flightPlannerLabel.setBounds(50, 20, 200, 50);
        JLabel departureLabel = new JLabel("Departure Airport:");
        departureLabel.setBounds(50, 50, 200, 50);
        JLabel arrivalLabel = new JLabel("Arrival Airport:");
        arrivalLabel.setBounds(50, 130, 200, 50);
        JLabel airplaneLabel = new JLabel("Airplane:");
        airplaneLabel.setBounds(50, 210, 200, 50);

        JComboBox<String> departureField = new JComboBox<String>();
        departureField.setBounds(50, 100, 200, 30);
        JComboBox<String> arrivalField = new JComboBox<String>();
        arrivalField.setBounds(50, 180, 200, 30);

        departureField.addItem("Select an Airport");
        arrivalField.addItem("Select an Airport");
        for (int i = 0; i < airportManager.getAirportCount(); i++) {
            departureField.addItem(
                    airportManager.getAirport(i).getICAOIdentifier() + " - " + airportManager.getAirport(i).getName());
            arrivalField.addItem(
                    airportManager.getAirport(i).getICAOIdentifier() + " - " + airportManager.getAirport(i).getName());
        }

        JComboBox<String> airplaneField = new JComboBox<String>();
        airplaneField.setBounds(50, 260, 200, 30);
        airplaneField.addItem("Select an Airplane");
        for (int i = 0; i < airplaneManager.getAirplaneCount(); i++) {
            airplaneField.addItem(
                    airplaneManager.getAirplane(i).getMake() + " " + airplaneManager.getAirplane(i).getModel());
        }

        JButton planFlightButton = new JButton("Plan Flight");
        planFlightButton.setBounds(50, 310, 150, 40);

        JPanel planel = new JPanel();
        planel.setLayout(null);
        planel.setBounds(270, 50, 180, 300);
        planel.setBackground(sidebarTextColor);
        JLabel destinationLabel = new JLabel("Destinations:");
        destinationLabel.setBounds(20, -10, 180, 60);
        JLabel destinationOneLabel = new JLabel("");
        destinationOneLabel.setBounds(20, 10, 180, 60);
        JLabel destinationTwoLabel = new JLabel("");
        destinationTwoLabel.setBounds(20, 70, 180, 60);
        JLabel destinationThreeLabel = new JLabel("");
        destinationThreeLabel.setBounds(20, 130, 180, 60);
        JLabel destinationFourLabel = new JLabel("");
        destinationFourLabel.setBounds(20, 190, 180, 60);
        JLabel destinationFiveLabel = new JLabel("");
        destinationFiveLabel.setBounds(20, 250, 180, 60);

        flightPlanPanel.add(flightPlannerLabel);
        flightPlanPanel.add(departureLabel);
        flightPlanPanel.add(arrivalLabel);
        flightPlanPanel.add(airplaneLabel);
        flightPlanPanel.add(departureField);
        flightPlanPanel.add(arrivalField);
        flightPlanPanel.add(airplaneField);
        flightPlanPanel.add(planFlightButton);

        planel.add(destinationLabel);
        planel.add(destinationOneLabel);
        planel.add(destinationTwoLabel);
        planel.add(destinationThreeLabel);
        planel.add(destinationFourLabel);
        planel.add(destinationFiveLabel);

        flightPlanPanel.add(planel);

        planFlightButton.addActionListener(e -> {
            if (departureField.getSelectedIndex() == 0 || arrivalField.getSelectedIndex() == 0
                    || airplaneField.getSelectedIndex() == 0) {
                return;
            }
            if (departureField.getSelectedIndex() == arrivalField.getSelectedIndex()) {
                return;
            }
            int departureIndex = departureField.getSelectedIndex() - 1;
            int arrivalIndex = arrivalField.getSelectedIndex() - 1;
            int airplaneIndex = airplaneField.getSelectedIndex() - 1;
            Vector<Airport> flightPlan = flightPlanner.planFlight(departureIndex, arrivalIndex, airplaneIndex);
        });

        ////////////////////////
        // Add Airplane Panel //
        ////////////////////////
        addAirplanePanel = new JPanel();
        addAirplanePanel.setLayout(null);
        JLabel addAirplaneLabel = new JLabel("Add Airplane");
        addAirplaneLabel.setBounds(50, 20, 200, 50);

        JLabel airplaneMakeLabel = new JLabel("Airplane Make:");
        airplaneMakeLabel.setBounds(50, 50, 200, 50);
        JTextField airplaneMakeField = new JTextField();
        airplaneMakeField.setBounds(50, 100, 180, 30);

        JLabel airplaneModelLabel = new JLabel("Airplane Model:");
        airplaneModelLabel.setBounds(250, 50, 200, 50);
        JTextField airplaneModelField = new JTextField();
        airplaneModelField.setBounds(250, 100, 180, 30);

        JLabel airplaneTypeLabel = new JLabel("Airplane Type:");
        airplaneTypeLabel.setBounds(50, 130, 200, 50);
        JTextField airplaneTypeField = new JTextField();
        airplaneTypeField.setBounds(50, 180, 180, 30);

        JLabel fuelTankSizeLabel = new JLabel("Fuel Tank Size:");
        fuelTankSizeLabel.setBounds(250, 130, 200, 50);
        JTextField fuelTankSizeField = new JTextField();
        fuelTankSizeField.setBounds(250, 180, 180, 30);

        JLabel fuelBurnLabel = new JLabel("Fuel Burn:");
        fuelBurnLabel.setBounds(50, 210, 200, 50);
        JTextField fuelBurnField = new JTextField();
        fuelBurnField.setBounds(50, 260, 180, 30);

        JLabel airSpeedLabel = new JLabel("Air Speed:");
        airSpeedLabel.setBounds(250, 210, 200, 50);
        JTextField airSpeedField = new JTextField();
        airSpeedField.setBounds(250, 260, 180, 30);

        JButton addAirplaneSubmitButton = new JButton("Submit");
        addAirplaneSubmitButton.setBounds(50, 310, 150, 40);

        Component[] addAirplaneComponents = { addAirplaneLabel, airplaneMakeLabel, airplaneMakeField,
                airplaneModelLabel,
                airplaneModelField, airplaneTypeLabel, airplaneTypeField, fuelTankSizeLabel, fuelTankSizeField,
                fuelBurnLabel, fuelBurnField, airSpeedLabel, airSpeedField, addAirplaneSubmitButton };
        for (Component component : addAirplaneComponents) {
            addAirplanePanel.add(component);
        }
        addAirplaneSubmitButton.addActionListener(e -> {
            Airplane airplane = new Airplane(airplaneMakeField.getText(), airplaneModelField.getText(),
                    airplaneTypeField.getText(), fuelTankSizeField.getText(), fuelBurnField.getText(),
                    airSpeedField.getText());
            airplaneManager.add(airplane);
            airplaneMakeField.setText("");
            airplaneModelField.setText("");
            airplaneTypeField.setText("");
            fuelTankSizeField.setText("");
            fuelBurnField.setText("");
            airSpeedField.setText("");
            airplaneManager.save();

            airplaneTable.addData(new String[] { airplane.getMake(), airplane.getModel() });
        });

        //////////////////////////////////
        // Edit/Delete/Display Airplane //
        //////////////////////////////////

        // Labels and buttons for the edit/delete airplane panel
        editdeleteAirplaneLabel = new JLabel("Edit/Delete Airplane");
        editdeleteAirplaneLabel.setBounds(50, 15, 200, 50);

        editdeleteAirplaneButton = new JButton("Edit");
        editdeleteAirplaneButton.setBounds(50, 350, 150, 40);

        editAirplanePanel = new JPanel();
        editAirplanePanel.setLayout(null);

        JScrollPane editAirplaneScrollPane = new JScrollPane(airplaneTable);
        editAirplaneScrollPane.setBounds(50, 50, 373, 295);

        editAirplanePanel.add(editdeleteAirplaneLabel);
        editAirplanePanel.add(editAirplaneScrollPane);
        editAirplanePanel.add(editdeleteAirplaneButton);

        Component[] editAirplaneComponents = { airplaneMakeLabel, airplaneMakeField, airplaneModelLabel,
                airplaneModelField, airplaneTypeLabel, airplaneTypeField, fuelTankSizeLabel, fuelTankSizeField,
                fuelBurnLabel, fuelBurnField, airSpeedLabel, airSpeedField };

        JButton[] buttonsToDisable = { flightPlannerButton, airplaneManagerButton, airportManagerButton,
                addAirplaneButton, editAirplaneButton, deleteAirplaneButton, displayAirplaneButton,
                addAirportButton, editAirportButton, deleteAirportButton, displayAirportButton };

        editdeleteAirplaneButton.addActionListener(e -> {
            if (editingAirplanePanel) {
                if (editingAirplane) {
                    int selectedRow = airplaneTable.getSelected();
                    editAirplaneScrollPane.setVisible(true);
                    for (Component component : editAirplaneComponents) {
                        editAirplanePanel.remove(component);
                        addAirplanePanel.add(component);
                    }
                    revalidate();
                    repaint();
                    Airplane airplane = airplaneManager.getAirplane(selectedRow);
                    airplaneManager.edit(airplane, airplaneMakeField.getText(), airplaneModelField.getText(),
                            airplaneTypeField.getText(), Double.parseDouble(fuelTankSizeField.getText()),
                            Double.parseDouble(fuelBurnField.getText()),
                            Double.parseDouble(airSpeedField.getText()));
                    airplaneManager.save();
                    airplaneTable.setValueAt(airplane.getMake(), selectedRow, 0);
                    airplaneTable.setValueAt(airplane.getModel(), selectedRow, 1);
                    airplaneMakeField.setText("");
                    airplaneModelField.setText("");
                    airplaneTypeField.setText("");
                    fuelTankSizeField.setText("");
                    fuelBurnField.setText("");
                    airSpeedField.setText("");
                    editdeleteAirplaneButton.setText("Edit");
                    for (JButton button : buttonsToDisable) {
                        button.setEnabled(true);
                    }
                    airplaneTable.clearSelection();
                    editingAirplane = false;
                } else {
                    int selectedRow = airplaneTable.getSelected();
                    if (selectedRow == -1 || selectedRow >= airplaneManager.getAirplaneCount()) {
                        return;
                    }
                    editAirplaneScrollPane.setVisible(false);
                    for (Component component : editAirplaneComponents) {
                        editAirplanePanel.add(component);
                    }
                    Airplane airplane = airplaneManager.getAirplane(selectedRow);
                    airplaneMakeField.setText(airplane.getMake());
                    airplaneModelField.setText(airplane.getModel());
                    airplaneTypeField.setText(airplane.getType());
                    fuelTankSizeField.setText(Double.toString(airplane.getFuelTankSize()));
                    fuelBurnField.setText(Double.toString(airplane.getFuelBurn()));
                    airSpeedField.setText(Double.toString(airplane.getAirSpeed()));
                    editdeleteAirplaneButton.setText("Submit");
                    for (JButton button : buttonsToDisable) {
                        button.setEnabled(false);
                    }
                    editingAirplane = true;
                }
            } else if (deletingAirplanePanel) {
                int selectedRow = airplaneTable.getSelected();
                if (selectedRow == -1 || selectedRow >= airplaneManager.getAirplaneCount()) {
                    return;
                }
                JDialog confirmDialog = new JDialog();
                confirmDialog.setSize(300, 150);
                confirmDialog.setLayout(null);
                confirmDialog.setResizable(false);
                confirmDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                confirmDialog.setLocationRelativeTo(this);
                Airplane selectedAirplane = airplaneManager.getAirplane(selectedRow);
                confirmDialog
                        .setTitle("Delete " + selectedAirplane.getMake() + " " + selectedAirplane.getModel() + "?");
                JLabel confirmLabel = new JLabel("Are you sure you want to delete this airplane?");
                confirmLabel.setBounds(15, 0, 270, 50);
                JButton confirmButton = new JButton("Confirm");
                confirmButton.setBounds(45, 50, 90, 30);
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setBounds(145, 50, 90, 30);
                confirmButton.addActionListener(e1 -> {
                    airplaneManager.delete(airplaneManager.getAirplane(selectedRow));
                    airplaneManager.save();
                    airplaneTable.removeData(selectedRow);
                    confirmDialog.dispose();
                });
                cancelButton.addActionListener(e1 -> {
                    confirmDialog.dispose();
                });
                confirmDialog.add(confirmLabel);
                confirmDialog.add(confirmButton);
                confirmDialog.add(cancelButton);
                confirmDialog.setVisible(true);
            } else if (displayingAirplanePanel) {
                int selectedRow = airplaneTable.getSelected();
                if (selectedRow == -1 || selectedRow >= airplaneManager.getAirplaneCount()) {
                    return;
                }
                airplaneManager.display(airplaneManager.getAirplane(selectedRow));
            }
        });

        /////////////////
        // Add Airport //
        /////////////////

        addAirportPanel = new JPanel();
        addAirportPanel.setLayout(null);
        JLabel addAirportLabel = new JLabel("Add Airport");
        addAirportLabel.setBounds(50, 20, 200, 50);

        JLabel airportICAOIdentifierLabel = new JLabel("ICAO Identifier:");
        airportICAOIdentifierLabel.setBounds(50, 50, 200, 50);

        JTextField airportICAOIdentifierField = new JTextField();
        airportICAOIdentifierField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (airportICAOIdentifierField.getText().length() >= 4)
                    e.consume();
                char keyChar = e.getKeyChar();
                if (Character.isLowerCase(keyChar)) {
                    e.setKeyChar(Character.toUpperCase(keyChar));
                }
                if (Character.isDigit(keyChar)) {
                    e.consume();
                }
            }
        });
        airportICAOIdentifierField.setBounds(50, 100, 90, 30);

        JLabel airportNameLabel = new JLabel("Name:");
        airportNameLabel.setBounds(160, 50, 200, 50);

        JTextField airportNameField = new JTextField();
        airportNameField.setBounds(160, 100, 270, 30);

        JLabel airportLatitudeLabel = new JLabel("Latitude (N):");
        airportLatitudeLabel.setBounds(50, 130, 200, 50);

        JTextField airportLatitudeField = new JTextField();
        airportLatitudeField.setBounds(50, 180, 180, 30);

        JLabel airportLongitudeLabel = new JLabel("Longitude (W):");
        airportLongitudeLabel.setBounds(250, 130, 200, 50);

        JTextField airportLongitudeField = new JTextField();
        airportLongitudeField.setBounds(250, 180, 180, 30);

        JLabel COMFrequencyLabel = new JLabel("COM Frequency:");
        COMFrequencyLabel.setBounds(50, 210, 200, 50);

        JTextField COMFrequencyField = new JTextField();
        COMFrequencyField.setBounds(50, 260, 130, 30);

        JLabel fuelTypesLabel = new JLabel("Fuel Types:");
        fuelTypesLabel.setBounds(200, 210, 200, 50);

        JComboBox<String> fuelTypesField1 = new JComboBox<String>();
        fuelTypesField1.setBounds(200, 260, 100, 30);
        fuelTypesField1.addItem("AVGAS");
        fuelTypesField1.addItem("JA-a");

        JComboBox<String> fuelTypesField2 = new JComboBox<String>();
        fuelTypesField2.setBounds(320, 260, 100, 30);
        fuelTypesField2.addItem("JA-a");
        fuelTypesField2.addItem("AVGAS");

        JButton addAirportSubmitButton = new JButton("Submit");
        addAirportSubmitButton.setBounds(50, 310, 150, 40);

        Component[] airportInputs = { addAirportLabel, airportICAOIdentifierLabel, airportICAOIdentifierField,
                airportNameLabel,
                airportNameField, airportLatitudeLabel, airportLatitudeField, airportLongitudeLabel,
                airportLongitudeField,
                COMFrequencyField, COMFrequencyLabel, fuelTypesLabel, fuelTypesField1, fuelTypesField2,
                addAirportSubmitButton };

        for (Component component : airportInputs) {
            addAirportPanel.add(component);
        }

        addAirportSubmitButton.addActionListener(e -> {
            Airport airport = new Airport(airportICAOIdentifierField.getText(), airportNameField.getText(),
                    Double.parseDouble(airportLatitudeField.getText()),
                    Double.parseDouble(airportLongitudeField.getText()),
                    Double.parseDouble(COMFrequencyField.getText()), new String[] {
                            (String) fuelTypesField1.getSelectedItem(), (String) fuelTypesField2.getSelectedItem() });
            airportManager.add(airport);
            airportICAOIdentifierField.setText("");
            airportNameField.setText("");
            airportLatitudeField.setText("");
            airportLongitudeField.setText("");
            fuelTypesField1.setSelectedIndex(0);
            fuelTypesField2.setSelectedIndex(0);
            airportManager.save();

            airportTable.addData(new String[] { airport.getICAOIdentifier(), airport.getName() });
        });

        /////////////////////////////////
        // Edit/Delete/Display Airport //
        /////////////////////////////////

        // Labels and buttons for the edit/delete airport panel
        editdeleteAirportLabel = new JLabel("Edit/Delete Airport");
        editdeleteAirportLabel.setBounds(50, 15, 200, 50);

        editdeleteAirportButton = new JButton("Edit");
        editdeleteAirportButton.setBounds(50, 350, 150, 40);

        editAirportPanel = new JPanel();
        editAirportPanel.setLayout(null);

        JScrollPane editAirportScrollPane = new JScrollPane(airportTable);
        editAirportScrollPane.setBounds(50, 50, 373, 295);

        editAirportPanel.add(editdeleteAirportLabel);
        editAirportPanel.add(editAirportScrollPane);
        editAirportPanel.add(editdeleteAirportButton);

        Component[] editAirportComponents = { airportICAOIdentifierLabel, airportICAOIdentifierField, airportNameLabel,
                airportNameField, airportLatitudeLabel, airportLatitudeField, airportLongitudeLabel,
                airportLongitudeField,
                COMFrequencyField, COMFrequencyLabel, fuelTypesLabel, fuelTypesField1, fuelTypesField2 };

        JButton[] buttonsToDisableAirport = { flightPlannerButton, airplaneManagerButton, airportManagerButton,
                addAirplaneButton, editAirplaneButton, deleteAirplaneButton, displayAirplaneButton,
                addAirportButton, editAirportButton, deleteAirportButton, displayAirportButton };

        editdeleteAirportButton.addActionListener(e -> {
            if (editingAirportPanel) {
                if (editingAirport) {
                    int selectedRow = airportTable.getSelected();
                    editAirportScrollPane.setVisible(true);
                    for (Component component : editAirportComponents) {
                        editAirportPanel.remove(component);
                        addAirportPanel.add(component);
                    }
                    revalidate();
                    repaint();
                    Airport airport = airportManager.getAirport(selectedRow);
                    airportManager.edit(airport, airportICAOIdentifierField.getText(), airportNameField.getText(),
                            Double.parseDouble(airportLatitudeField.getText()),
                            Double.parseDouble(airportLongitudeField.getText()),
                            Double.parseDouble(COMFrequencyField.getText()),
                            new String[] { (String) fuelTypesField1.getSelectedItem(),
                                    (String) fuelTypesField2.getSelectedItem() });
                    airportManager.save();
                    airportTable.setValueAt(airport.getICAOIdentifier(), selectedRow, 0);
                    airportTable.setValueAt(airport.getName(), selectedRow, 1);
                    airportICAOIdentifierField.setText("");
                    airportNameField.setText("");
                    airportLatitudeField.setText("");
                    airportLongitudeField.setText("");
                    COMFrequencyField.setText("");
                    fuelTypesField1.setSelectedIndex(0);
                    fuelTypesField2.setSelectedIndex(0);
                    editdeleteAirportButton.setText("Edit");
                    for (JButton button : buttonsToDisableAirport) {
                        button.setEnabled(true);
                    }
                    airportTable.clearSelection();
                    editingAirport = false;
                } else {
                    int selectedRow = airportTable.getSelected();
                    if (selectedRow == -1 || selectedRow >= airportManager.getAirportCount()) {
                        return;
                    }
                    editAirportScrollPane.setVisible(false);
                    for (Component component : editAirportComponents) {
                        editAirportPanel.add(component);
                    }
                    Airport airport = airportManager.getAirport(selectedRow);
                    airportICAOIdentifierField.setText(airport.getICAOIdentifier());
                    airportNameField.setText(airport.getName());
                    airportLatitudeField.setText(Double.toString(airport.getLatitude()));
                    airportLongitudeField.setText(Double.toString(airport.getLongitude()));
                    COMFrequencyField.setText(Double.toString(airport.getCOMFrequency()));
                    fuelTypesField1.setSelectedItem(airport.getFuelType()[0]);
                    fuelTypesField2.setSelectedItem(airport.getFuelType()[1]);
                    editdeleteAirportButton.setText("Submit");
                    for (JButton button : buttonsToDisableAirport) {
                        button.setEnabled(false);
                    }
                    editingAirport = true;

                }
            } else if (deletingAirportPanel) {
                int selectedRow = airportTable.getSelected();
                if (selectedRow == -1 || selectedRow >= airportManager.getAirportCount()) {
                    return;
                }
                JDialog confirmDialog = new JDialog();
                confirmDialog.setSize(300, 150);
                confirmDialog.setLayout(null);
                confirmDialog.setResizable(false);
                confirmDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                confirmDialog.setLocationRelativeTo(this);
                Airport selectedAirport = airportManager.getAirport(selectedRow);
                confirmDialog.setTitle("Delete " + selectedAirport.getICAOIdentifier() + "?");
                JLabel confirmLabel = new JLabel("Are you sure you want to delete this airport?");
                confirmLabel.setBounds(15, 0, 270, 50);
                JButton confirmButton = new JButton("Confirm");
                confirmButton.setBounds(45, 50, 90, 30);
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setBounds(145, 50, 90, 30);
                confirmButton.addActionListener(e1 -> {
                    airportManager.delete(airportManager.getAirport(selectedRow));
                    airportManager.save();
                    airportTable.removeData(selectedRow);
                    confirmDialog.dispose();
                });
                cancelButton.addActionListener(e1 -> {
                    confirmDialog.dispose();
                });
                confirmDialog.add(confirmLabel);
                confirmDialog.add(confirmButton);
                confirmDialog.add(cancelButton);
                confirmDialog.setVisible(true);
            } else if (displayingAirportPanel) {
                int selectedRow = airportTable.getSelected();
                if (selectedRow == -1 || selectedRow >= airportManager.getAirportCount()) {
                    return;
                }
                airportManager.display(airportManager.getAirport(selectedRow));
            }
        });

        /////////////////
        // Title Panel //
        /////////////////
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        JLabel titleLabel = new JLabel("Flight Planning System");
        titleLabel.setBounds(0, 100, 473, 50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
        JLabel creatorsLabel = new JLabel("<html><center>Created by: Jadon Ott, Mary "
                + "Alice Woolington, <br>Brecken Merrill, and Cheyenne Kirby</center></html>");
        creatorsLabel.setBounds(20, 150, 453, 50);
        creatorsLabel.setHorizontalAlignment(JLabel.CENTER);
        creatorsLabel.setFont(creatorsLabel.getFont().deriveFont(15.0f));
        JLabel warningLabel = new JLabel("<html><center>THIS SOFTWARE IS NOT TO BE USED FOR FLIGHT PLANNING OR NAVIGATIONAL PURPOSE</center></html>");
        warningLabel.setHorizontalAlignment(JLabel.CENTER);
        warningLabel.setFont(warningLabel.getFont().deriveFont(15.0f));
        warningLabel.setBounds(20, 350, 453, 50);

        titlePanel.add(titleLabel);
        titlePanel.add(creatorsLabel);
        titlePanel.add(warningLabel);

        //////////////////////
        // Main Tabbed Pane //
        //////////////////////
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(130, -44, 473, 447);
        tabbedPane.setBackground(java.awt.Color.BLUE);
        tabbedPane.setBorder(null);

        JPanel airplaneManagerPanel = new JPanel();
        airplaneManagerPanel.setBackground(java.awt.Color.GREEN);

        JPanel airportManagerPanel = new JPanel();
        airportManagerPanel.setBackground(java.awt.Color.YELLOW);

        tabbedPane.addTab("Title", titlePanel);
        tabbedPane.addTab("Flight Planner", flightPlanPanel);
        tabbedPane.addTab("Add Airplane", addAirplanePanel);
        tabbedPane.addTab("Edit Airplane", editAirplanePanel);
        tabbedPane.addTab("Add Airport", addAirportPanel);
        tabbedPane.addTab("Edit Airport", editAirportPanel);

        add(tabbedPane);
        tabbedPane.setSelectedIndex(0);
    }

}
