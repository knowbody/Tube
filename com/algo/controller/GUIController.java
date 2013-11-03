/**
 * Prague Metro - Journey Planner
 * Project for - COMP1555: Algorithms and Modelling
 * Authors:
 *      Mateusz Zatorski (000738254)
 *      Patrik Fuhrmann (000725089)
 *      Irmantas Marozas (000708431)
 */

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