/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package session;

import model.User;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: Session.java, v 0.1 2021-12-19 17.16 Muhammad Firza Gustama Exp $$
 */
public class Session {
    /** session */
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }
}
