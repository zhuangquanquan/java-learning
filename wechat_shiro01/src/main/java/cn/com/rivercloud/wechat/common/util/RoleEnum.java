package cn.com.rivercloud.wechat.common.util;


public enum RoleEnum {

    admin("admin", "后台管理员"),
    manage("manage", "电信客户经理"),
    customer("customer", "用户");

    private String type;
    private String summary;

    RoleEnum(String type, String summary) {
        this.type = type;
        this.summary = summary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
