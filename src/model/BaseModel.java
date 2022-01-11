/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package model;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: BaseModel.java, v 0.1 2021-12-19 17.29 Muhammad Firza Gustama Exp $$
 */
public abstract class BaseModel {

    // auto query in repository
    private final String tableName;
    private final String primaryKey;
    private final Type primaryKeyType;

    public abstract String setPrimaryKey();
    public abstract String setTableName();
    public abstract Type setPrimaryKeyType();
    public abstract Object getPrimaryKeyValue();

    /**
     * default constructor
     */
    public BaseModel() {
        this.primaryKey = setPrimaryKey();
        this.tableName = setTableName();
        this.primaryKeyType = setPrimaryKeyType();
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public Type getPrimaryKeyType() {
        return primaryKeyType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("[");

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            sb.append(field.getName());
            sb.append("=");
            try {
                sb.append(field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sb.append(";");
        }

        sb.deleteCharAt(sb.length() - 1); // remove last ';' character
        sb.append("]");
        return sb.toString();
    }
}
