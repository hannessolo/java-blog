package com.hanneshertach.blog.media;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import spark.Response;
import spark.Request;
import spark.Route;
import spark.utils.IOUtils;

public class MediaController {

  public static Route uploadMedia = (Request request, Response response) -> {

    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
    Part filePart = request.raw().getPart("file");

    try(InputStream inputStream = filePart.getInputStream()) {
      OutputStream outputStream = new FileOutputStream("/tmp" + filePart.getSubmittedFileName());
      IOUtils.copy(inputStream, outputStream);
      outputStream.close();
    }

    response.redirect("/admin");
    return null;

  };

}
