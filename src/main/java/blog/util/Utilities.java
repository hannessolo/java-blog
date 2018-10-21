package blog.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities {

  public static int parseUriToPostId(String URI) {
    return Integer.parseInt(URI.split("_")[0]);
  }

  public static Map<String, Object> parseUrlEncodedBody(String body) throws UnsupportedEncodingException {

    Map<String, Object> map = new HashMap<>();
    String[] tokens = body.split("&");

    for (String token : tokens) {

      String[] keyAndValue = token.split("=");
      if (keyAndValue.length != 2) {
        throw new UnsupportedEncodingException();
      }

      String key = keyAndValue[0].replace('+', ' ');
      String value = keyAndValue[1].replace('+', ' ');

      key = replacePercentEncoding(key);
      value = replacePercentEncoding(value);

      try {
        int intValue = Integer.parseInt(value);
        map.put(key, intValue);
      } catch (NumberFormatException e) {
        map.put(key, value);
      }

    }
    return map;
  }

  private static String replacePercentEncoding(String before) {

    StringBuilder newString = new StringBuilder();

    for (int i = 0; i < before.length(); i++) {

      char here = before.charAt(i);

      if (here == '%') {

        char actual = (char) Integer.parseInt(before.substring(i + 1, i + 3), 16);
        newString.append(actual);
        i += 2;
      } else {
        newString.append(here);
      }
    }

    return newString.toString();

  }

  public static <K> List<K> reverseList(List<K> list) {
    int len = list.size();
    for (int i = 0; i < len / 2; i++) {
      K swap = list.get(len - 1 - i);
      list.set(len - 1 - i, list.get(i));
      list.set(i, swap);
    }
    return list;
  }

}
