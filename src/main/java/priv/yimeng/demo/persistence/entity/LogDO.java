package priv.yimeng.demo.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
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
class LogDO implements Serializable {

    private static final long serialVersionUID = -6353147869679311087L;

    /**
     * 编号
     */
    @Id
    @GeneratedValue
    @Column(name = "log_id")
    private Long id;
    /**
     * 客户端请求ip
     */
    @Column(name = "log_client_ip")
    private String clientIp;

    @Column(name = "log_uri")
    private String uri;

    @Column(name = "log_type")
    private String type;

    @Column(name = "log_method")
    private String method;

    @Column(name = "log_param_data")
    private String paramData;

    @Column(name = "log_session_id")
    private String sessionId;

    @Column(name = "log_time")
    private Timestamp time;

    @Column(name = "log_return_time")
    private Timestamp returnTime;

    @Column(name = "log_return_data")
    private String returnData;

    @Column(name = "log_http_status_code")
    private String httpStatusCode;

    @Column(name = "log_time_consuming")
    private Integer timeConsuming;

}
