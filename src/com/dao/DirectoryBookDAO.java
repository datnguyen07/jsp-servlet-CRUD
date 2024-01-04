package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.models.DirectoryBook;
import com.util.SqlConnection;

public class DirectoryBookDAO {
	public List<DirectoryBook> getAll() {
		List<DirectoryBook> data = new ArrayList<>();
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("select * from address_book.directorybook");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				DirectoryBook d = new DirectoryBook(rs);
				data.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return data;
	}

	public DirectoryBook findId(String id) {
		List<DirectoryBook> data = new ArrayList<>();
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("select * from address_book.directorybook WHERE id = ?");
			cs.setString(1, id);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				DirectoryBook d = new DirectoryBook(rs);
				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return null;
	}

	public DirectoryBook save(DirectoryBook d) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("INSERT INTO address_book.directorybook (`address`, `phone`, `email`) VALUES(?,?,?)");
			cs.setString(1, d.getAddress());
			cs.setString(2, d.getPhone());
			cs.setString(3, d.getEmail());

			cs.executeUpdate();
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return null;
	}

	public DirectoryBook update(DirectoryBook d, String id) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall(
					"UPDATE address_book.directorybook SET address = ?, phone = ?, email = ? WHERE (`id` = ?)");
			cs.setString(1, d.getAddress());
			cs.setString(2, d.getPhone());
			cs.setString(3, d.getEmail());
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
			cs = conn.prepareCall("DELETE FROM address_book.directorybook WHERE id = ?");
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

	public int count(String txtSearch) {
		try {
			Connection conn = null;
			CallableStatement cs = null;
			conn = SqlConnection.open();
			cs = conn.prepareCall("SELECT count(*) from address_book.directorybook where address like ?");
			cs.setString(1,"%" + txtSearch + "%");

			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return 0;

	}
	
	public int countAll() {
		try {
			Connection conn = null;
			CallableStatement cs = null;
			conn = SqlConnection.open();
			cs = conn.prepareCall("SELECT count(*) from address_book.directorybook");

			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return 0;

	}
	
	public List<DirectoryBook> search(String txtSearch, int index, int size) {
		List<DirectoryBook> data = new ArrayList<>();
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("WITH x AS (\r\n" + 
					"    SELECT\r\n" + 
					"        id,\r\n" + 
					"        ROW_NUMBER() OVER (ORDER BY id ASC) AS id_rank,\r\n" + 
					"        address,\r\n" + 
					"        phone,\r\n" + 
					"        email\r\n" + 
					"    FROM\r\n" + 
					"        address_book.directorybook\r\n" + 
					"    WHERE\r\n" + 
					"        address LIKE ?\r\n" + 
					")\r\n" + 
					"SELECT\r\n" + 
					"    id,\r\n" + 
					"    id_rank,\r\n" + 
					"    address,\r\n" + 
					"    phone,\r\n" + 
					"    email\r\n" + 
					"FROM\r\n" + 
					"    x\r\n" + 
					"WHERE\r\n" + 
					"    id_rank BETWEEN ? * ? - 4 AND ? * 5;");
			cs.setString(1, "%" + txtSearch + "%");
			cs.setInt(2, index);
			cs.setInt(3, size);
			cs.setInt(4, index);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				DirectoryBook d = new DirectoryBook(rs);
				data.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return data;
	}
	
	public List<Integer> searchIdRank(String txtSearch, int index, int size) {
		List<Integer> id_rank  = new ArrayList<>();
		Connection conn = null;
		CallableStatement cs = null;
		try {
			conn = SqlConnection.open();
			cs = conn.prepareCall("WITH x AS (\r\n" + 
					"    SELECT\r\n" + 
					"        id,\r\n" + 
					"        ROW_NUMBER() OVER (ORDER BY id ASC) AS id_rank,\r\n" + 
					"        address,\r\n" + 
					"        phone,\r\n" + 
					"        email\r\n" + 
					"    FROM\r\n" + 
					"        address_book.directorybook\r\n" + 
					"    WHERE\r\n" + 
					"        address LIKE ?\r\n" + 
					")\r\n" + 
					"SELECT\r\n" + 
					"    id,\r\n" + 
					"    id_rank,\r\n" + 
					"    address,\r\n" + 
					"    phone,\r\n" + 
					"    email\r\n" + 
					"FROM\r\n" + 
					"    x\r\n" + 
					"WHERE\r\n" + 
					"    id_rank BETWEEN ? * ? - 4 AND ? * 5;");
			cs.setString(1, "%" + txtSearch + "%");
			cs.setInt(2, index);
			cs.setInt(3, size);
			cs.setInt(4, index);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				id_rank.add(rs.getInt("id_rank"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlConnection.close(conn);
		}
		return id_rank;
	}
}