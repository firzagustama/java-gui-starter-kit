/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All right reserved.
 */
package repository;

import model.User;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: UserRepository.java, v 0.1 2022-01-13 19.29 Muhammad Firza Gustama Exp $$
 */
public class UserRepository extends BaseRepository<User> {
    @Override
    protected User getModel() {
        return new User();
    }
}
