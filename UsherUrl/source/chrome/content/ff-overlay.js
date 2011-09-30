DukItanUsherUrl.onFirefoxLoad = function(event) {
  document.getElementById("contentAreaContextMenu")
          .addEventListener("popupshowing", function (e){ DukItanUsherUrl.showFirefoxContextMenu(e); }, false);
};

DukItanUsherUrl.showFirefoxContextMenu = function(event) {
  // show or hide the menuitem based on what the context menu is on
  document.getElementById("context-DukItanUsherUrl").hidden = gContextMenu.onImage;
};

window.addEventListener("load", function () { DukItanUsherUrl.onFirefoxLoad(); }, false);
