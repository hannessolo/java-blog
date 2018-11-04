package com.hanneshertach.blog.database;

import java.util.List;

public interface Dao<K> {

  public void removeItem(K item);

  public K addItem(K item);

  public List<K> getItems();

  public K getItem(int id);

  public K editItem(int id, K item);

}
