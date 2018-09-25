package blog.post;

import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import blog.util.Path.Templates;
import blog.util.ViewUtil;

public class PostController {

  public static Route serveMainPage = (Request request, Response response) -> {

    Map<String, Object> model = new HashMap<>();
    model.put("title", "blog.Blog");

    return ViewUtil.render(request, model, Templates.POSTS_ROUTE);

  };

}
