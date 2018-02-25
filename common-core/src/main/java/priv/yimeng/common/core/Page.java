package priv.yimeng.common.core;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.ToString;
import priv.yimeng.common.core.Order.Direction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * @author yimeng
 * @version 1.0
 */
@Getter
@ToString
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -2053800594583879853L;
    private static final Long MAX_NUMBER = 10000L;

    /**
     * 内容
     */
    private final List<T> content = new ArrayList<>();

    /**
     * 总记录数
     */
    private final Long total;

    /**
     * 分页信息
     */
    @JSONField(serialize = false)
    private final Pageable pageable;

    /**
     * 初始化一个新创建的Page对象
     */
    public Page() {
        this.total = 0L;
        this.pageable = new Pageable();
    }

    /**
     * @param content  内容
     * @param total    总记录数
     * @param pageable 分页信息
     */
    public Page(List<T> content, long total, Pageable pageable) {
        this.content.addAll(content);
        this.total = total;
        this.pageable = pageable;
    }

    /**
     * 获取页码
     *
     * @return 页码
     */
    public int getPageNumber() {
        return pageable.getPageNumber();
    }

    /**
     * 获取每页记录数
     *
     * @return 每页记录数
     */
    public int getPageSize() {
        return pageable.getPageSize();
    }

    /**
     * 获取搜索属性
     *
     * @return 搜索属性
     */
    public String getSearchProperty() {
        return pageable.getSearchProperty();
    }

    /**
     * 获取搜索值
     *
     * @return 搜索值
     */
    public String getSearchValue() {
        return pageable.getSearchValue();
    }

    /**
     * 获取排序属性
     *
     * @return 排序属性
     */
    public String getOrderProperty() {
        return pageable.getOrderProperty();
    }

    /**
     * 获取排序方向
     *
     * @return 排序方向
     */
    public Direction getOrderDirection() {
        return pageable.getOrderDirection();
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    @JSONField(serialize = false)
    public List<Order> getOrders() {
        return pageable.getOrders();
    }

    /**
     * 获取筛选
     *
     * @return 筛选
     */
    @JSONField(serialize = false)
    public List<Filter> getFilters() {
        return pageable.getFilters();
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public int getTotalPages() {
        long total = getTotal();
        if (total > MAX_NUMBER) {
            total = MAX_NUMBER;
        }
        return (int) Math.ceil((double) total / (double) getPageSize());
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public List<T> getContent() {
        return content;
    }

}