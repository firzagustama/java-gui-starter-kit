/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package view.component;

import view.BaseView;

import javax.swing.*;
import java.awt.*;

/**
 * Create custom combo box with custom dimension
 *
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: CustomComboBox.java, v 0.1 2021-12-23 16.39 Muhammad Firza Gustama Exp $$
 */
public class CustomComboBox<E extends Object> extends JComboBox<E> {
    @Override
    public Dimension getPreferredSize() {
        return BaseView.COMBO_BOX_SIZE;
    }
}
