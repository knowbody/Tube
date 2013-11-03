package com.algo.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Clock extends JLabel {

    private String format;
    private Timer timer;
    private int delay;

    /**
     * Builds a clock using default formatting and delay by default is: 1000ms = 1s
     */
    public Clock() {
        format = "hh:mm:ss a";
        this.delay = 1000;
        createTimer();
        timer.start();
    }

    private void createTimer() {
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setText(new SimpleDateFormat(format).format(new Date()));
            }
        });
    }
}