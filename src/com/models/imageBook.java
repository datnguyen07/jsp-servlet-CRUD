package com.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class imageBook {
	private int id;
	private String directorybook_id;
	private String image;
	public imageBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public imageBook(String directorybook_id, String image) {
		super();
		this.id = id;
		this.directorybook_id = directorybook_id;
		this.image = image;
	}
	public imageBook(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.directorybook_id = rs.getString("directorybook_id");
		this.image = rs.getString("image");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDirectorybook_id() {
		return directorybook_id;
	}
	public void setDirectorybook_id(String directorybook_id) {
		this.directorybook_id = directorybook_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
