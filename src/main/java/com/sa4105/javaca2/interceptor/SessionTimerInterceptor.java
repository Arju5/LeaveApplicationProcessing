package com.sa4105.javaca2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionTimerInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(SessionTimerInterceptor.class);

    private static final long MAX_INACTIVE_SESSION_TIME = 600 *1000;
    @Autowired
    private HttpSession session;

    /**
     * Executed before actual handler is executed
     **/
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        log.info("Pre handle method - check handling start time");
        long startTime = System.currentTimeMillis();
        request.setAttribute("executionTime", startTime);
        session = request.getSession();
        if (session.getAttribute("username")!= null) {
            log.info("Time since last request for user {} in this session: {} ms", session.getAttribute("username").toString(), System.currentTimeMillis() - request.getSession().getLastAccessedTime());
            if (System.currentTimeMillis() - session.getLastAccessedTime() > MAX_INACTIVE_SESSION_TIME) {
                log.warn("Logging user {} out, due to inactive session", session.getAttribute("username").toString());
                request.logout();
                response.sendRedirect("/logout");
            }
        }else {
        	log.info("Current path is {}",  request.getRequestURI());
        	log.info("User not logged in yet, redirecting to login page");
        	response.sendRedirect("/");
        }
        

        return true;
    }

}
