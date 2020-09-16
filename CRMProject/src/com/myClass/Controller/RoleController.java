package com.myClass.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myClass.DAO.RoleDao;
import com.myClass.Entity.Role;

@WebServlet(name="RoleServlet", urlPatterns = {"/role","/role/update","/role/delete","/role/add"})
public class RoleController extends HttpServlet {
	private RoleDao roleDao= new RoleDao();
	List<Role> listRoles= new ArrayList<Role>();
	int idUpdate=-1;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action=req.getServletPath();
		switch (action) {
			case "/role":{
				listRoles=roleDao.findAll();
				this.getServletContext().setAttribute("listRoles",listRoles);
				req.getRequestDispatcher("/WEB-INF/web/home/role/role-table.jsp").forward(req, resp);
				break;
			}
			
			case "/role/update":{
				idUpdate=Integer.parseInt(req.getParameter("id"));
				Role role = roleDao.findById(idUpdate);
				req.setAttribute("roleUpdate", role);
				req.getRequestDispatcher("/WEB-INF/web/home/role/edit.jsp").forward(req, resp);
				break;
			}
			
			
			case "/role/delete":{
				int id=Integer.parseInt(req.getParameter("id"));
				Role role=roleDao.findById(id);
				roleDao.deleteById(id);
				listRoles=roleDao.findAll();
				this.getServletContext().setAttribute("listRoles",listRoles);
				resp.sendRedirect(req.getContextPath()+"/role");
				break;
			}
			case "/role/add":{
				req.getRequestDispatcher("/WEB-INF/web/home/role/add.jsp").forward(req, resp);
				break;
			}
		
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getServletPath();
		
		switch(action) {
			case "/role/update":{
				Role role= new Role(idUpdate,req.getParameter("txtName"),req.getParameter("txtDesc"));
				roleDao.update(role);
				resp.sendRedirect(req.getContextPath()+"/role");
				idUpdate=-1;
				break;
			}
			case "/role/add":{
				Role role= new Role(req.getParameter("txtName"),req.getParameter("txtDesc"));
				roleDao.add(role);
				resp.sendRedirect(req.getContextPath()+"/role");
				break;
			}
			
		}
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	
}
