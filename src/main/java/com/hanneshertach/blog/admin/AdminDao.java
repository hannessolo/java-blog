package com.hanneshertach.blog.admin;

import com.hanneshertach.blog.database.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDao implements Dao<Admin> {

  private Connection conn;

  public AdminDao() {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Error fetching database driver.");
    }

    try {
      conn = DriverManager.getConnection("jdbc:mysql://db:3306/blog", "root", "");
    } catch (Exception e) {
      throw new RuntimeException("Error connecting to database.");
    }

  }

  @Override
  public void removeItem(Admin item) {

  }

  @Override
  public Admin addItem(Admin item) {
    return null;
  }

  @Override
  public List<Admin> getItems() {
    return null;
  }

  @Override
  public Admin getItem(int id) {
    ResultSet rs = null;

    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE id=?;");
      ps.setObject(1, id);
      rs = ps.executeQuery();

      if (rs.next()) {
        return new Admin(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password")
        );

      }

    } catch (SQLException e) {
      throw new RuntimeException("Error trying to execute statement on database.");
    } finally {
      try {
        rs.close();
      } catch (SQLException | NullPointerException e) {

      }

    }

    return null;
  }

  @Override
  public Admin editItem(int id, Admin item) {

    try {
      PreparedStatement ps = conn.prepareStatement("UPDATE admin SET password=? WHERE username=?");
      ps.setString(1, item.getHashedAndSaltedPassword());
      ps.setString(2, item.getUsername());

      ps.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException("Error trying to execute statement on database");
    }

    return item;
  }

  public Admin getAdminByUsernameAndPassword(String username, String password) {
    ResultSet rs = null;

    try {
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
      ps.setString(1, username);
      ps.setString(2, password);

      rs = ps.executeQuery();

      if (rs.next()) {
        return new Admin(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password")
        );
      }

    } catch (SQLException e) {
      throw new RuntimeException("Error trying to execute statement on database");
    } finally {
      try {
        rs.close();
      } catch (SQLException e) {
      }
    }

    return null;
  }
}
