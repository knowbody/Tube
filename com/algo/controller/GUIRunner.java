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

import java.awt.*;
import java.io.IOException;

public class GUIRunner implements Runnable {
    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new GUIRunner());
    }

    @Override
    public void run() {

        GUIPanel view = null;

        // if tube map image cannot be found throw IOException
        try {
            view = new GUIPanel();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GUIModel model = new GUIModel();
        GUIController controller = new GUIController(model, view);

        // without if statement might cause NullPointerException
        if (view != null) {
            view.setVisible(true);
        }
    }
}