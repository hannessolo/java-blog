package blog.admin;

import blog.post.Post;
import blog.post.PostDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import blog.util.Path.Templates;
import blog.util.ViewUtil;

public class AdminController {

  public static Route serveAdminPage = (Request request, Response response) -> {

    redirectIfNotLoggedIn(request, response);

    Map<String, Object> model = new HashMap<>();

    PostDao postDao = new PostDao();

    List<Post> posts = postDao.getItems();

    model.put("title", "Blog Administration");
    model.put("posts", posts);

    return ViewUtil.render(request, model, Templates.ADMIN_TEMPLATE);

  };

  public static void redirectIfNotLoggedIn(Request request, Response response) {

    if (false) {
      response.redirect("/", 403);
    }

    return;
  }

}
