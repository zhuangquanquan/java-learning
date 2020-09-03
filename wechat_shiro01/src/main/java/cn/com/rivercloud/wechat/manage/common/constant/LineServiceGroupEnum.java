package cn.com.rivercloud.wechat.manage.common.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LineServiceGroupEnum {

    monitor(1, "安全检测服务套餐"),
    base(2, "基础防护服务套餐"),
    advance(3, "高级防护服务套餐"),
    monitor_180(4, "安全监测服务套餐（带180天安全日志存储服务）"),
    base_180(5, "基础防护服务套餐（带180天安全日志存储服务）"),
    advance_180(6, "高级防护服务套餐（带180天安全日志存储服务）");

    private int type;
    private String summary;

    LineServiceGroupEnum(int type, String summary) {
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
        Optional<LineServiceGroupEnum> first = Arrays.stream(values()).filter(e -> e.getType() == type).findFirst();
        return first.isPresent();
    }

    public static Map<Integer, LineServiceGroupEnum> toMap() {
        return Arrays.stream(values()).collect(Collectors.toMap(e -> e.getType(), Function.identity()));
    }
}
