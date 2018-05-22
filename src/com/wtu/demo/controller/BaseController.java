package com.wtu.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wtu.demo.common.AppContext;
import com.wtu.demo.constants.Constants;
import com.wtu.demo.util.PathUtil;
import com.wtu.demo.util.SessionUtil;

public class BaseController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
    	e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView(this.getRedirectView(Constants.INNER_ERROR_PAGE));

        return modelAndView;
    }

    protected RedirectView getRedirectView(String path) {
        return new RedirectView(PathUtil.getFullPath(path));
    }

    protected void addSession(String key, Object value) {
        SessionUtil.addSession(key, value);
    }

    protected Object getSession(String key) {
        return SessionUtil.getSession(key);
    }

    protected void removeSession(String key) {
        SessionUtil.removeSession(key);
    }

    protected HttpServletResponse getResponse() {
        return (HttpServletResponse) AppContext.getContext().getObject("response");
    }
}