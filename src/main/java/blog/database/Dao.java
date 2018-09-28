package blog.database;

import java.util.List;

public interface Dao<K> {

  public void removeItem(K item);

  public void addItem(K item);

  public List<K> getItems();

  public K getItem(String key);

}
