package blog.util;

public interface Path {
  public static final String INDEX_ROUTE = "/";
  public static final String ADMIN_ROUTE = "/admin";
  public static final String CREATE_POST_ROUTE = "/posts/create";
  public static final String SINGLE_POST_ROUTE = "/posts/:title";
  public static final String ERR_404_ROUTE = "*";
  public static final String EDIT_POST_ROUTE = "/posts/:title/edit";
  public static final String LOGIN_ROUTE = "/admin/login";

  public static interface Templates {
    public static final String INDEX_TEMPLATE = "template/index/index.vm";
    public static final String ADMIN_TEMPLATE = "template/admin/admin.vm";
    public static final String SINGLE_POST_TEMPLATE = "template/post/post.vm";
    public static final String ERR_404_TEMPLATE = "template/404.vm";
    public static final String CREATE_POST_TEMPLATE = "template/post/create.vm";
    public static final String EDIT_POST_TEMPLATE = "template/post/edit.vm";
    public static final String LOGIN_TEMPLATE = "template/admin/login.vm";

  }

}
