package cn.com.rivercloud.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /*@RequestMapping({"/", "/index"})
    public String fowardIndex() {
        //return "index";
        System.out.println("call => " + "index");
        return "index";
    }*/

    @RequestMapping("/a")
    public String a() {
        //return "index";
        System.out.println("call => " + "ab");
        return "a";
    }

    @RequestMapping("/ab")
    public String ab() {
        //return "index";
        System.out.println("call => " + "ab");
        return "a.html";
    }
}
