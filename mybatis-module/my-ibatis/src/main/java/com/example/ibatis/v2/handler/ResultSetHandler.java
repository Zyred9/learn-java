package com.example.ibatis.v2.handler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * 结果集处理器
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/17 17:28
 **/
public class ResultSetHandler {


    /**
     * 处理结果集
     *
     * @param pojoClazz 被处理成这类的对象
     * @param resultSet 从mysql查询出来的内容
     * @param <T>       结果集
     * @return
     */
    public <T> T handlerResult(Class<?> pojoClazz, ResultSet resultSet) {
        try {
            T instance = (T) pojoClazz.newInstance();
            Field[] pojoFields = instance.getClass().getDeclaredFields();
            if (resultSet.next()) {
                for (Field field : pojoFields) {
                    field.setAccessible(true);
                    Object fieldValue = getResult(resultSet, field);
                    field.set(instance, fieldValue);
                }
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private Object getResult(ResultSet rs, Field field) throws SQLException {
        //TODO TypeHandler
        Class type = field.getType();
        // 驼峰转下划线
        String dataName = HumpToUnderline(field.getName());
        // TODO 类型判断不够全
        if (Integer.class == type) {
            return rs.getInt(dataName);
        } else if (String.class == type) {
            return rs.getString(dataName);
        } else if (Long.class == type) {
            return rs.getLong(dataName);
        } else if (Boolean.class == type) {
            return rs.getBoolean(dataName);
        } else if (Double.class == type) {
            return rs.getDouble(dataName);
        } else {
            return rs.getString(dataName);
        }
    }

    public static String HumpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        if (!para.contains("_")) {
            for (int i = 0; i < para.length(); i++) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

}
