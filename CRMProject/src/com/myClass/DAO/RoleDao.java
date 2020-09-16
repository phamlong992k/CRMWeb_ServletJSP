package com.myClass.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myClass.ConnectDatabase.MyConnect;
import com.myClass.Entity.Role;



public class RoleDao {

	private List<Role> list = null;

	public RoleDao() {
		list = new ArrayList<Role>();
	}

	// Phương thức lấy danh sách
	public List<Role> findAll() {
		
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				String sql="Select * from roles";
				ps=cn.prepareStatement(sql);
				rs=ps.executeQuery();
				list.clear();
				while(rs.next()) {
					Role role= new Role();
					role.setId(rs.getInt("id"));
					role.setName(rs.getString("name"));
					role.setDescription(rs.getString("description"));
					list.add(role);
				}
			}
		}catch(Exception e) {
			
		}finally {
			
		}
		return list;
	}

	// Phương thức lấy ra đối tượng role theo id
	public Role findById(int id) {
		
		Role result = new Role();
		
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				String sql="select * from roles where id = "+id;
				ps=cn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					result.setId(rs.getInt("id"));
					result.setName(rs.getString("name"));
					result.setDescription(rs.getString("description"));
				}
				return result;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Phương thức thêm mới
	public boolean add(Role role) {
		Connection cn=null;
		PreparedStatement ps=null;
	
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				String sql="insert into roles(name,description) values(?,?)";
				ps=cn.prepareStatement(sql);
				ps.setObject(1,role.getName());
				ps.setObject(2,role.getDescription());
				int rs=ps.executeUpdate();
				if(rs<1) {
					return false;
				}
				if(rs>=1) {
					return true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Phương thức cập nhật
	public void update(Role role) {
		
		Connection cn=null;
		PreparedStatement ps=null;
		
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				String sql="Update roles set name = ?, description = ? where id = ?";
				cn.setAutoCommit(false);
				ps=cn.prepareStatement(sql);
				ps.setObject(1,role.getName());
				ps.setObject(2,role.getDescription());
				ps.setObject(3, role.getId());
				ps.executeUpdate();
				cn.commit();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(cn!=null) {
					cn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Phương thức xóa đối tượng role theo id
	public boolean deleteById(int id) {
		
		Connection cn=null;
		PreparedStatement ps=null;
	
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				String sql="delete from roles where id = ?";
				cn.setAutoCommit(false);
				ps=cn.prepareStatement(sql);
				ps.setObject(1, id);
				int rs=ps.executeUpdate();
				cn.commit();
				if(rs>=1) {
					return true;
				}
				if(rs<1) {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
