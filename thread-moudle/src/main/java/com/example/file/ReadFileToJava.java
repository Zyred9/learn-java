package com.example.file;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class ReadFileToJava {

    static final String class_annotation = "园区基本信息";
    static final String name = "parkInfo";


    /**********以下是不需要修改的内容**********/
    static final String path = "C:\\Users\\Administrator\\Desktop\\";
    static final String write_path = "E:\\workspace\\svnWorkspace\\wsyq\\src\\main\\java\\com\\zzyt\\model\\vo\\third\\park\\";

    static final String url = path + "roback.log";
    static final String regex = "\t";
    static final String class_file_name = "Third" + firstUp(name) + "Vo";


    public static void main(String[] args) {
        File file = new File(url);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {

            String strLine;
            StringBuilder sb = new StringBuilder();
            prefix(sb, class_file_name);
            while (null != (strLine = bufferedReader.readLine())) {
                String msg = strLine;
                try {
                    String[] split = strLine.split(regex);
                    strLine = split[0];
                    String annotation = split[1];
                    String type = split[2];
                    String remark = split[4];
                    strLine = strLine.replaceAll("\"", "");
                    strLine = strLine.replaceAll("\\s+", "");
                    strLine = strLine.replaceAll("\\uFEFF", "");
                    // 生成Java文件
                    if (remark.equals("　")) {
                        sb.append("\t/** ").append(annotation).append(" **/\n");
                    } else {
                        sb.append("\t/** \n\t * ").append(annotation).append("\n\t * ").append(remark).append("\n\t **/\n");
                    }
                    sb.append("\tprivate ").append(firstUpString(type)).append(" ").append(strLine).append(";").append("\r\n");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(msg);
                }
            }
            suffix(sb);

            write(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void write(StringBuilder sb) {
        String fileName = write_path + class_file_name.concat(".java");
        try (FileOutputStream o = new FileOutputStream(fileName);) {
            o.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void prefix(StringBuilder sb, String name) {
        sb.append("package com.zzyt.model.vo.third.park;\n\n")
                .append("import lombok.Getter;\n")
                .append("import lombok.Setter;\n")
//                .append("import lombok.experimental.Accessors;\n")
                .append("/**\n" +
                        " * <p>\n *\t\t\t" +
                        class_annotation + "\n" +
                        " * </p>\n" +
                        " *\n" +
                        " * @author zyred\n" +
                        " * @since v 0.1\n" +
                        " **/\n")
                .append("@Setter\n")
                .append("@Getter\n")
//                .append("@Accessors(chain =true)\n")
                .append("public class ").append(name).append(" {\n");
    }

    private static void suffix(StringBuilder sb) {
        sb.append("}");
    }


    private static String firstUp(String str) {
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return new String(chars);
    }

    private static String firstUpString(String str) {
        if (!str.equals("string")) {
            return str;
        }
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return new String(chars);
    }
}
