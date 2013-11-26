package com.algo.controller;

import com.algo.model.GUIModel;
import com.algo.view.GUIPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener {

    private GUIModel model;
    private GUIPanel view;

    public GUIController(GUIModel model, GUIPanel view) {
        this.model = model;
        this.view = view;
        this.view.checkBtnListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.resetDisplay();
        // button has to pass both values to the model, get the response and set view
        String from = view.getFromStation();
        String to = view.getToStation();


        model.setTime(view.getTime());

        if (from != null && to != null) {
            model.setDoSearch(from, to);
            view.setDisplay(model.getDetails());
        } else {
            // display error message when user doesn't choose station TO and FROM
            view.setDisplay("Please choose FROM and TO station.");
        }
    }
}