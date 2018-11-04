package com.hanneshertach.blog;

import com.hanneshertach.blog.post.PostDao;
import com.hanneshertach.blog.util.Utilities;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;
import com.hanneshertach.blog.util.Path;
import com.hanneshertach.blog.util.ViewUtil;

public class IndexController {

  public static Route serveMainPage = (Request request, Response response) -> {

    Map<String, Object> model = new HashMap<>();
    model.put("title", "blog.");

    PostDao indexPostsDao = new PostDao();

    model.put("posts", Utilities.reverseList(indexPostsDao.getPostsTruncated(600)));

    return ViewUtil.render(request, model, Path.Templates.INDEX_TEMPLATE);

  };

}
