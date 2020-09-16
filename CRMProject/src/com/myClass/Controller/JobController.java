package com.myClass.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myClass.DAO.JobDao;
import com.myClass.Entity.Job;
@WebServlet(urlPatterns = {"/job","/job/add","/job/update","/job/delete"})
public class JobController extends HttpServlet{
	Job jobUpdate=null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getServletPath();
		
		switch(action) {
		case "/job":{
			List<Job> jobList= new ArrayList<Job>();
			jobList=JobDao.findAll();
			req.setAttribute("jobList", jobList);
			req.getRequestDispatcher("/WEB-INF/web/home/group/groupwork.jsp").forward(req, resp);
			break;
		}
		case "/job/add":{
			req.getRequestDispatcher("/WEB-INF/web/home/group/groupwork-add.jsp").forward(req, resp);
			break;
		}
		
		case "/job/update":{
			int id=Integer.parseInt(req.getParameter("id"));
			jobUpdate=JobDao.findById(id);
			
			if(jobUpdate!=null) {
				jobUpdate.setId(id);
				req.setAttribute("job", jobUpdate);
				req.getRequestDispatcher("/WEB-INF/web/home/group/groupwork-update.jsp").forward(req, resp);
			}
			
			break;
		}
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action=req.getServletPath();
		switch(action) {
		case "/job/add":{
			Job job = new Job();
			job.setName(req.getParameter("txtName"));
			job.setStartDate(Date.valueOf(req.getParameter("txtStartDate")));
			job.setEndDate(Date.valueOf(req.getParameter("txtEndDate")));
			JobDao.insert(job);
			resp.sendRedirect(req.getContextPath()+"/job");
			break;
		}
		case "/job/delete":{
			
			break;
		}
		case "/job/update":{
			if(jobUpdate!=null) {
				
				jobUpdate.setName(req.getParameter("txtName"));
				jobUpdate.setStartDate(Date.valueOf(req.getParameter("txtStartDate")));
				jobUpdate.setEndDate(Date.valueOf(req.getParameter("txtEndDate")));
				JobDao.update(jobUpdate);
				resp.sendRedirect(req.getContextPath()+"/job");
				jobUpdate=null;
			}
			break;
		}
		}	
	}
}
