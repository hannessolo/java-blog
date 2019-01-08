(function() {

  var els = document.getElementsByClassName("delete-button");

  Array.prototype.forEach.call(els, function (value) {
    value.addEventListener("click", function (e) {

      e.stopPropagation();

      var mediaName = value.getAttribute("mediaName");

      fetch("/media/" + mediaName + "/delete", {
        method: "delete"
      }).then(function (v) {
        location = "/admin";
      });

    });
  });

})();