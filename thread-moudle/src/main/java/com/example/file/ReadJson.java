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
public class ReadJson {

    static final String path = "C:\\Users\\Administrator\\Desktop\\";
    static final String url = path + "roback.log";

    public static void main(String[] args) {
        File file = new File(url);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String strLine;
            while (null != (strLine = bufferedReader.readLine())) {
                strLine = strLine.replaceAll("    ", "").replaceAll(": ", ":").replaceAll(",", ",");
                System.out.print(strLine);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
