package com.algo.controller;

import com.algo.model.GUIModel;
import com.algo.view.GUIPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController {

    private GUIModel model;
    private GUIPanel view;

    public GUIController(GUIModel model, GUIPanel view) {
        this.model = model;
        this.view = view;

        this.view.checkBtnListener(new CheckButtonListener());
    }

    private class CheckButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // button has to pass both values to the model, get the response and set view
            String from = view.getFromStation();
            String to = view.getToStation();
            if (from == null) {
                view.setDisplay("Please select FROM station");
            } else if (to == null) {
                view.setDisplay("Please select TO station");
            } else {
                model.setDoSearch(from, to);
                view.setDisplay(model.getDetails());
            }
        }
    }
}