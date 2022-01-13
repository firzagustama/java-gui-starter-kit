/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package view;

import repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: BaseView.java, v 0.1 2021-12-16 20.08 Muhammad Firza Gustama Exp $$
 */
public abstract class BaseView extends JPanel {

    // panel
    private JFrame mainFrame;
    private final JPanel panel;

    // constraint
    private int gridWidth = 1;
    private int gridHeight = 1;
    private double weightX = 0;
    private double weightY = 0;
    private int anchor = GridBagConstraints.CENTER;
    private int fill = GridBagConstraints.NONE;
    private Insets insets = new Insets(0, 0, 0, 0);
    private int ipadX = 0;
    private int ipadY = 0;

    // color
    public static final Color BASE_COLOR = new Color(64, 64, 64);
    public static final Color SECONDARY_COLOR = Color.GRAY;
    public static final Color ACCENT_COLOR = Color.WHITE;

    // panel size
    public static final Dimension VIEW_SIZE = new Dimension(500, 350);
    public static final Dimension LARGE_VIEW_SIZE = new Dimension(1000, 600);

    // combo box size
    public static final Dimension COMBO_BOX_SIZE = new Dimension(178, 27);

    // repository
    protected final UserRepository userRepository = new UserRepository();

    /**
     * Constructor
     *
     * default layout {@link GridBagLayout}
     */
    public BaseView() {
        panel = new JPanel();
        panel.setSize(VIEW_SIZE);
        panel.setLayout(new GridBagLayout());
    }

    /**
     * switchView
     * @param destinationView
     */
    public void switchView(BaseView destinationView) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(destinationView.getView(mainFrame));
        mainFrame.validate();
    }

    /**
     * validateView
     */
    public void validateView() {
        panel.validate();
        mainFrame.validate();
    }

    /**
     * get Constraint
     * @param gridx
     * @param gridy
     * @return
     */
    public GridBagConstraints getConstraint(int gridx, int gridy) {
        return new GridBagConstraints(gridx, gridy, gridWidth, gridHeight, weightX, weightY, anchor, fill, insets, ipadX, ipadY);
    }

    /**
     * get Constraint
     * @param gridx
     * @param gridy
     * @return
     */
    public GridBagConstraints getConstraint(int gridx, int gridy, int gridWidth) {
        return new GridBagConstraints(gridx, gridy, gridWidth, gridHeight, weightX, weightY, anchor, fill, insets, ipadX, ipadY);
    }

    /**
     * get Constraint
     * @param gridx
     * @param gridy
     * @param anchor
     * @return
     */
    public GridBagConstraints getConstraintAnchor(int gridx, int gridy, int anchor) {
        return new GridBagConstraints(gridx, gridy, gridWidth, gridHeight, weightX, weightY, anchor, fill, insets, ipadX, ipadY);
    }

    /**
     * get Constraint
     * @param gridx
     * @param gridy
     * @param anchor
     * @return
     */
    public GridBagConstraints getConstraint(int gridx, int gridy, int gridWidth, int anchor) {
        return new GridBagConstraints(gridx, gridy, gridWidth, gridHeight, weightX, weightY, anchor, fill, insets, ipadX, ipadY);
    }

    /**
     * View Initialization
     */
    public abstract void initView();

    /**
     * getView
     *
     * @param mainFrame
     * @return
     */
    public JPanel getView(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        initView();

        panel.setVisible(true);
        return panel;
    }

    /**
     * showWarning
     * @param message {@link String}
     */
    protected void showWarning(String message) {
        JOptionPane.showMessageDialog(mainFrame, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * showSuccess
     * @param message {@link String}
     */
    protected void showSuccess(String message) {
        JOptionPane.showMessageDialog(mainFrame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * add to base panel (GridBagLayout)
     *
     * @param component
     */
    public void addToPanel(JComponent component) {
        panel.add(component);
    }

    /**
     * add to base panel with constraint (GridBagLayout)
     *
     * @param component
     * @param c
     */
    public void addToPanel(JComponent component, GridBagConstraints c) {
        panel.add(component, c);
    }

    /**
     * add to base panel with constraint (BorderLayout)
     * @param component {@link JComponent}
     * @param borderLayoutConstraint {@link String}
     */
    public void addToPanel(JComponent component, String borderLayoutConstraint) {
        panel.add(component, borderLayoutConstraint);
    }

    /**
     * customizeLabel
     * @param label {@link JLabel}
     */
    protected void customizeLabel(JLabel label, Map<TextAttribute, Object> textAttributeObjectMap) {
        Font font = label.getFont().deriveFont(textAttributeObjectMap);
        label.setFont(font);
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public void setWeightX(double weightX) {
        this.weightX = weightX;
    }

    public void setWeightY(double weightY) {
        this.weightY = weightY;
    }

    public void setAnchor(int anchor) {
        this.anchor = anchor;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public void setInsets(Insets insets) {
        this.insets = insets;
    }

    public void setIpadX(int ipadX) {
        this.ipadX = ipadX;
    }

    public void setIpadY(int ipadY) {
        this.ipadY = ipadY;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        this.panel.setLayout(layoutManager);
    }

    public void setLayoutManager(LayoutManager2 layoutManager) {
        this.panel.setLayout(layoutManager);
    }

    public void setPanelSize(Dimension dimension) {
        mainFrame.setSize(dimension);
        this.panel.setSize(dimension);
    }
}
