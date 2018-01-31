package priv.yimeng.demo.persistence;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-31
 *
 * @author yimeng
 * @version 1.0
 */
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 520527763554402189L;

    /**
     * 类型
     */
    public enum Type {

        /**
         * 成功
         */
        success,

        /**
         * 警告
         */
        warn,

        /**
         * 错误
         */
        error
    }

    /**
     * 类型
     */
    private Type type;

    /**
     * 内容
     */
    private String content;

    /**
     * 数据
     */
    private Serializable data;

    /**
     * 初始化一个新创建的 Message 对象，使其表示一个空消息。
     */
    public Message() {

    }

    /**
     * 初始化一个新创建的 Message 对象
     *
     * @param type    类型
     * @param content 内容
     */
    public Message(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * 返回成功消息
     *
     * @param content 内容
     * @param args    参数
     * @return 成功消息
     */
    public static Message success(String content, Object... args) {
        return new Message(Type.success, content);
    }

    /**
     * 返回警告消息
     *
     * @param content 内容
     * @param args    参数
     * @return 警告消息
     */
    public static Message warn(String content, Object... args) {
        return new Message(Type.warn, content);
    }

    /**
     * 返回错误消息
     *
     * @param content 内容
     * @param args    参数
     * @return 错误消息
     */
    public static Message error(String content, Object... args) {
        return new Message(Type.error, content);
    }

}
