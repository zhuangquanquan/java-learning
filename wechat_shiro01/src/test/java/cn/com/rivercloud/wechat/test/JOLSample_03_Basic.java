package cn.com.rivercloud.wechat.test;


import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

public class JOLSample_03_Basic {
    public static void main(String[] args){
        out.println(ClassLayout.parseInstance(new Object()).toPrintable());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println(ClassLayout.parseInstance(new Object()).toPrintable());
    }
}