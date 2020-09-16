package com.myClass.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;

import org.mindrot.jbcrypt.BCrypt;

import com.myClass.DAO.RoleDao;
import com.myClass.DAO.UserDao;
import com.myClass.DTO.LoginDTO;
import com.myClass.Entity.Role;
import com.myClass.Entity.User;


@WebServlet(urlPatterns = {"/user","/user/add","/user/update","/user/delete","/user/view"})
@MultipartConfig(
fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50, // 50MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UserController extends HttpServlet{
	UserDao userDAO= new UserDao();
	RoleDao roleDAO= new RoleDao();
	int idUpdate=-1;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String choice= req.getServletPath();
		
		switch(choice) {
			case "/user":{
				List<User> listUser= userDAO.findAll();
				req.setAttribute("listUser",listUser);
				req.getRequestDispatcher("/WEB-INF/web/home/user/user-table.jsp").forward(req, resp);
				break;
			}
			case "/user/add":{
				List<Role> listRole=roleDAO.findAll(); 
				req.setAttribute("listRole",listRole);
				req.getRequestDispatcher("/WEB-INF/web/home/user/user-add.jsp").forward(req, resp);
				break;
			}
			case "/user/update":{
				idUpdate=Integer.parseInt(req.getParameter("id"));
				User user=userDAO.findById(idUpdate);
				req.setAttribute("user", user);
				req.setAttribute("roles",roleDAO.findAll());
				req.getRequestDispatcher("/WEB-INF/web/home/user/user-edit.jsp").forward(req, resp);
				break;
			}
			case "/user/delete":{
				int id=Integer.parseInt(req.getParameter("id"));
				userDAO.deleteById(id);
				resp.sendRedirect(req.getContextPath()+"/user");
				break;
			}
			case "/user/view":{
				User user= userDAO.findById(Integer.parseInt(req.getParameter("id")));
				req.setAttribute("user", user);
				req.getRequestDispatcher("/WEB-INF/web/home/user/user-details.jsp").forward(req, resp);
				//resp.sendRedirect(req.getContextPath()+"/user/user-details");
				break;
			}

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String choice= req.getServletPath();
		
		switch(choice) {
			case "/user":{
				break;
			}
			case "/user/add":{
				User user= new User();
				String passwordBCrypt= BCrypt.hashpw(req.getParameter("txtPassword"), BCrypt.gensalt(12));
				user.setFullname(req.getParameter("txtName"));
				user.setAvatar(req.getParameter("txtAvatar"));
				user.setEmail(req.getParameter("txtEmail"));
				
				user.setPassword(passwordBCrypt);
				user.setRoleId(Integer.parseInt(req.getParameter("roleID")));
				System.out.println(user);
				userDAO.add(user);
				resp.sendRedirect(req.getContextPath()+"/user");
				break;
			}
			case "/user/update":{
				String avatar=saveFile(req);
				User user= new User();
				user.setFullname(req.getParameter("txtName"));
				user.setId(idUpdate);
				user.setAvatar(avatar);
				user.setPassword(req.getParameter("txtPassword"));
				user.setRoleId(Integer.parseInt(req.getParameter("roleID")));
				
				user.setEmail(req.getParameter("txtEmail"));
				
				System.out.println(user);
				userDAO.update(user);
				HttpSession session=req.getSession();
				session.setAttribute("login",user);
				LoginDTO userDTO= userDAO.checkLogin(user.getEmail());
				session.setAttribute("roleName",userDTO.getRoleName());
				idUpdate=-1;
				resp.sendRedirect(req.getContextPath()+"/user");
				break;
			}
			case "/user/delete":{
				break;
			}
			
		}
	}
	private String saveFile(HttpServletRequest req) {
		String avatar="";
		try {
			
			for(Part part:req.getParts()) {
				String contentDisp=part.getHeader("content-disposition");
				String [] arrFormData=contentDisp.split(";");
				
				String fileName="";
				for(String item:arrFormData) {
					System.out.println(item);
					
					if(item.trim().contains("filename")) {
						
						fileName=item.substring(item.indexOf("=")+2,item.length()-1);
						System.out.println("filename: "+fileName);
					}
				}
				if(!fileName.isEmpty()) {
					String appPath=req.getServletContext().getRealPath("");
					System.out.println(appPath);
					
					String savePath="F:\\CyberSoft\\Servlet\\CRMProject\\WebContent\\static\\upload";
					System.out.println(savePath);
					File fileSaveDir= new File(savePath);
					if(!fileSaveDir.exists()) {
						System.out.println("create file");
						fileSaveDir.mkdir();
					}
					System.out.println(savePath+File.separator+fileName);
					part.write(savePath+File.separator+fileName);
					avatar="/static/upload/"+fileName;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return avatar;
	}
	
		
}
