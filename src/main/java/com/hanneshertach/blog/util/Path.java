package com.hanneshertach.blog.util;

public interface Path {
  String INDEX_ROUTE = "/";
  String ADMIN_ROUTE = "/admin";
  String CREATE_POST_ROUTE = "/posts/create";
  String SINGLE_POST_ROUTE = "/posts/:title";
  String ERR_404_ROUTE = "*";
  String EDIT_POST_ROUTE = "/posts/:title/edit";
  String LOGIN_ROUTE = "/admin/login";
  String ABOUT_ROUTE = "/about";
  String CONTACT_ROUTE = "/contact";
  String LOGOUT_ROUTE = "/admin/logout";
  String MEDIA_UPLOAD_ROUTE = "/media/upload";
  String MEDIA_DELETE_ROUTE = "/media/:id/delete";
  String MEDIA_VIEW_ROUTE = "/media/view";
  String GET_MEDIA_ROUTE = "/media/view/:file";
  String PASSWORD_RESET_PATH = "/admin/password-reset";

  interface Templates {
    String INDEX_TEMPLATE = "template/index/index.vm";
    String ADMIN_TEMPLATE = "template/admin/admin.vm";
    String SINGLE_POST_TEMPLATE = "template/post/post.vm";
    String ERR_404_TEMPLATE = "template/404.vm";
    String CREATE_POST_TEMPLATE = "template/post/create.vm";
    String EDIT_POST_TEMPLATE = "template/post/edit.vm";
    String LOGIN_TEMPLATE = "template/admin/login.vm";
    String ABOUT_TEMPLATE = "template/static/about.vm";
    String CONTACT_TEMPLATE = "template/static/contact.vm";
    String MEDIA_VIEW_TEMPLATE = "template/media/view.vm";
  }

}
