package com.algo.view;

import com.algo.model.MetroMap;
import com.algo.model.PragueMetroMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private JLabel picLabel;


    public GUIPanel() throws IOException {
        super("Prague Metro - Journey Planner");

        MetroMap list = new PragueMetroMap();

        labelFrom = new JLabel("From station:");
        labelTo = new JLabel("To station:");
        listFrom = new JList<>(list.getAllStationsNamesAsArray());
        listTo = new JList<>(list.getAllStationsNamesAsArray());

        checkBtn = new JButton("Check");
        detailsArea = new JTextArea(15, 34);

        BufferedImage tube = ImageIO.read(new File("src/tube.jpg"));
        picLabel = new JLabel(new ImageIcon(tube));

        currentLayout = new SpringLayout();

        setupPanel();
        setupLayout();
    }

    /**
     * setting size, color, etc. of items and adding them to the panel
     */
    private void setupPanel() {

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        JPanel myPanel = new JPanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1024, 768);

        detailsArea.setLineWrap(true);
        scrollPane = new JScrollPane(detailsArea);
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
        currentLayout.putConstraint(SpringLayout.WEST, scrollPane, 615, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, scrollPane, 40, SpringLayout.NORTH, this);

        // BUTTON: Check
        currentLayout.putConstraint(SpringLayout.WEST, checkBtn, 500, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, checkBtn, 140, SpringLayout.NORTH, this);

        // IMAGE: Tube Map
        currentLayout.putConstraint(SpringLayout.WEST, picLabel, 160, SpringLayout.WEST, this);
        currentLayout.putConstraint(SpringLayout.NORTH, picLabel, 290, SpringLayout.NORTH, this);
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
     * @param details takes String parameter to display it in the "details area"
     */
    public void setDisplay(String details) {
        detailsArea.setText(details);
    }
}