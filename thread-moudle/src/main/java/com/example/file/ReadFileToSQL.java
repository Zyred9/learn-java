package com.example.file;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *          读取文件转换为sql
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ReadFileToSQL {

    static final String tableName = "t_laws_library";
    static final String tableChineseName = "法律文库" + "表";
    static final String path = "C:\\Users\\Administrator\\Desktop\\test.sql";
    static final String regex = "\t";

    public static void main(String[] args) {

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("map", "123");

        try {
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strLine = null;
            StringBuilder sb = new StringBuilder();
            prefix1(sb);
            while (null != (strLine = bufferedReader.readLine())) {
                if (StringUtils.isEmpty(strLine)) {continue;}

                String[] split = strLine.split(regex);
                String fields = split[0];
                String type = split[1];

                /*if (!split[3].equals("　")) {
                    comm = comm + split[3];
                }*/
                if (split.length > 4 && !split[4].equals("　")) {
//                    type = type +"("+ split[4]+")";
                    String comm = split[4];
                    sb.append("`").append(fields).append("`").append(type/*sqlType(split[1], split[4])*/).append(" DEFAULT NULL COMMENT '").append(comm).append("',\n");
                }else{
                    sb.append("`").append(fields).append("`").append(/*sqlType(split[2],"")*/type).append(" DEFAULT NULL COMMENT '").append(type).append("',\n");
                }
            }
            /*sb.append("`create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                    "`update_time` datetime DEFAULT NULL COMMENT '更新时间',\n" +
                    "`create_by` varchar(32) DEFAULT NULL COMMENT '创建人',\n" +
                    "`update_by` varchar(32) DEFAULT NULL COMMENT '更新人',\n" +
                    "`version` int(11) DEFAULT NULL COMMENT '版本号',\n" +
                    "`deleted` int(1) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',");*/
            sb.append("PRIMARY KEY (`id`) USING BTREE\n")
                    .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='").append(tableChineseName).append("';");
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void prefix1(StringBuilder sb) {

//        char[] charArray = tableName.toCharArray();
//        StringBuilder realTableName = new StringBuilder();
//        for (char c : charArray) {
//            if (c >= 'A' && c <= 'Z') {
//                realTableName.append("_").append((char) (c + 32));
//            } else {
//                realTableName.append(c);
//            }
//        }
//        String newTableName = "t_" + new String(realTableName);
        sb.append("create table ").append(tableName).append("(\n")
                .append("`id` varchar(32) NOT NULL COMMENT '主键',\n");
    }



    private static String sqlType (String javaType, String info) {
        if (javaType.equals("string") && info.contains("0：")) {
            return " varchar(1)";
        } else if (javaType.equals("string") && info.contains("01：")) {
            return " varchar(2)";
        } else if (javaType.equals("string") && info.contains("YYYY-MM-DD") && info.length() <= 10) {
            return " date";
        } else if (javaType.equals("string") && info.contains("YYYY-MM-DD") && info.length() > 10) {
            return " datetime";
        } else if (javaType.equals("string")) {
            return " varchar(32)";
        } else if (javaType.equals("double")) {
            return " decimal(20, 10)";
        }
        return null;
    }
}
