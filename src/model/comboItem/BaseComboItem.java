/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package model.comboItem;

import model.BaseModel;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: BaseComboItem.java, v 0.1 2021-12-23 17.22 Muhammad Firza Gustama Exp $$
 */
public abstract class BaseComboItem<M extends BaseModel> {
    private M model;
    private String label;

    /**
     * Default Constructor
     * @param model
     * @param label
     */
    public BaseComboItem(M model, String label) {
        this.model = model;
        this.label = label;
    }

    /**
     * Label Only Constructor
     * @param label
     */
    public BaseComboItem(String label) {
        this.label = label;
    }

    /**
     * getModel
     * @return
     */
    public M getModel() {
        return model;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return this.label;
    }
}
