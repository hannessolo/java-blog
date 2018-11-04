package com.hanneshertach.blog.admin;

import com.hanneshertach.blog.post.Post;
import com.hanneshertach.blog.post.PostDao;
import com.hanneshertach.blog.util.Utilities;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import com.hanneshertach.blog.util.Path.Templates;
import com.hanneshertach.blog.util.ViewUtil;

public class AdminController {

  // todo: move to config file
  private static final byte SALT = (byte) 1234;

  public static Route serveAdminPage = (Request request, Response response) -> {

    redirectIfNotLoggedIn(request, response);

    Map<String, Object> model = new HashMap<>();

    PostDao postDao = new PostDao();

    List<Post> posts = postDao.getItems();

    model.put("title", "Blog Administration");
    model.put("posts", posts);

    return ViewUtil.render(request, model, Templates.ADMIN_TEMPLATE);

  };

  public static Route serveLoginPage = (Request request, Response response) -> {

    if (request.session(false) != null) {
      response.redirect("/");
      return null;
    }

    Map<String, Object> model = new HashMap<>();
    model.put("title", "Login");

    String success = request.queryMap().get("success").value();

    if (success != null && success.equals("false")) {
      model.put("message", "Incorrect username and/or password.");
    }

    return ViewUtil.render(request, model, Templates.LOGIN_TEMPLATE);

  };

  public static Route login = (Request request, Response response) -> {

    if (request.session(false) != null) {
      response.redirect("/");
      return null;
    }

    Map<String, Object> body;

    try {
      body = Utilities.parseUrlEncodedBody(request.body());
    } catch (UnsupportedEncodingException e) {
      response.redirect("/admin?success=false", 400);
      return null;
    }

    if (!body.containsKey("username") || !body.containsKey("password")) {
      response.redirect("/admin?success=false", 400);
      return null;
    }

    String username = (String) body.get("username");
    String password = hashAndSaltPassword((String) body.get("password"));

    AdminDao dao = new AdminDao();
    Admin admin = dao.getAdminByUsernameAndPassword(username, password);

    if (admin == null) {
      response.redirect("/admin/login?success=false");
      return null;
    }

    request.session(true).attribute("username", username);
    request.session().attribute("id", admin.getId());
    request.session().maxInactiveInterval(500);

    response.redirect("/admin");
    return null;
  };

  public static Route logout = (Request request, Response response) -> {

    if (request.session(false) == null) {
      response.redirect("/");
    }

    request.session(false).invalidate();

    response.redirect("/");
    return null;

  };

  public static void redirectIfNotLoggedIn(Request request, Response response) {

    if (request.session(false) == null) {
      //response.redirect("/admin/login");
    }

  }

  private static String hashAndSaltPassword(String password) {

    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      for (int i = 0; i < hash.length; i++) {
        hash[i] ^= SALT;
      }
      return new String(Base64.getEncoder().encode(hash));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return null;
  }

}
