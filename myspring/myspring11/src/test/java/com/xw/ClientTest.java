package com.xw;

public class ClientTest {

    public static void main(String[] args) {
        Host host = new Host();
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setRent(host);
        Host obj = (Host) proxyInvocationHandler.getProxy();
        obj.rent();
    }
}
