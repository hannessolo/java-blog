package blog.post;

import blog.Dao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements Dao<Post> {

  private List<Post> posts;

  public PostDao() {

    posts = new ArrayList<>();

  }

  @Override
  public void removeItem(Post post) {
    posts.remove(post);
  }

  @Override
  public void addItem(Post post) {
    posts.add(post);
  }

  @Override
  public List<Post> getItems() {
    return posts;
  }

  @Override
  public Post getItem(String key) {
    for (Post p : posts) {
      if (p.getTitle().equals(key)) {
        return p;
      }
    }
    return null;
  }

  public void addPost(String title, String contents) {
    this.addItem(new Post(title, contents, LocalDateTime.now(), this));
  }

}
