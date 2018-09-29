package blog.post;

import blog.database.Dao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  private String title;
  private String contents;
  private LocalDateTime date;
  private Dao<Post> dao;

  public Post(String title, String contents, LocalDateTime date, Dao<Post> dao) {
    this.title = title;
    this.contents = contents;
    this.dao = dao;
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public String getURI() {
    return title.replace(' ', '_');
  }

  public String getContents() {
    return contents;
  }

  public String getDateString() {
    return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM"));
  }

  public LocalDateTime getDateObject() {
    return date;
  }

  public Dao<Post> getDao() {
    return dao;
  }

  public void deletePost() {
    dao.removeItem(this);
  }

}
