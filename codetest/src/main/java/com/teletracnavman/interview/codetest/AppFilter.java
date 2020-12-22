package com.teletracnavman.interview.codetest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AppFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(AppFilter.class);
	
	@Value("${authentication_token}")
	private String authentication_token;
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		//Simple token based authorization, return 401 status for failed requests.
		if (!authentication_token.equals(httpRequest.getHeader("Authorization")))
		{
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		//Audit the request for IP and request path
		String remoteAddr = httpRequest.getHeader("X-FORWARDED-FOR");
		if (remoteAddr == null || "".equals(remoteAddr ))
				remoteAddr = httpRequest.getRemoteAddr();
		logger.info("Audit Request: " + remoteAddr + "--" + httpRequest.getRequestURI());

		//if URL ends in '/nocontent', forward to '/nocontent'
		if (httpRequest.getRequestURI()!= null && httpRequest.getRequestURI().endsWith("/nocontent")) 
		{
			httpRequest.getRequestDispatcher("/nocontent").forward(httpRequest, httpResponse);
		}
		//if URL is '/echo' or '/device', pass through
		else if ( httpRequest.getRequestURI()!= null && httpRequest.getRequestURI().endsWith("/nocontent") || "/echo".equals(httpRequest.getRequestURI())||"/device".equals(httpRequest.getRequestURI()))
		{
			chain.doFilter(httpRequest, httpResponse);
		}
		//else return HTTP 400
		else 
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		//Audit the response status
		logger.info("Audit Response: " + httpResponse.getStatus());
	}

}
