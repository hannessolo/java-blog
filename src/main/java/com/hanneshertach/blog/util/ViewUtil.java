package com.hanneshertach.blog.util;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.jetty.http.HttpStatus;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class ViewUtil {

  public static String render(Request request, Map<String, Object> model, String templatePath) {

    VelocityTemplateEngine vte = new VelocityTemplateEngine();

    return vte.render(new ModelAndView(model, templatePath));

  }

  public static Route render404 = (Request request, Response response) -> {

    Map<String, Object> model = new HashMap<>();
    model.put("title", 404);

    VelocityTemplateEngine vte = new VelocityTemplateEngine();

    response.status(HttpStatus.NOT_FOUND_404);

    return vte.render(new ModelAndView(model, Path.Templates.ERR_404_TEMPLATE));
  };

}
