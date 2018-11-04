package com.hanneshertach.blog.post;

import com.hanneshertach.blog.database.Dao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class Post {

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  private int id;
  private String title;
  private String contents;
  private LocalDateTime date;
  private Dao<Post> dao;

  public Post(int id, String title, String contents, LocalDateTime date, Dao<Post> dao) {
    this.id = id;
    this.title = title;
    this.contents = contents;
    this.dao = dao;
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  public String getURI() {
    return Integer.toString(id) + "_" + title.replace(' ', '_');
  }

  public String getContent() {
    return contents;
  }

  public String getPrettyContent() {
    return prettify(contents);
  }

  private String prettify(String contents) {
    Parser parser = Parser.builder().build();
    Node document = parser.parse(contents);
    return HtmlRenderer.builder().build().render(document);
  }

  public String getDateString() {
    return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
