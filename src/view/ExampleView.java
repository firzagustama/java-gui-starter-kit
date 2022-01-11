/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All right reserved.
 */
package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: ExampleView.java, v 0.1 2022-01-11 17.46 Muhammad Firza Gustama Exp $$
 */
public class ExampleView extends BaseView {
    @Override
    public void initView() {
        // default LayoutManager is using GridBagLayout, set the anchor and insets example
        setAnchor(GridBagConstraints.CENTER);
        setInsets(new Insets(10, 0, 10, 0));

        // addToPanel using baseView to make it simpler and easier when adding constraint
        addToPanel(new JLabel("Example Label"), getConstraint(0, 0));
    }
}
