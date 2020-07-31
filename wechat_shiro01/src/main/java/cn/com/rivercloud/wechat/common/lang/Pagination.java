package cn.com.rivercloud.wechat.common.lang;

public interface Pagination {

    int getCurrent();

    int getPageSize();

    long getTotal();

    void setTotal(long total);

    int getOffset();
}
