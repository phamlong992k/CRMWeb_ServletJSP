package com.myClass.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myClass.ConnectDatabase.MyConnect;
import com.myClass.Entity.Job;

public class JobDao {
	public static List<Job> findAll() {
		String sql="select * from jobs";
		List<Job> result=new ArrayList<Job>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection cn=null;
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				ps=cn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					Job job= new Job();
					job.setId(rs.getInt("id"));
					job.setName(rs.getString("name"));
					job.setStartDate(rs.getDate("start_date"));
					job.setEndDate(rs.getDate("end_date"));
					result.add(job);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static Job findById(int id) {
		String sql="select name,start_date,end_date from jobs where id = ?";
		PreparedStatement ps=null;
		Connection cn=null;
		ResultSet rs=null;
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				ps=cn.prepareStatement(sql);
				ps.setObject(1,id);
				rs=ps.executeQuery();
				
				while(rs.next()) {
					Job job= new Job();
					job.setName(rs.getString("name"));
					job.setStartDate(rs.getDate("start_date"));
					job.setEndDate(rs.getDate("end_date"));
					return job;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void insert(Job job) {
		String sql="insert into jobs(name,start_date,end_date) values(?,?,?)";
		PreparedStatement ps=null;
		Connection cn= null;
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				ps=cn.prepareStatement(sql);
				ps.setObject(1, job.getName());
				ps.setObject(2, job.getStartDate());
				ps.setObject(3, job.getEndDate());
				ps.executeUpdate();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void update(Job job) {
		String sql="update jobs set name = ?, start_date = ?, end_date =? where id= ?";
		PreparedStatement ps=null;
		Connection cn=null;
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				ps=cn.prepareStatement(sql);
				ps.setObject(1, job.getName());
				ps.setObject(2,job.getStartDate());
				ps.setObject(3, job.getEndDate());
				ps.setObject(4, job.getId());
				ps.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
