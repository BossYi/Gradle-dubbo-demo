package priv.yimeng.demo.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
@Entity
@Table(name = "ci_log")
public @Data
class LogDO {

    //编号
    @Id
    @GeneratedValue
    @Column(name = "log_id")
    private Long id;
    //客户端请求ip
    @Column(name = "log_client_ip")
    private String clientIp;
    //客户端请求路径
    @Column(name = "log_uri")
    private String uri;
    //终端请求方式,普通请求,ajax请求
    @Column(name = "log_type")
    private String type;
    //请求方式method,post,get等
    @Column(name = "log_method")
    private String method;
    //请求参数内容,json
    @Column(name = "log_param_data")
    private String paramData;
    //请求接口唯一session标识
    @Column(name = "log_session_id")
    private String sessionId;
    //请求时间
    @Column(name = "log_time")
    private Timestamp time;
    //接口返回时间
    @Column(name = "log_return_time")
    private Timestamp returnTime;
    //接口返回数据json
    @Column(name = "log_return_data")
    private String returnData;
    //请求时httpStatusCode代码，如：200,400,404等
    @Column(name = "log_http_status_code")
    private String httpStatusCode;
    //请求耗时毫秒单位
    @Column(name = "log_time_consuming")
    private Integer timeConsuming;

}
