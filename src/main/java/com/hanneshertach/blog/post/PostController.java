package com.hanneshertach.blog.post;

import com.hanneshertach.blog.admin.AdminController;
import com.hanneshertach.blog.database.Dao;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import com.hanneshertach.blog.util.Path.Templates;
import com.hanneshertach.blog.util.ViewUtil;
import com.hanneshertach.blog.util.Utilities;

public class PostController {

  public static Route showSinglePost = (Request request, Response response) -> {

    Map<String, Object> model = new HashMap<>();

    Dao<Post> dao = new PostDao();

    Post post = dao.getItem(Utilities.parseUriToPostId(request.params("title")));

    if (post == null) {
      response.redirect("/404");
    }

    model.put("title", post.getTitle());
    model.put("post", post);

    return ViewUtil.render(request, model, Templates.SINGLE_POST_TEMPLATE);

  };

  public static Route createPost = (Request request, Response response) -> {

    AdminController.redirectIfNotLoggedIn(request, response);

    PostDao dao = new PostDao();
    Map<String, Object> body;

    try {
      body = Utilities.parseUrlEncodedBody(request.body());
    } catch (UnsupportedEncodingException e) {
      response.redirect("/admin?success=false", 400);
      return null;
    }

    if (!body.containsKey("title") || !body.containsKey("body")) {
      response.redirect("/admin?success=false", 400);
      return null;
    }

    Post post = dao.addPost((String)body.get("title"), (String)body.get("body"));

    if (post == null) {
      response.redirect("/admin?success=false", 400);
      return null;
    }

    response.redirect(post.getURI());

    return null;

  };

  public static Route serveCreatePostPage = (Request request, Response response) -> {

    AdminController.redirectIfNotLoggedIn(request, response);

    Map<String, Object> model = new HashMap<>();
    model.put("title", "Create New Post");

    return ViewUtil.render(request, model, Templates.CREATE_POST_TEMPLATE);

  };

  public static Route serveEditPostPage = (Request request, Response response) -> {

    AdminController.redirectIfNotLoggedIn(request, response);

    Dao<Post> dao = new PostDao();
    Post post = dao.getItem(Utilities.parseUriToPostId(request.params("title")));

    Map<String, Object> model = new HashMap<>();
    model.put("title", "Edit: " + post.getTitle());
    model.put("post", post);

    return ViewUtil.render(request, model, Templates.EDIT_POST_TEMPLATE);

  };

  public static Route editPost = (Request request, Response response) -> {
    AdminController.redirectIfNotLoggedIn(request, response);

    PostDao dao = new PostDao();
    Map<String, Object> body;

    try {
      body = Utilities.parseUrlEncodedBody(request.body());
    } catch (UnsupportedEncodingException e) {
      response.redirect("/admin?success=false", 400);
      return null;
    }

    if (!body.containsKey("title") || !body.containsKey("body") || !body.containsKey("id")) {
      response.redirect("/admin?success=false", 400);
      return null;
    }

    Post post = dao.editPost((int)body.get("id"), (String)body.get("title"), (String)body.get("body"));

    if (post == null) {
      response.redirect("/admin?success=false", 400);
      return null;
    }

    response.redirect("/posts/" + post.getURI());

    return null;

  };

  public static Route deletePost = (Request request, Response response) -> {

    AdminController.redirectIfNotLoggedIn(request, response);

    Dao<Post> dao = new PostDao();

    Post post = dao.getItem(Utilities.parseUriToPostId(request.params("title")));

    post.deletePost();

    response.redirect("/admin?success=true");

    return null;

  };

}
