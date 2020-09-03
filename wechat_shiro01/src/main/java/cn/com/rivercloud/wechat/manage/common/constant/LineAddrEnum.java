package cn.com.rivercloud.wechat.manage.common.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LineAddrEnum {

    jx_nc(1, "南昌市"),
    jx_jj(2, "九江市"),
    jx_jdz(3, "景德镇市"),
    jx_px(4, "萍乡市"),
    jx_xy(5, "新余市"),
    jx_yt(6, "鹰潭市"),
    jx_gz(7, "赣州市"),
    jx_yc(8, "宜春市"),
    jx_sr(9, "上饶市"),
    jx_ja(10, "吉安市"),
    jx_fz(11, "抚州市");

    private int type;
    private String summary;

    LineAddrEnum(int type, String summary) {
        this.type = type;
        this.summary = summary;
    }

    public int getType() {
        return type;
    }

    public String getSummary() {
        return summary;
    }

    public static boolean containsType(int type) {
        Optional<LineAddrEnum> first = Arrays.stream(values()).filter(e -> e.getType() == type).findFirst();
        return first.isPresent();
    }

    public static Map<Integer, LineAddrEnum> toMap() {
        return Arrays.stream(values()).collect(Collectors.toMap(e -> e.getType(), Function.identity()));
    }

}
