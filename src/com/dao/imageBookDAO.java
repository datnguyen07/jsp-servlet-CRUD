package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.models.DirectoryBook;
import com.models.imageBook;
import com.util.SqlConnection;

public class imageBookDAO {
	public List<imageBook> getAll() {
		List<imageBook> data = new ArrayList<>();
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("select * from address_book.image_book");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				imageBook i = new imageBook(rs);
				data.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return data;
	}
	
	public List<imageBook> findByDirectoryBookId(String id) {
		List<imageBook> data = new ArrayList<>();
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("select * from address_book.image_book WHERE directoryBook_id = ?");
			cs.setString(1, id);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				imageBook i = new imageBook(rs);
				data.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return data;
	}
	
	public imageBook findId(String id) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("select * from address_book.image_book WHERE id = ?");
			cs.setString(1, id);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				imageBook d = new imageBook(rs);
				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return null;
	}
	
	public imageBook save(imageBook d) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("INSERT INTO address_book.image_book (`directorybook_id`, `image`) VALUES(?,?)");
			cs.setString(1, d.getDirectorybook_id());
			cs.setString(2, d.getImage());

			cs.executeUpdate();
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return null;
	}

	public imageBook update(imageBook d, String id) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall(
					"UPDATE address_book.image_book SET directorybook_id = ?, image = ? WHERE (`id` = ?)");
			cs.setString(1, d.getDirectorybook_id());
			cs.setString(2, d.getImage());
			cs.setString(4, id);

			cs.executeUpdate();
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return null;
	}

	public boolean remove(String id) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("DELETE FROM address_book.image_book WHERE id = ?");
			cs.setString(1, id);

			cs.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return false;
	}

}
