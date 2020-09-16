package com.myClass.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.mindrot.jbcrypt.BCrypt;

import com.myClass.DAO.EmailDao;
import com.myClass.DAO.RoleDao;
import com.myClass.DAO.UserDao;
import com.myClass.DTO.LoginDTO;


@WebServlet(urlPatterns = {"/login","/logout","/login/forget","/login/checkcode","/login/setPassword"})
public class AuthController extends HttpServlet {
	public static  String EMAIL="phamlong992k@gmail.com";
	public static  String EMAIL_PASS="Goboi123";
	UserDao userDAO= new UserDao();
	RoleDao roleDAO= new RoleDao(); 
	int code;
	String emailLogin;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getServletPath();
		switch(action) {
		case "/login":{
			req.getRequestDispatcher("/WEB-INF/web/home/auth/login.jsp").forward(req, resp);
			break;
		}
		case "/logout":{
			HttpSession session= req.getSession();
			session.removeAttribute("login");
			resp.sendRedirect(req.getContextPath()+"/login");
			break;
		}
		case "/login/forget":{
			emailLogin=req.getParameter("email");
			EmailDao emailDao= new EmailDao(EMAIL, EMAIL_PASS);
			code=emailDao.EmailSend(emailLogin);
			System.out.println(code);
			req.getRequestDispatcher("/WEB-INF/web/home/auth/checkcode.jsp").forward(req, resp);
			break;
		}
		case "/login/checkcode":{
			
			int codeEnter= Integer.parseInt(req.getParameter("txtCode"));
			
			if(codeEnter==code) {
				LoginDTO user=userDAO.checkLogin(emailLogin);
				if(user==null||user.getId()<0) {
					emailLogin=null;
					req.setAttribute("messageErr","account invalid");
					req.getRequestDispatcher("/WEB-INF/web/home/auth/login.jsp").forward(req, resp);
				}
				else {
					HttpSession session=req.getSession();
				
					session.setAttribute("login",user);
					
					resp.sendRedirect(req.getContextPath()+"/login/setPassword");
				}
			}
			else {
				req.setAttribute("messageErr","invalid code");
				req.getRequestDispatcher("/WEB-INF/web/home/auth/checkcode.jsp").forward(req, resp);
				
			}
			break;
		}
		case "/login/setPassword":{
			
			req.getRequestDispatcher("/WEB-INF/web/home/auth/setpassword.jsp").forward(req, resp);
		}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=req.getServletPath();
		switch(action) {
		case "/login/setPassword":{
			
			HttpSession session=req.getSession();
			LoginDTO userDTO=(LoginDTO) session.getAttribute("login");
			String passwordBCrypt= BCrypt.hashpw(req.getParameter("txtPass"), BCrypt.gensalt(12));
			userDTO.setPassword(passwordBCrypt);
			userDAO.updatePassword(userDTO);
			session.removeAttribute("login");
			resp.sendRedirect(req.getContextPath()+"/login");
			break;
		}
		case "/login":{
			String email=req.getParameter("txtEmail");
			String pass=req.getParameter("txtPassword");
			LoginDTO user=userDAO.checkLogin(email);
			System.out.println(user);
			
			if(user==null||user.getId()<0) {
				req.setAttribute("messageErr","account invalid");
				req.getRequestDispatcher("/WEB-INF/web/home/auth/login.jsp").forward(req, resp);
			}
			else {
				// create session 
				// set value in session
				if(BCrypt.checkpw(pass,user.getPassword())) {
					HttpSession session=req.getSession();
					session.setAttribute("login",user);
					resp.sendRedirect(req.getContextPath()+"/home");
					session.setAttribute("roleName",user.getRoleName());
				}
				else {
					req.setAttribute("messageErr","account invalid");
					req.getRequestDispatcher("/WEB-INF/web/home/auth/login.jsp").forward(req, resp);
				}
				
			}
			break;
		}
		}
	
	}
}
