package com.myClass.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myClass.ConnectDatabase.MyConnect;
import com.myClass.DTO.LoginDTO;
import com.myClass.DTO.UserDTO;
import com.myClass.Entity.User;


public class UserDao {
	private List<User> list = null;

	public UserDao() {
		list = new ArrayList<User>();
	}

	// Phương thức lấy danh sách
	public List<User> findAll() {
		String sql= "select u.id, u.email,u.password,u.fullname,u.avatar,u.role_id,r.name as role_name " + 
					"from users u, roles r " + 
					"where u.role_id = r.id ";
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				ps=cn.prepareStatement(sql);
				rs=ps.executeQuery();
				list.clear();
				while(rs.next()) {
					UserDTO user= new  UserDTO(rs.getInt("id"),rs.getString("email"),rs.getString("password"),rs.getString("fullname"),rs.getString("avatar"),rs.getInt("role_id"),rs.getString("role_name"));
					list.add(user);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Phương thức lấy ra đối tượng role theo id
	public User findById(int id) {
		for (User user : list) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	// Phương thức thêm mới
	public boolean add(User user) {
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			String sql="insert into users (email,password,fullname,avatar,role_id) values(?,?,?,?,?)";
			
			cn=MyConnect.getConnect();
			
			if(cn!=null) {
				cn.setAutoCommit(false);
				ps=cn.prepareStatement(sql);
				ps.setObject(1,user.getEmail());
				ps.setObject(2,user.getPassword());
				ps.setObject(3,user.getFullname());
				ps.setObject(4,user.getAvatar());
				ps.setObject(5,user.getRoleId());
				
				int rs=ps.executeUpdate();
				cn.commit();
				if(rs>=1) {
					return true;
				}
				else {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Phương thức cập nhật
	public boolean update(User user) {
		
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			String sql="Update users set email = ?, fullname = ?, avatar = ?, role_id = ? where id = ?";
			
			cn=MyConnect.getConnect();
			
			if(cn!=null) {
				cn.setAutoCommit(false);
				ps=cn.prepareStatement(sql);
				ps.setObject(1,user.getEmail());
				
				ps.setObject(2,user.getFullname());
				ps.setObject(3,user.getAvatar());
				ps.setObject(4,user.getRoleId());
				ps.setObject(5,user.getId());
				
				int rs=ps.executeUpdate();
				cn.commit();
				if(rs>=1) {
					return true;
				}
				else {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
public boolean updatePassword(LoginDTO loginDTO) {
		
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			String sql="Update users set password = ? where id = ?";
			
			cn=MyConnect.getConnect();
			
			if(cn!=null) {
				cn.setAutoCommit(false);
				ps=cn.prepareStatement(sql);
				ps.setObject(1,loginDTO.getPassword());
				ps.setObject(2,loginDTO.getId());
				int rs=ps.executeUpdate();
				cn.commit();
				if(rs>=1) {
					return true;
				}
				else {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	// Phương thức xóa đối tượng role theo id
	public boolean deleteById(int id) {
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			String sql="Delete from users where id = ?";
			
			cn=MyConnect.getConnect();
			
			if(cn!=null) {
				cn.setAutoCommit(false);
				ps=cn.prepareStatement(sql);
				ps.setObject(1,id);
				int rs=ps.executeUpdate();
				cn.commit();
				if(rs>=1) {
					return true;
				}
				else {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public User findByEmail(String email) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs= null;
		try {
			String sql="select * from users where email = ?";
			cn=MyConnect.getConnect();
			
			if(cn!=null) {
				
				ps=cn.prepareStatement(sql);
				ps.setObject(1,email);;
				rs=ps.executeQuery();
				while(rs.next()) {
					User user= new User();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setFullname(rs.getString("fullname"));
					user.setPassword(rs.getString("password"));
					user.setRoleId(rs.getInt("role_id"));
					user.setAvatar(rs.getString("avatar"));
					return user;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public LoginDTO checkLogin(String email) {
		LoginDTO loginDTO= new  LoginDTO();
		String sql="select u.id,u.email,u.password,u.fullname,u.avatar,r.name as roleName"
				+ " from users u, roles r"
				+ " where u.role_id=r.id and email = ? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection cn=null;
		try {
			cn=MyConnect.getConnect();
			if(cn!=null) {
				int flag=0;
				ps=cn.prepareStatement(sql);
				ps.setObject(1,email);
				rs=ps.executeQuery();
				while(rs.next()) {
					flag=1;
					loginDTO.setId(Integer.parseInt(rs.getString("id")));
					loginDTO.setEmail(rs.getString("email"));
					loginDTO.setFullname(rs.getString("fullname"));
					if(rs.getString("avatar")!=null) {
						loginDTO.setAvatar(rs.getString("avatar"));
					}
					loginDTO.setPassword(rs.getString("password"));
					loginDTO.setRoleName(rs.getString("roleName"));
					
				}
				if(flag==1) {
					return loginDTO;
				}
				
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
		return null;
	}
}
