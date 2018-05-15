package com.wtu.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wtu.demo.common.AppContext;
import com.wtu.demo.constants.Constants;


public class AppContextFilter implements Filter {

    private static Logger logger = Logger.getLogger(AppContextFilter.class);

    public AppContextFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding(Constants.ENCODE);
        HttpSession session = request.getSession();
        AppContext.setContextPath(request.getContextPath());

        AppContext appContext = AppContext.getContext();
        appContext.addObject(Constants.APP_CONTEXT_REQUEST, request);
        appContext.addObject(Constants.APP_CONTEXT_RESPONSE, response);
        appContext.addObject(Constants.APP_CONTEXT_SESSION, session);

        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            appContext.clear();
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}