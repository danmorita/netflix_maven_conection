var jsonPosts;
var reloadPosts = document.getElementById("reload");
var html = "";
function reload(){
  html = "";
  var xmlHttp;
  if (window.XMLHttpRequest) { // Mozilla, Safari, ...
      xmlHttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // IE
      try {
        xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
      } 
      catch (e) {
        try {
          xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
        } 
        catch (e) {}
      }
    }
    if (!xmlHttp) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }
  var url = "http://localhost:8080/netflix/searchposts";
  //var url = "danilomissaomorita";
  xmlHttp.onreadystatechange = function(){
    console.log(this.responseText);
      if(this.readyState == 4 && this.status == 200){
        //document.getElementById("reload").innerHTML = this.responseText;
        jsonPosts = JSON.parse(this.responseText);
        document.getElementById("reload").innerHTML = jsonPosts;
        mostrarPosts();
        reloadPosts.innerHTML = html;
      }
  };
  //xmlHttp.onreadystatechange = alertContents();
  xmlHttp.open("POST",url);
  xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  var input=document.getElementById("pesquisar");
  xmlHttp.send("pesquisar=" + encodeURIComponent(input.value));

}

function mostrarPosts(){
  html = html + "<div class='head'><h1>"+jsonPosts.titulo+"</h1></div>";
  html = html + "<div class='mid'>";
  jsonPosts.postsLista.forEach(montarPosts);
  html = html + "</div>";
  console.log(html);
}

function montarPosts(item, index) {
    html = html + "<div class='conteudo'>";
    html = html + "<a href='http://localhost:8080/netflix/getpost?id=" + item.id;
    html = html + "'>Ver post completo</a>";
    html = html + " <iframe src='http://localhost:8080/netflix/getpost?id=" + item.id;
    html = html + "' scrolling='no' >";
    html = html + "</iframe></div>";
}