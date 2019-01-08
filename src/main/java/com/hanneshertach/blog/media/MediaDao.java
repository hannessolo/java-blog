package com.hanneshertach.blog.media;

import com.hanneshertach.blog.database.Dao;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.NotImplementedException;

public class MediaDao implements Dao<String> {

  @Override
  public void removeItem(String file) {
    File toDelete = new File(MediaController.FILE_FOLDER_ROUTE + file);
    if (!toDelete.exists()) {
      throw new RuntimeException("The file does not exist.");
    }
    if (!toDelete.delete()) {
      throw new RuntimeException("Could not delete file.");
    }
  }

  @Override
  public String addItem(String file) {
    throw new NotImplementedException();
  }

  @Override
  public List<String> getItems() {
    return Arrays.asList(new File(MediaController.FILE_FOLDER_ROUTE).list());
  }

  @Override
  public String getItem(int id) {
    return new File(MediaController.FILE_FOLDER_ROUTE).list()[id];
  }

  @Override
  public String editItem(int id, String item) {
    throw new NotImplementedException();
  }
}
