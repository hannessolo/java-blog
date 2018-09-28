package blog.post;

import blog.Dao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

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

  public String getContents() {
    return contents;
  }

  public String getDateString() {
    return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM"));
  }

  public void deletePost() {
    dao.removeItem(this);
  }

}
