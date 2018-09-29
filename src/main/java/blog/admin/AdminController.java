package blog.admin;

import java.util.HashMap;
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
    model.put("title", "Blog Administration");

    return ViewUtil.render(request, model, Templates.ADMIN_ROUTE);

  };

  public static void redirectIfNotLoggedIn(Request request, Response response) {

    if (false) {
      response.redirect("/", 403);
    }

    return;
  }

}
