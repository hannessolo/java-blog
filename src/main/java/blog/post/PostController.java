package blog.post;

import blog.admin.AdminController;
import blog.database.Dao;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import blog.util.Path.Templates;
import blog.util.ViewUtil;
import blog.util.Utilities;

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

    return ViewUtil.render(request, model, Templates.SINGLE_POST_ROUTE);

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

}
