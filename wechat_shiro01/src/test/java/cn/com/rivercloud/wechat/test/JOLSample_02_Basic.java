package cn.com.rivercloud.wechat.test;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class JOLSample_02_Basic {
    /**
     * 此示例展示了基本字段布局，通过此示例可以了解到：
     * a) 对象头消耗了多少；
     * b) 字段的布局方法
     * c) 基础数据类型所占内存情况​
     * d) 对其填充数据
     */
    public static void main(String[] args){
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(JOLSample_01_Basic.A.class).toPrintable());
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
