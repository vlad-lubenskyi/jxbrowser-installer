/*
 * Copyright (c) 2000-2022 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */

package com.teamdev.examples;

import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * A Pomodoro tracker.
 */
public final class PomodoroTracker {

    public static final String URL = "https://jxbrowser-support.teamdev.com/docs/tutorials/jpackage/pomodoro.html";

    public static void main(String[] args) {
        var splash = showSplashScreen();
        showBrowser();
        splash.dispose();
    }

    private static void showBrowser() {
        var engine = Engine.newInstance(HARDWARE_ACCELERATED);
        var browser = engine.newBrowser();
        var frame = new JFrame("Pomodoro Tracker");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                engine.close();
            }
        });
        var view = BrowserView.newInstance(browser);
        frame.add(view, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(1280, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        browser.navigation().loadUrl(URL);
    }

    private static JWindow showSplashScreen() {
        var splash = new JWindow();
        splash.getContentPane().add(new JLabel("Loading...", CENTER));
        splash.setBounds(500, 150, 300, 200);
        splash.setVisible(true);
        return splash;
    }
}
