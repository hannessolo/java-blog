package blog;

import blog.post.PostDao;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import blog.util.Path;
import blog.util.ViewUtil;

public class IndexController {

  public static Route serveMainPage = (Request request, Response response) -> {

    Map<String, Object> model = new HashMap<>();
    model.put("title", "blog.");

    PostDao indexPostsDao = new PostDao();

    model.put("posts", indexPostsDao.getPostsTruncated(600));

    return ViewUtil.render(request, model, Path.Templates.INDEX_ROUTE);

  };

}
