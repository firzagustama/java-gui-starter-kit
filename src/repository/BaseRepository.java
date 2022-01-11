/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package repository;

import db.DbCon;
import model.BaseModel;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: BaseRepository.java, v 0.1 2021-12-19 17.25 Muhammad Firza Gustama Exp $$
 */
public abstract class BaseRepository<M extends BaseModel> {

    private final String tableName;
    private final String primaryKey;
    private final Type primaryKeyType;

    /**
     * Default Constructor
     */
    public BaseRepository() {
        M model = getModel();
        this.tableName = camelToSnake(model.getTableName());
        this.primaryKey = camelToSnake(model.getPrimaryKey());
        this.primaryKeyType = model.getPrimaryKeyType();
    }

    /**
     * delete by Model
     * @param model
     * @return
     */
    public boolean delete(M model) {
        Connection con = DbCon.getConnection();

        try {
            PreparedStatement statement = con.prepareStatement(String.format("DELETE FROM %s WHERE %s = ?", model.getTableName(), model.getPrimaryKey()));
            statement.setObject(1, model.getPrimaryKeyValue());

            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * insert
     * @param model {@link ? extends BaseModel}
     * @return {@link Boolean}
     */
    public boolean insert(M model) {
        Connection con = DbCon.getConnection();

        try {
            StringBuilder queryStatement = new StringBuilder(String.format("INSERT INTO %s VALUES (", tableName));

            Field[] fields = model.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().equals(String.class) ||
                    field.getType().equals(Timestamp.class)
                ) {
                    // String
                    queryStatement.append("'");
                    queryStatement.append(field.get(model));
                    queryStatement.append("',");
                } else {
                    // int
                    queryStatement.append(field.get(model));
                    queryStatement.append(",");
                }
            }
            queryStatement.deleteCharAt(queryStatement.length() - 1);
            queryStatement.append(")");

            con.createStatement().execute(queryStatement.toString());
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * update
     * @param model {@link ? extends BaseModel}
     * @return {@link Boolean}
     */
    public boolean update(M model) {
        Connection con = DbCon.getConnection();

        try {
            StringBuilder queryStatement = new StringBuilder(String.format("UPDATE %s", tableName));

            Field[] fields = model.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (i == 0) {
                    queryStatement.append(" set");
                }
                queryStatement.append(String.format(" %s = ", camelToSnake(field.getName())));

                field.setAccessible(true);
                if (field.getType().equals(String.class) ||
                    field.getType().equals(Timestamp.class)
                ) {
                    // String
                    queryStatement.append("'");
                    queryStatement.append(field.get(model));
                    queryStatement.append("',");
                } else {
                    // int
                    queryStatement.append(field.get(model));
                    queryStatement.append(",");
                }
            }
            queryStatement.deleteCharAt(queryStatement.length() - 1);
            queryStatement.append(String.format(" WHERE %s = ", camelToSnake(model.getPrimaryKey())));
            if (primaryKeyType.equals(String.class)) {
                queryStatement.append("'");
                queryStatement.append(model.getPrimaryKeyValue());
                queryStatement.append("'");
            } else {
                queryStatement.append(model.getPrimaryKeyValue());
            }
            queryStatement.append(";");

            System.out.println(queryStatement.toString());

            con.createStatement().execute(queryStatement.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * queryById
     * @param id
     * @return
     */
    public M getById(String id) {
        Connection con = DbCon.getConnection();

        try {
            String queryStatement = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, primaryKey, id);
            ResultSet rs = con.createStatement().executeQuery(queryStatement);
            if (!rs.next()) {
                // no data found
                return null;
            }

            M model = resultSetToModel(rs);
            rs.close();
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query");
        }
    }

    /**
     * queryAll
     * @return {@link List<BaseModel>}
     */
    public List<M> getAll() {
        Connection con = DbCon.getConnection();

        List<M> models = new ArrayList<>();
        try {
            String queryStatement = String.format("SELECT * FROM %s", tableName);
            ResultSet rs = con.createStatement().executeQuery(queryStatement);
            while (rs.next()) {
                models.add(resultSetToModel(rs));
            }
            return models;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query");
        }
    }

    /**
     * getChildModel
     * @return {@link BaseModel}
     */
    protected abstract M getModel();

    /**
     * ResultSetToModel
     * @param rs
     * @return
     */
    protected M resultSetToModel(ResultSet rs) {
        M model = getModel();

        try {
            Field[] fields = model.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(model, rs.getObject(camelToSnake(field.getName())));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to parse result set");
        }

        return model;
    }

    /**
     * Reference https://www.geeksforgeeks.org/convert-camel-case-string-to-snake-case-in-java/
     * @param str {@link String}
     * @return {@link String}
     */
    private String camelToSnake(String str)
    {

        // Empty String
        String result = "";

        // Append first character(in lower case)
        // to result string
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);

        // Traverse the string from
        // ist index to last index
        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result
                        = result
                        + Character.toLowerCase(ch);
            }

            // If the character is lower case then
            // add such character into result string
            else {
                result = result + ch;
            }
        }

        // return the result
        return result;
    }
}
