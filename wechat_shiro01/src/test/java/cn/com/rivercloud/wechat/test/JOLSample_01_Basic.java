package cn.com.rivercloud.wechat.test;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static java.lang.System.out;
public class JOLSample_01_Basic {

    public static Unsafe UNSAFE;
    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        out.println(VM.current().details());
        try {
            out.println(UNSAFE.objectFieldOffset(A.class.getDeclaredField("a1")));
            out.println(ClassLayout.parseClass(A.class).toPrintable());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static class A {
        boolean a1;
        byte a2;
        short a3;
        char a4;
        int a5;
        long a6;
        float a7;
        double a8;
    }
}