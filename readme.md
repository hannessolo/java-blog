# Blog
Repository containing the code for the blog to be hosted at blog.hanneshertach.com in the future.

Built using the JavaSpark web framework.

You can easily try using this yourself. Just clone the repo and run teh following:

Build using `mvn package`, then run `docker-compose up`. You can view the website running on `localhost:4567`.

# Features

This is a simple, custom-built CMS tool. It shows a restricted permission admin panel under `/admin`. The default username and password are `admin` and `testpwd`.

The admin panel lets users create new posts uing markdown for styling, as well as delete and update old posts.
