(function() {

  var els = document.getElementsByClassName("delete-button");

  Array.prototype.forEach.call(els, function (value) {
    value.addEventListener("click", function (e) {

      e.stopPropagation();

      var postUri = value.getAttribute("postURI");

      fetch("/posts/" + postUri, {
        method: "delete"
      }).then(function (value1) {
        location = "/admin";
      });

    });
  });

})();