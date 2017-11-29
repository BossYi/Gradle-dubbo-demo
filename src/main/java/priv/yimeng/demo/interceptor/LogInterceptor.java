package priv.yimeng.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import priv.yimeng.demo.persistence.entity.LogDO;
import priv.yimeng.demo.service.LogService;
import priv.yimeng.demo.utils.LogUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    /**
     * 请求开始时间标识
     */
    private static final String LOG_SEND_TIME = "send_time";
    /**
     * 请求日志实体标识
     */
    private static final String LOG_DO = "log_do";

    /**
     * 进入SpringMVC的Controller之前开始记录日志实体
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return 下一步
     * @throws Exception ex
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogDO logDO = new LogDO();
        String sessionId = request.getRequestedSessionId();
        //请求路径
        String url = request.getRequestURI();
        //获取请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        //设置客户端ip
        logDO.setClientIp(LogUtil.getClientIp(request));
        //设置请求方法
        logDO.setMethod(request.getMethod());
        //设置请求类型（json|普通请求）
        logDO.setType(LogUtil.getRequestType(request));
        //设置请求参数内容json字符串
        logDO.setParamData(paramData);
        //设置请求地址
        logDO.setUri(url);
        //设置sessionId
        logDO.setSessionId(sessionId);
        //设置请求开始时间
        long currentTime = System.currentTimeMillis();
        logDO.setTime(new Timestamp(currentTime));
        request.setAttribute(LOG_SEND_TIME, currentTime);

        //设置请求实体到request内，方面afterCompletion方法调用
        request.setAttribute(LOG_DO, logDO);
        log.info("request is coming!!");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //获取请求错误码
        int status = response.getStatus();
        //当前时间
        long currentTime = System.currentTimeMillis();
        //请求开始时间
        long time = Long.valueOf(request.getAttribute(LOG_SEND_TIME).toString());
        //获取本次请求日志实体
        LogDO logDO = (LogDO) request.getAttribute(LOG_DO);
        //设置请求时间差
        logDO.setTimeConsuming(Integer.valueOf((currentTime - time) + ""));
        //设置返回时间
        logDO.setReturnTime(new Timestamp(currentTime));
        //设置返回错误码
        logDO.setHttpStatusCode(status + "");
        //设置返回值

        //执行将日志写入数据库
        LogService logService = getInjectedLogService(request);
        logService.save(logDO);

    }

    private LogService getInjectedLogService(HttpServletRequest request) {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(LogService.class);
    }

}
