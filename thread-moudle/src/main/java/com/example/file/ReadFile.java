package com.example.file;

import java.io.*;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ReadFile {

    static String regex = "：";


    public static void main(String[] args) {
        readFileByLine("C:\\Users\\Administrator\\Desktop\\roback.log");
    }
    /**
     * 按行读取文件
     *
     * @param strFile
     */
    public static void readFileByLine(String strFile) {
        try {
            File file = new File(strFile);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strLine = null;
            StringBuilder sb = new StringBuilder();
            int lineCount = 1;
            while (null != (strLine = bufferedReader.readLine())) {

                sb.append(strLine.substring(0, strLine.lastIndexOf(regex)))
                        .append(",");

                lineCount++;
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
