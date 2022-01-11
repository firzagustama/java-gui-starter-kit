/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All right reserved.
 */
package model.comboItem;

import model.User;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: UserComboItem.java, v 0.1 2022-01-11 17.51 Muhammad Firza Gustama Exp $$
 */
public class UserComboItem extends BaseComboItem<User> {
    /**
     * Default Construction, save model and set label to show to user
     * @param model
     * @param label
     */
    public UserComboItem(User model, String label) {
        super(model, model.getPrimaryKey() + " - "  + model.getUsername());
    }
}
