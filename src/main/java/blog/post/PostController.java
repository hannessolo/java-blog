package blog.post;

import blog.Dao;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import blog.util.Path.Templates;
import blog.util.ViewUtil;

public class PostController {

  public static Route showSinglePost = (Request request, Response response) -> {

    Map<String, Object> model = new HashMap<>();
    model.put("title", "blog.Blog");

    Dao<Post> dao = new PostDao();
    ((PostDao) dao).addPost("testTitle", "TestContents");

    Post post = dao.getItem(request.params("title"));

    if (post == null) {
      response.redirect("/404");
    }

    model.put("post", post);

    return ViewUtil.render(request, model, Templates.SINGLE_POST_ROUTE);

  };

}
