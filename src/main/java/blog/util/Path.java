package blog.util;

public interface Path {
  public static final String INDEX_ROUTE = "/";
  public static final String ADMIN_ROUTE = "/admin";
  public static final String SINGLE_POST_ROUTE = "/posts/:title";
  public static final String ERR_404_ROUTE = "*";

  public static interface Templates {
    public static final String INDEX_ROUTE = "template/index/index.vm";
    public static final String ADMIN_ROUTE = "template/admin/admin.vm";
    public static final String SINGLE_POST_ROUTE = "template/post/post.vm";
    public static final String ERR_404_ROUTE = "template/404.vm";
  }

}
