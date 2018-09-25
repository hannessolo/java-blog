(function() {

  var hamburger = document.getElementById("hamburger");
  var menu = document.getElementById("menu");
  var hidden = true;

  hamburger.addEventListener("click", function(e) {
    e.stopPropagation();
    if (hidden) {
      menu.classList.remove("hidden");
      menu.style.animation = "expand-menu 500ms ease-in-out";
    } else {
      menu.style.animation = "hide-menu 500ms ease-in-out";
      setTimeout(function() {
        menu.classList.add("hidden");
      }, 500);

    }

    hidden = !hidden;

  });

  document.addEventListener("click", function(e) {

    if (!hidden && e.target !== menu) {
      menu.style.animation = "hide-menu 500ms ease-in-out";
      setTimeout(function() {
        menu.classList.add("hidden");
      }, 500);
      hidden = true;
    }

  });

})();