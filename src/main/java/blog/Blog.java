package blog;

import static spark.Spark.*;

import blog.admin.AdminController;
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

    get(Path.ADMIN_ROUTE, AdminController.serveAdminPage);

    post(Path.CREATE_POST_ROUTE, PostController.createPost);

    get(Path.SINGLE_POST_ROUTE, PostController.showSinglePost);

    get(Path.INDEX_ROUTE, IndexController.serveMainPage);

    get(Path.ERR_404_ROUTE, ViewUtil.render404);

    init();
  }

}
