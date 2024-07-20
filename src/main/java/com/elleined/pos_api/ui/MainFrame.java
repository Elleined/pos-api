package com.elleined.pos_api.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {

        setTitle("Main Frame");
        setVisible(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
