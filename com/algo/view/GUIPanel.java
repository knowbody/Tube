package com.algo.view;

import com.algo.model.Clock;
import com.algo.model.MetroMap;
import com.algo.model.PragueMetroMap;
import com.algo.structures.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class GUIPanel extends JFrame {

    private JLabel labelFrom;
    private JLabel labelTo;
    private JList<String> listFrom;
    private JList<String> listTo;
    private JButton checkBtn;
    private JTextArea detailsArea;
    private SpringLayout currentLayout;
    private JScrollPane toScroller;
    private JScrollPane fromScroller;
    private JScrollPane scrollPane;
    private JLabel labelDepart;
    private JSpinner spinner;
    private JLabel picLabel;
    private Clock clock;
    private JList<String> jl;
    private Vector<String> v;



    public GUIPanel() throws IOException {
        super("Prague Metro - Journey Planner");

        MetroMap list = new PragueMetroMap();
        clock = new Clock();

        labelFrom = new JLabel("From station:");
        labelTo = new JLabel("To station:");
        listFrom = new JList<>(list.getAllStationsNamesAsArray());
        listTo = new JList<>(list.getAllStationsNamesAsArray());

        checkBtn = new JButton("Check");
        detailsArea = new JTextArea(15, 34);

        labelDepart = new JLabel("Depart at");
        spinner = new JSpinner();
        spinner.setModel(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "HH:mm"));


        BufferedImage tube = ImageIO.read(new File("src/lib/img/tube.jpg"));
        picLabel = new JLabel(new ImageIcon(tube));


        // Create a vector that can store String objects
        v = new Vector();

        // Create a JList that is capable of storing String type items
        jl = new JList(v);


        currentLayout = new SpringLayout();

        setupPanel();
        setupLayout();
    }

    /**
     * setting size, color, etc. of items and adding them to the panel
     */
    private void setupPanel() {
        // Switching look and feel from ugly to less ugly
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Do nothing. Default look will be applied
        }

        JPanel myPanel = new JPanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1124, 768);
        detailsArea.setLineWrap(true);
        jl.setCellRenderer(new StripeRenderer());
        scrollPane = new JScrollPane(jl);
        scrollPane.setPreferredSize(new Dimension(380, 680));
        detailsArea.setEditable(false);

        fromScroller = new JScrollPane(listFrom);
        fromScroller.setPreferredSize(new Dimension(200, 230));

        toScroller = new JScrollPane(listTo);
        toScroller.setPreferredSize(new Dimension(200, 230));

        myPanel.setBackground(new Color(0xD6, 0xD9, 0xDF));
        myPanel.setLayout(currentLayout);
        myPanel.add(scrollPane);
        myPanel.add(checkBtn);
        myPanel.add(fromScroller);
        myPanel.add(toScroller);
        myPanel.add(labelFrom);
        myPanel.add(labelTo);
        myPanel.add(picLabel);
        myPanel.add(clock);
        myPanel.add(labelDepart);
        myPanel.add(spinner);

        this.add(myPanel);
    }

    /**
     * placing the items in the right positions using SpringLayout
     */
    private void setupLayout() {

        // LABEL: From station:
        currentLayout.putConstraint(SpringLayout.WEST, labelFrom, 20, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, labelFrom, 20, SpringLayout.NORTH, this);

        // LABEL: To station:
        currentLayout.putConstraint(SpringLayout.WEST, labelTo, 260, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, labelTo, 20, SpringLayout.NORTH, this);

        // SCROLLER AREA: From
        currentLayout.putConstraint(SpringLayout.WEST, fromScroller, 20, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, fromScroller, 40, SpringLayout.NORTH, this);

        // SCROLLER AREA: To
        currentLayout.putConstraint(SpringLayout.WEST, toScroller, 260, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, toScroller, 40, SpringLayout.NORTH, this);

        // DETAILS AREA: Journey description
        currentLayout.putConstraint(SpringLayout.WEST, scrollPane, 715, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, scrollPane, 40, SpringLayout.NORTH, this);

        // BUTTON: Check
        currentLayout.putConstraint(SpringLayout.WEST, checkBtn, 500, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, checkBtn, 150, SpringLayout.NORTH, this);

        // IMAGE: Tube Map
        currentLayout.putConstraint(SpringLayout.WEST, picLabel, 20, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, picLabel, 290, SpringLayout.NORTH, this);

        // CLOCK
        currentLayout.putConstraint(SpringLayout.WEST, clock, 500, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, clock, 20, SpringLayout.NORTH, this);

        // Depart time label
        currentLayout.putConstraint(SpringLayout.WEST, labelDepart, 505, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, labelDepart, 90, SpringLayout.NORTH, this);

        // Depart time
        currentLayout.putConstraint(SpringLayout.WEST, spinner, 505, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, spinner, 110, SpringLayout.NORTH, this);
    }

    public void addToList(String el) {
        // Add what the user types in JTextField jf, to the vector
        v.add(el);
    }

    public void resetDisplay() {
        v.removeAllElements();
    }


    /**
     * @return name of the selected station from "From station:"
     */
    public String getFromStation() {
        return listFrom.getSelectedValue();
    }

    /**
     * @return name of the selected station from "To station:"
     */
    public String getToStation() {
        return listTo.getSelectedValue();
    }

    /**
     * @param listenForCheckBtn listens to the "Check" button
     */
    public void checkBtnListener(ActionListener listenForCheckBtn) {
        checkBtn.addActionListener(listenForCheckBtn);
    }

    /**
     * @param dt takes String parameter to display it in the "details area"
     */
    public void setDisplay(ArrayList dt) {
        //detailsArea.setText(details);
        for (int i = 0; i < dt.getSize(); i++) {
            addToList((dt.get(i).toString()));
        }

        // Now set the updated vector to JList jl
        jl.setListData(v);
    }

    // display error message when user doesn't choose station TO and FROM
    public void setDisplay(String error) {
        addToList(error);
        jl.setListData(v);
    }

    // getting the time from the spinner
    public Date getTime() {
        Date time = (Date)spinner.getValue();
        return time;
    }
}