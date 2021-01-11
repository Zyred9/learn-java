package com.example.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 方法区内存溢出
 * </p>
 * <p>
 * 依赖 asm 框架
 *
 * java.lang.OutOfMemoryError: Metaspace
 *
 * @author zyred
 * @since v 0.1
 **/
public class MetaspaceOOM extends ClassLoader {

    static List<Class<?>> list = new ArrayList<>();

    public static List<Class<?>> createClasses() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (int i = 0; i < 10000000; ++i) {
            ClassWriter cw = new ClassWriter(0);
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
            MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mw.visitVarInsn(Opcodes.ALOAD, 0);
            mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mw.visitInsn(Opcodes.RETURN);
            mw.visitMaxs(1, 1);
            mw.visitEnd();
            MetaspaceOOM test = new MetaspaceOOM();
            byte[] code = cw.toByteArray();
            Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
            classes.add(exampleClass);
        }
        return classes;
    }


    /**
     * java.lang.OutOfMemoryError: Metaspace
     * @param args
     */
    public static void main(String[] args) {
        while (true){
            list.addAll(createClasses());
        }
    }
}
