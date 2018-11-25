package com.hanneshertach.blog.media;

import com.hanneshertach.blog.util.Utilities;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import spark.Response;
import spark.Request;
import spark.Route;
import spark.utils.IOUtils;

public class MediaController {

  private static final String FILE_FOLDER_ROUTE = "/tmp/";

  public static Route uploadMedia = (Request request, Response response) -> {

    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement(FILE_FOLDER_ROUTE));
    Part filePart = request.raw().getPart("file");

    try(InputStream inputStream = filePart.getInputStream()) {
      OutputStream outputStream = new FileOutputStream(FILE_FOLDER_ROUTE + filePart.getSubmittedFileName());
      IOUtils.copy(inputStream, outputStream);
      outputStream.close();
    }

    response.redirect("/admin");
    return null;

  };

  public static Route serveMedia = (Request request, Response response) -> {

    String fileName = request.params("file");
    if (fileName == null) {
      response.redirect("/", 404);
    }

    Path path = Paths.get(FILE_FOLDER_ROUTE + fileName);
    if (!Files.exists(path)) {
      response.redirect("/", 404);
    }

    byte[] media;

    media = Files.readAllBytes(path);

    HttpServletResponse raw = response.raw();

    raw.getOutputStream().write(media);
    raw.getOutputStream().flush();
    raw.getOutputStream().close();

    return raw;
  };

}
