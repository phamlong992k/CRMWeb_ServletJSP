package com.myClass.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
	static int n=0;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req= ((HttpServletRequest) request);
		HttpServletResponse resp=((HttpServletResponse) response);
		HttpSession session= req.getSession();
		String action=req.getServletPath();
		
		if(action.startsWith("/login")||action.startsWith("/logout")) {
			chain.doFilter(req, resp);
			return;
		}
		if(session.getAttribute("login")==null) {
			resp.sendRedirect(req.getContextPath()+"/login");
			return;
		}
		
		if(session.getAttribute("roleName")!=null) {
			String roleName=(String) session.getAttribute("roleName");
			System.out.println(roleName);
			
			if(action.startsWith("/role")&&!roleName.equals("user")) {
				resp.sendRedirect(req.getContextPath()+"/error/403");
				return;
			}
			
			if(action.startsWith("/user")&&!roleName.equals("user")) {
				resp.sendRedirect(req.getContextPath()+"/error/403");
				return;
			}
			chain.doFilter(req, resp);
			return;
		}
		if(session.getAttribute("roleName")==null) {
			resp.sendRedirect(req.getContextPath()+"/login");
			return;
		}
		
		
	}

}
