var DukItanUsherUrl = {
  onLoad: function() {
    // initialization code
    this.initialized = true;
    this.strings = document.getElementById("DukItanUsherUrl-strings");
  },

  onMenuItemCommand: function(e) {
  var promptService = Components.classes["@mozilla.org/embedcomp/prompt-service;1"]
                                  .getService(Components.interfaces.nsIPromptService);

    var urlOriginal  = gURLBar.value;
    var url          = "";
    var tamanho      = urlOriginal.length;
    var separador    = urlOriginal.indexOf('=')+1;
    var urlTemp      = urlOriginal.substring(separador,tamanho);
    var tamanho      = urlTemp.length;
    var urlPadrao    = ["http",".com",".net",".org"];
    var urlOriginalI = -1;
    
    for (var i=0; i<urlPadrao.length; i++){
		
      if (urlOriginal.indexOf(urlPadrao[i])>-1){
		  
        urlOriginalI = i;
        break;
		
      }
      
    }

    
    if (urlOriginalI>-1){

      if (urlTemp.indexOf("http")>-1){

        url = urlTemp;
        
      } else {

        for (var i=0; i<tamanho; i++){
          
          url = url.concat(urlTemp[tamanho-(i+1)]);
        }
        
      }

      if ((urlOriginal!=url) && (url.indexOf("http")>-1)){

        gURLBar.value=this.strings.getString("duu_goto")+": "+url;
        
        gBrowser.loadURI(url);
        
      }
    }
    
  },

  onToolbarButtonCommand: function(e) {
    // just reuse the function above.  you can change this, obviously!
    DukItanUsherUrl.onMenuItemCommand(e);
  }
};

window.addEventListener("load", function () { DukItanUsherUrl.onLoad(); }, false);
