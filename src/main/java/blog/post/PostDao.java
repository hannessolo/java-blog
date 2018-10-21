package blog.post;

import blog.database.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements Dao<Post> {

  private Connection conn = null;

  public PostDao() {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Error fetching database driver.");
    }

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/blog?user=root");
    } catch (Exception e) {
      throw new RuntimeException("Error connecting to database.");
    }

  }

  @Override
  public void removeItem(Post post) {

    try {

      PreparedStatement ps = conn.prepareStatement(
          "DELETE FROM post WHERE id=? AND title=?;"
      );

      ps.setObject(1, post.getId());
      ps.setObject(2, post.getTitle());

      ps.execute();

    } catch (SQLException e) {
      throw new RuntimeException("Error creating statement.");
    }

  }

  @Override
  public Post addItem(Post post) {
    return addPost(post.getTitle(), post.getContent());
  }

  @Override
  public List<Post> getItems() {

    List<Post> posts = new ArrayList<>();

    ResultSet rs = null;

    try (Statement stmt = conn.createStatement()) {

      rs = stmt.executeQuery("SELECT * FROM post;");

      while (rs.next()) {
        Post post = new Post(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("body"),
            rs.getTimestamp("dateAndTime").toLocalDateTime(),
            this
        );

        posts.add(post);

      }

    } catch (SQLException e) {
      throw new RuntimeException("Error trying to execute statement on database.");
    } finally {
      try {
        rs.close();
      } catch (SQLException | NullPointerException e) {

      }

    }

    return posts;
  }

  @Override
  public Post getItem(int id) {

    ResultSet rs = null;

    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM post WHERE id=?;");
      ps.setObject(1, id);
      rs = ps.executeQuery();

      if (rs.next()) {
        return new Post(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("body"),
            rs.getTimestamp("dateAndTime").toLocalDateTime(),
            this
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
  public Post editItem(int id, Post item) {

    PreparedStatement ps = null;

    try {
      ps = conn.prepareStatement("UPDATE post SET title=?, body=? WHERE id=?");

      ps.setString(1, item.getTitle());
      ps.setString(2, item.getContent());
      ps.setInt(3, id);

      ps.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException("Error creating SQL statement");
    } finally {
      try {
        ps.close();
      } catch (SQLException | NullPointerException e) {

      }
    }
    return item;
  }

  public Post addPost(String title, String contents) {

    try {

      PreparedStatement ps = conn.prepareStatement(
          "INSERT INTO post (title, body) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS
      );

      ps.setObject(1, title);
      ps.setObject(2, contents);

      ps.executeUpdate();

      ResultSet rs = ps.getGeneratedKeys();
      rs.next();
      int post = rs.getInt(1);

      return getItem(post);


    } catch (SQLException e) {
      throw new RuntimeException("Error trying to create statement.");
    }

  }

  public List<Post> getPostsTruncated(int len) {

    List<Post> truncatedPosts = new ArrayList<>();
    List<Post> posts = getItems();

    for (Post post : posts) {
      truncatedPosts.add(new Post(
          post.getId(),
          post.getTitle(),
          post.getContent().substring(0, Math.min(post.getContent().length(), len)) + "...",
          post.getDateObject(),
          post.getDao()
      ));
    }

    return truncatedPosts;

  }

  public Post editPost(int id, String title, String body) {
    Post post = getItem(id);

    post.setTitle(title);
    post.setContents(body);

    return editItem(id, post);

  }
}
