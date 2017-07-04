function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
        	$('#divImagem').show();
            $('#show-imagem').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
var form;
$("#input-imagem").change(function(event){
    readURL(this);

    form = new FormData();
    form.append('input-imagem', event.target.files[0]);

    $.ajax({
	   url: "http://localhost:8080/netflix/upload",
	   data: form,
	   processData: false,
	   contentType: false,
	   type: 'POST',
	   success: function(data) {
	   		console.log("data" + data);
	   		 $("#idFile").val(data);
	   },
	   error: function() {
	   		alert("erro no upload da imagem");
	      // $('#info').html('<p>An error has occurred</p>');
	   }
	});
});

function setPosts(){
	$("#send-conteudo").val($("#input-conteudo").val());
	$("#send-titulo").val($("#input-titulo").val());
	return true;
}


