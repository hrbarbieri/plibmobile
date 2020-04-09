package com.brightstar.plibmobi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class EyeHttpServlet implements Filter {
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        
		/*
		 * String timeout = (String) req.getServletContext().getAttribute("TIMEOUT");
		 * if(timeout!= null && timeout.equals("1")) {
		 * res.sendRedirect(req.getServletContext().getContextPath() +
		 * "/login?expired=true" ); }
		 */
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}
}
