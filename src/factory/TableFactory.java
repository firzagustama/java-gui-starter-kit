/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package factory;

import model.BaseModel;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: TableFactory.java, v 0.1 2021-12-19 20.13 Muhammad Firza Gustama Exp $$
 */
public class TableFactory {
    // table factory -> convert data from repository to supported JTable data
    /**
     * Convert data from repository to supported JTable data
     * @param data {@link List}
     * @return {@link Object[][]}
     */
    public static Object[][] toRowData(List<? extends BaseModel> data) {
        // empty data
        if (data == null || data.isEmpty()) {
            return new Object[0][0];
        }

        int dataSize = data.size();
        int attrSize = data.get(0).getClass().getDeclaredFields().length;
        Object[][] out = new Object[dataSize][attrSize];

        try {
            int i = 0;
            for (BaseModel model : data) {
                int j = 0;
                Field[] fields = model.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    out[i][j++] = field.get(model);
                }
                i++;
            }

            return out;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse data to table column");
        }
    }

    /**
     * Convert data from JTable data to Model
     * @param data {@link Object[]}
     * @param clazz {@link Class}
     * @return {@link Object}
     */
    public static BaseModel toModel(Object[] data, Class<? extends BaseModel> clazz) {
        if (data == null || data.length == 0) {
            return null;
        }

        try {
            BaseModel model = clazz.newInstance();

            int i = 0;
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(model, data[i++]);
            }

            return model;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
