package cn.com.rivercloud.wechat.test;


import org.openjdk.jol.info.ClassLayout;
import static java.lang.System.out;

public class JOLSample_04_Basic {
    public static void main(String[] args){
        out.println(ClassLayout.parseInstance(A.class).toPrintable());
        out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    static class A {
       private byte[] a;
    }
}