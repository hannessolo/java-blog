package com.hanneshertach.blog.staticpage;

import com.hanneshertach.blog.util.Path.Templates;
import com.hanneshertach.blog.util.ViewUtil;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

public class StaticPageController {

  public static Route serveAboutPage = (Request request, Response response) -> {

    Map<String, Object> model = new HashMap<>();

    model.put("title", "About");

    return ViewUtil.render(request, model, Templates.ABOUT_TEMPLATE);

  };

  public static Route serveContactPage = (Request request, Response response) -> {

    Map<String, Object> model = new HashMap<>();

    model.put("title", "Contact");

    return ViewUtil.render(request, model, Templates.CONTACT_TEMPLATE);

  };

}
