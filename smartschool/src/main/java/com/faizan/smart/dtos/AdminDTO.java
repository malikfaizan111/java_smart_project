package com.faizan.smart.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;

public class AdminDTO {
	
//    @JsonIgnore
    private Long adminId;
    @Nonnull
    private String name;
    private Boolean isActive = true;
    private String phoneNumber;
    private String address;
   
    private UserDTO user;
    
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "AdminDTO [adminId=" + adminId + ", name=" + name + ", isActive=" + isActive + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", user=" + user + "]";
	}
	
	
	
}
