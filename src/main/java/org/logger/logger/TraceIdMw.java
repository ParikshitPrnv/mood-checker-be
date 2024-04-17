package org.logger.logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.logger.logger.loggingFramework.LogStructure;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;


@Component
@NoArgsConstructor
public class TraceIdMw implements HandlerInterceptor {

    public static ThreadLocal<LogStructure> requestIdHolder = new ThreadLocal<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogStructure logStructure = new LogStructure();
        UUID uuid = UUID.randomUUID();

        logStructure.setReqId(uuid);
        requestIdHolder.set(logStructure);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LogStructure.logIncomingApi(request, response);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        requestIdHolder.remove();
    }

    public static LogStructure getLogWithRequestId() {
        return requestIdHolder.get();
    }
}
