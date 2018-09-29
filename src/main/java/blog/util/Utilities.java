package blog.util;

public class Utilities {

  public static int parseUriToPostId(String URI) {
    return Integer.parseInt(URI.split("_")[0]);
  }

}
