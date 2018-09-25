(function() {

  var header = document.getElementById("header");

  document.addEventListener("scroll", function() {

    if (window.scrollY <= 0) {
      header.style.setProperty("border-bottom", "none");
    } else {
      header.style.setProperty("border-bottom", "1px solid springgreen");
    }

  });

})();