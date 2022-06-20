/*
 * Copyright 2000-2022 TeamDev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 *
 * This app displays a window with the integration browser component that loads and displays
 * the Pomodoro Tracker web application.
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
