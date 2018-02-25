package com.company.gui;

import javax.swing.*;

/**
 * Created by Eric on 06/09/2015.
 */
public class ScrambleSquareApp {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Mainframe();
    }
}
