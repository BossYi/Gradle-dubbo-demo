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
    private String msg;

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
     * @param type 类型
     * @param msg  内容
     */
    public Message(Type type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 Message 对象
     *
     * @param type 类型
     * @param msg  内容
     * @param data 数据
     */
    public Message(Type type, String msg, Serializable data) {
        this.type = type;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static Message success(String msg) {
        return new Message(Type.success, msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  内容
     * @param data 数据
     * @return 成功消息
     */
    public static Message success(String msg, Serializable data) {
        return new Message(Type.success, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 内容
     * @param args    参数
     * @return 警告消息
     */
    public static Message warn(String msg) {
        return new Message(Type.warn, msg);
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @param args    参数
     * @return 错误消息
     */
    public static Message error(String msg) {
        return new Message(Type.error, msg);
    }

}
