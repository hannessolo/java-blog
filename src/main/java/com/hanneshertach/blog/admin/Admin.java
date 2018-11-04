package com.hanneshertach.blog.admin;

public class Admin {

  private int id;
  private String username;
  private String hashedAndSaltedPassword;

  public Admin(int id, String username, String hashedAndSaltedPassword) {
    this.id = id;
    this.username = username;
    this.hashedAndSaltedPassword = hashedAndSaltedPassword;
  }

  public Admin() {

  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getHashedAndSaltedPassword() {
    return hashedAndSaltedPassword;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setHashedAndSaltedPassword(String hashedAndSaltedPassword) {
    this.hashedAndSaltedPassword = hashedAndSaltedPassword;
  }
}
