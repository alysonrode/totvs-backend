package com.totvs.totvsapi.auth.infrastructure.filter;

import com.totvs.totvsapi.auth.domain.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements Filter
{
	@Autowired
	AuthService service;
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
		FilterChain filterChain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) servletRequest;

		HttpServletResponse res = (HttpServletResponse) servletResponse;
		if (req.getMethod().equals("POST") && req.getRequestURI().equals("/auth"))
		{
			filterChain.doFilter(servletRequest, servletResponse);
		}
		else
		{
			if (req.getHeader("Authentication") == null || req.getHeader("email") == null )
			{
				res.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
				return;
			}

			UUID token = UUID.fromString(req.getHeader("Authentication"));
			String email = req.getHeader("email");
			boolean result  = service.validateToken(token, email);
			if (!result)
			{
				res.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
				return;
			}

			filterChain.doFilter(servletRequest, servletResponse);
		}
	}
}
