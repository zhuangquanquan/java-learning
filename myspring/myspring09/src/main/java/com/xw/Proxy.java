package com.xw;

public class Proxy implements Rent {
    private Host host;
    //public Proxy(){}
    public Proxy(Host host) {
      this.host = host;
    }
    public void rent() {
        seeHouse();
        host.rent();
        fare();
    }

    public void seeHouse(){
        System.out.println("帶客戶看房");
    }

    public void fare(){
        System.out.println("收中介費");
    }
}
