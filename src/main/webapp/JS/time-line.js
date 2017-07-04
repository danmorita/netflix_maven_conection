// function showResult(str) {
//   if (str.length==0) { 
//     document.getElementById("livesearch").innerHTML="";
//     document.getElementById("livesearch").style.border="0px";
//     return;
//   }
//   if (window.XMLHttpRequest) {
//     // code for IE7+, Firefox, Chrome, Opera, Safari
//     xmlhttp=new XMLHttpRequest();
//   } else {  // code for IE6, IE5
//     xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//   }
//   xmlhttp.onreadystatechange=function() {
//     if (this.readyState==4 && this.status==200) {
//       document.getElementById("livesearch").innerHTML=this.responseText;
//       document.getElementById("livesearch").style.border="1px solid #A5ACB2";
//     }
//   }
//   xmlhttp.open("GET","http://localhost:8080/netflix/searchconteudo?pesquisar="+str,true);
//   xmlhttp.send();
// }

var sugestoes = [];

$( function() {
    $( "#pesquisar" ).autocomplete({
      source: sugestoes
    });
  } );

var wait = false;

function ok(){
	wait = false;
}

function pesquisarConteudo(){
	// console.log($("#pesquisar").val());
	if(!wait){
		wait = true;
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/netflix/searchconteudo",
			data: { pesquisar: $("#pesquisar").val() }
		})
		.done(function( msg ) {
			console.log( "Data Saved: " + msg );
			var jsonCont = JSON.parse(msg);
			sugestoes = [];
			jsonCont.postsLista.forEach(montarConteudo);
			console.log( "sugestoes: " + sugestoes );
			setTimeout(ok, 2000);
		});
	}
}

$("#pesquisar").keyup(function(event){
	$( "#pesquisar" ).autocomplete({
      source: sugestoes
    });

	setTimeout(pesquisarConteudo, 2000);
});

function montarConteudo(item, index) {
	sugestoes.push(item.conteudo);
}