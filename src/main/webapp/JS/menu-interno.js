
/*
* Francisco da Crux Xavier
* 30/02/2017
*
* menu interno netflix
*/
window.onload = function(){//ao carregar a pagina
						document.getElementById("cancele").style.fill = 'white';
						document.getElementById("cancele").style.color = 'white';
						document.getElementById("2").style.display = 'none';
						document.getElementById("3").style.display = 'none';

				}
				
function menu(opcao){
	if(opcao == 1){
		document.getElementById("2").style.display = 'none';
		document.getElementById("3").style.display = 'none';
		document.getElementById('1').style.display = 'block';//show this
		document.getElementById('bar-1').className += ' active'; 
		document.getElementById('bar-2').className = ' barra-menu'; 
		document.getElementById('bar-3').className = ' barra-menu'; 
		document.getElementById("cancele").style.fill = 'white';
		document.getElementById("cancele").style.color = 'white';
		document.getElementById("device").style.fill = '#757575';
		document.getElementById("device").style.color = '#757575'; 
		document.getElementById("planos").style.fill = '#757575';
		document.getElementById("planos").style.color = '#757575'; 

	}else if(opcao == 2){
		document.getElementById("1").style.display = 'none';
		document.getElementById("3").style.display = 'none';
		document.getElementById('2').style.display = 'block';//show this
		document.getElementById('bar-2').className += ' active';
		document.getElementById('bar-1').className = ' barra-menu'; 
		document.getElementById('bar-3').className = ' barra-menu'; 
		document.getElementById("cancele").style.fill = '#757575';
		document.getElementById("cancele").style.color = '#757575'; 
		document.getElementById("planos").style.fill = '#757575';
		document.getElementById("planos").style.color = '#757575'; 
		document.getElementById("device").style.fill = 'white';
		document.getElementById("device").style.color = 'white';
	}else if(opcao == 3){
		document.getElementById("2").style.display = 'none';
		document.getElementById("1").style.display = 'none';
		document.getElementById('3').style.display = 'block';//show this
		document.getElementById('bar-3').className += ' active'; 
		document.getElementById('bar-2').className = ' barra-menu'; 
		document.getElementById('bar-1').className = ' barra-menu'; 
		document.getElementById("cancele").style.fill = '#757575';
		document.getElementById("cancele").style.color = '#757575'; 
		document.getElementById("device").style.fill = '#757575';
		document.getElementById("device").style.color = '#757575'; 
		document.getElementById("planos").style.fill = 'white';
		document.getElementById("planos").style.color = 'white';
	}
	
}