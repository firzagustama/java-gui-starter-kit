/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */

import view.BaseView;
import view.ExampleView;

import javax.swing.*;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: Main.java, v 0.1 2021-12-21 18.31 Muhammad Firza Gustama Exp $$
 */
public class Main {

    /**
     * Main method this is to start the application
     * @param args
     */
    public static void main(String[] args) {
        // initialize java gui theme
        initUI();

        // create mainFrame with size as in BaseView
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(BaseView.VIEW_SIZE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ExampleView exampleView = new ExampleView();
        mainFrame.add(exampleView.getView(mainFrame));
        mainFrame.setVisible(true);
    }

    /**
     * styling
     */
    private static void initUI() {
        NimbusTheme.loadTheme();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
