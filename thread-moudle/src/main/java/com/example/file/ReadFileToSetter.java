package com.example.file;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ReadFileToSetter {

    private static final String className = "ParkInfo";

    private static final String regex = " ";

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
            prefix(sb);
            while (null != (strLine = bufferedReader.readLine())) {
                if (StringUtils.isEmpty(strLine)) { continue; }
                strLine = strLine.replaceAll(" {4}", "").replaceAll(";", "");
                String[] split = strLine.split(regex);
                sb.append(".set").append(firstUpCase(split[2])).append("(").append(")\n");
            }
            sb.append(";");
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void prefix(StringBuilder sb) {
        sb.append("new ").append(ReadFileToSetter.className).append("()");
    }

    private static String firstUpCase (String key) {
        char[] chars = key.toCharArray();
        chars[0] -= 32;
        return new String(chars);
    }


}
