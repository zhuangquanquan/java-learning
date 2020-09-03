package cn.com.rivercloud.wechat.test;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TheUnsafeTest {

    @SneakyThrows
    public static void main(String[] args) {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        String[] strings = new String[]{"1", "2", "3"};
        long i = unsafe.arrayBaseOffset(String[].class);
        System.out.println("string[] base offset is :" + i);

        //分配一个8byte的内存
        long address = unsafe.allocateMemory(8L);
//初始化内存填充1
        unsafe.setMemory(address, 8L, (byte) 1);
//测试输出
        System.out.println("add byte to memory:" + unsafe.getInt(address));

    }
}
