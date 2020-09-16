package com.myClass.DTO;

import com.myClass.Entity.User;

public class UserDTO extends User{
	private String roleName;
	

	public UserDTO() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	public UserDTO(int id, String email, String password, String fullname, String avatar, int roleId,String roleName) {
		super(id, email, password, fullname, avatar, roleId);
		this.getFullname();
		this.roleName=roleName;
	}

	public UserDTO(String email, String password, String fullname, String avatar, int roleId,String roleName) {
		super(email, password, fullname, avatar, roleId);
		this.roleName=roleName;
		// TODO Auto-generated constructor stub
	}

	public String getRoleName() {
		
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	
}
