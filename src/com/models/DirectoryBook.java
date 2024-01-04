package com.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectoryBook {
	private int id;
	private String address;
	private String phone;
	private String email;
	public DirectoryBook() {
		super();
	}
	public DirectoryBook(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.address = rs.getString("address");
		this.phone = rs.getString("phone");
		this.email = rs.getString("email");
	}
	public DirectoryBook(String address, String phone, String email) {
		super();
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
