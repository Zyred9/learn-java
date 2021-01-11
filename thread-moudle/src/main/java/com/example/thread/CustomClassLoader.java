package com.example.thread;

import java.io.*;

/**
 * <p>
 *      自定义类加载器
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class CustomClassLoader extends ClassLoader {

    private String root;

    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = this.findClassData(name);

        if (null != bytes) {
            return this.defineClass(name, bytes, 0, bytes.length);
        } else {
            throw new ClassNotFoundException("Class not found :" + name);
        }
    }


    private byte[] findClassData(String className) {
        String fileName = root + File.separatorChar + className.replace('.', File.separatorChar) + ".class";

        try {
            InputStream is = new FileInputStream(fileName);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            int buffSize = 1024;

            byte[] buff = new byte[buffSize];

            int len = 0;
            while ((len = is.read(buff)) != -1) {
                bao.write(buff, 0, len);
            }
            return bao.toByteArray();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomClassLoader classLoader = new CustomClassLoader();
        classLoader.setRoot("E:\\workspace\\gitWorkspace\\learn-java\\thread-moudle\\target\\classes");

        Class<?> clazz = classLoader.findClass("com.example.thread.TestClass");

        Object instance = clazz.newInstance();

        System.out.println(instance.getClass().getClassLoader());

    }

}
