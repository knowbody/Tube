package com.algo.view;

import javax.swing.*;
import java.awt.*;

public class StripeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list,
                value,
                index,
                isSelected,
                cellHasFocus
        );

        if (index % 2 == 0) {
            if (!list.isSelectedIndex(index)) {
                label.setBackground(new Color(230, 255, 230));
            } else {
                label.setBackground(new Color(255, 255, 200));
            }
        }

        return label;
    }
}