package org.idea.analytics.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptHandler extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(RequestInterceptHandler.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Got Request from "+request.getRemoteUser());
        logger.info("Handling request "+ request.getRequestURI());
        return super.preHandle(request, response, handler);
    }
}
