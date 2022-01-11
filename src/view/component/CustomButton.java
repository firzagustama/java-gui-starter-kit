/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package view.component;

import javax.swing.*;
import java.awt.*;

/**
 * Create custom button with fixed dimension
 *
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: CustomButton.java, v 0.1 2021-12-16 22.54 Muhammad Firza Gustama Exp $$
 */
public class CustomButton extends JButton {

    /**
     * Constructor
     * @param text
     */
    public CustomButton(String text) {
        super(text);
    }

    /**
     * minimum width and height
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(110, 28);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(110, 28);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(110, 28);
    }

    @Override
    public Insets getMargin() {
        return new Insets(0, 0, 0, 0);
    }
}
