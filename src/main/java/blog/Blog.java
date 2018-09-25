package blog;

import static spark.Spark.*;

import blog.post.PostController;

import blog.util.Path;
import blog.util.ViewUtil;

public class Blog {

  public static void main(String[] args) {

    if (args.length > 0 && args[0].equals("dev")) {
      String projectDir = System.getProperty("user.dir");
      String staticDir = "/src/main/resources/public";
      staticFiles.externalLocation(projectDir + staticDir);
    } else {
      staticFiles.location("/public");
    }

    get(Path.ADMIN_ROUTE, PostController.serveMainPage);

    get(Path.POSTS_ROUTE, PostController.serveMainPage);

    get(Path.INDEX_ROUTE, IndexController.serveMainPage);

    get(Path.ERR_404_ROUTE, ViewUtil.render404);

    init();
  }

}
