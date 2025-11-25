
package com.synechron.group1.onlineretail.dto;


public class UpdateProfileRequest {

	private String fullname;
	private String phoneNumber;
	private String address;

	public UpdateProfileRequest(String fullname, String phoneNumber, String address) {
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullName) {
		this.fullname = fullName;
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

}

//newlyu added file
