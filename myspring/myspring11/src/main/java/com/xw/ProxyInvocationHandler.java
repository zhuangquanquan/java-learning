package com.xw;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {

    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Object getProxy() {
        Class<?>[] interfaces = Rent.class.getInterfaces();
        System.out.println(interfaces);
        return  Proxy.newProxyInstance(this.getClass().getClassLoader(), Rent.class.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seeHouse();
        Object result = method.invoke(rent, args);
        pay();
        return result;
    }

    public void seeHouse() {
        System.out.println("帶客戶看房");
    }
    public void pay() {
        System.out.println("收取中介費");
    }
}
