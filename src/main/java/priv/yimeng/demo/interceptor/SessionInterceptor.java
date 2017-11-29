package priv.yimeng.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
public class SessionInterceptor implements HandlerInterceptor {

    private static final String LOGIN_VIEW = "/login_view";
    private static final String LOGIN = "/login";

    private static final String SESSION_USER = "SESSION_USER";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (LOGIN_VIEW.equals(requestURI) || LOGIN.equals(requestURI)) {
            return true;
        }

        Object obj = request.getSession().getAttribute(SESSION_USER);
        if (obj == null) {
            response.sendRedirect(LOGIN_VIEW);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
