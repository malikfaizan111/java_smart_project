package com.faizan.smart.dtos;

public class UserDTO {
	
//    @JsonIgnore
    private Long userId;
    private String username;
    private String password;
    private String email;
//    private AdminDTO admin;
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
//	public AdminDTO getAdmin() {
//		return admin;
//	}
//	public void setAdmin(AdminDTO admin) {
//		this.admin = admin;
//	}
//	@Override
//	public String toString() {
//		return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
//				+ ", admin=" + admin + "]";
//	}

	
}