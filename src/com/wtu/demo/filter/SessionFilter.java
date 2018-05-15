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

import com.wtu.demo.common.AppContext;
import com.wtu.demo.constants.SymbolConstants;
import com.wtu.demo.model.User;
import com.wtu.demo.util.PathUtil;

public class SessionFilter implements Filter {

    private static final String NOT_NEED_LOGIN = "notNeedLoginPages";
    private static final String LOGIN_JSP = "user/login";

    private String notNeedLoginPages;
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

        if (this.filterConfig.getInitParameter(NOT_NEED_LOGIN) != null) {
            notNeedLoginPages = this.filterConfig.getInitParameter(NOT_NEED_LOGIN);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String requestUri = uri.substring(request.getContextPath().length() + 1);

        String[] pages = notNeedLoginPages.split(SymbolConstants.COMMA);
        boolean isAllow = false;

        for (String page : pages) {
            if (page.equals(requestUri)) {
                isAllow = true;
                break;
            }
        }

        if (isAllow) {
            filterChain.doFilter(request, response);
        } else {
            User user = AppContext.getContext().getUser();

            if (user == null) {
                response.sendRedirect(PathUtil.getFullPath(LOGIN_JSP));
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}