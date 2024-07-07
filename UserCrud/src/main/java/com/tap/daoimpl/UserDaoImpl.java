package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.UserDao;
import com.tap.model.User;

public class UserDaoImpl implements UserDao {
	static final String INSERT_QUERY = "INSERT INTO `user`(`name`,`email`,`country`) values(?,?,?,?)";
	static final String SELECT_QUERY = "SELECT * FROM `user` Where `id`=?";
	static final String SELECT_ALL_QUERY = "SELECT * FROM `user`";
	static final String UPDATE_QUERY = "UDATE `user` SET `name`=? ,`email`=? ,`country`=? WHERE `id`=? ";
	static final String DELETE_QUERY = "DELETE FROM `user` WHERE `userId`=?";
	private Connection con;

	public UserDaoImpl() {
		String url = "jdbc:mysql://localhost:3306/jdbc_db";
		String username = "root";
		String password = "123456";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(INSERT_QUERY);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int id) {
		PreparedStatement statement = null;
		ResultSet set = null;
		User u1 = null;
		try {
			statement = con.prepareStatement(SELECT_QUERY);
			statement.setInt(1, id);
			set = statement.executeQuery();

			if (set.next()) {
				String name = set.getString(1);
				String email = set.getString(2);
				String country = set.getString(3);
				u1 = new User(id,name, email, country);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return u1;
	}

	@Override
	public List<User> getAllUser() {
		ArrayList<User> list = new ArrayList<User>();
		Statement statement = null;
		try {
			statement = con.createStatement();
			ResultSet set = statement.executeQuery(SELECT_ALL_QUERY);
			while (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				String email = set.getString(3);
				String counry = set.getString(4);
				User u2 = new User(id, name, email, counry);
				list.add(u2);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void updateUser(User user) {
		PreparedStatement statement=null;
       try {
		statement = con.prepareStatement(UPDATE_QUERY);
		statement.setString(1, user.getName());
		statement.setString(2, user.getEmail());
		statement.setString(3, user.getCountry());
		statement.setInt(4, user.getId());
		statement.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

	@Override
	public void deleteUser(int id) {
      PreparedStatement statement=null;
       try {
		statement = con.prepareStatement(DELETE_QUERY);
		statement.setInt(1, id);
		statement.executeUpdate();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}

}
