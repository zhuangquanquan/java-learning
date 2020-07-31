package cn.com.rivercloud.wechat.common.lang;

/**
 * @Description TODO
 * @Author wanghao@cncloudsec.com
 * @Date 19-5-7 下午3:13
 */
public interface Pagination {

    int getCurrent();

    int getPageSize();

    long getTotal();

    void setTotal(long total);

    int getOffset();
}
