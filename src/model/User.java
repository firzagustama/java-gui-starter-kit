/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All right reserved.
 */
package model;

import java.lang.reflect.Type;

/**
 * Each model must extends BaseModel to make it usable for repository
 *
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: User.java, v 0.1 2022-01-11 17.50 Muhammad Firza Gustama Exp $$
 */
public class User extends BaseModel{
    private String id;
    private String username;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * set primary key column name
     * @return
     */
    @Override
    public String setPrimaryKey() {
        return "id";
    }

    /**
     * set table name
     * @return
     */
    @Override
    public String setTableName() {
        return "user";
    }

    /**
     * set primary key type
     * @return
     */
    @Override
    public Type setPrimaryKeyType() {
        return String.class;
    }

    /**
     * return primary key value in this model
     * @return
     */
    @Override
    public Object getPrimaryKeyValue() {
        return this.id;
    }
}
