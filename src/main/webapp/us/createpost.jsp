<!DOCTYPE html>
<html>
    <head>
        <title>Create a new Post</title>
        <link rel="stylesheet" type="text/css" href="../CSS/createpost.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Última versão CSS compilada e minificada -->
        <link rel="stylesheet" href="../CSS/bootstrap.min.css" >
        <!-- Tema opcional -->
        <link rel="stylesheet" href="../CSS/bootstrap-theme.min.css">

        <!-- Última versão JavaScript compilada e minificada -->
        <script src="../JS/bootstrap.min.js"></script>
    </head>
    <body background="../Imagens/linho-cinza-textura-de-fundo_1053-253.jpg">
        <div class="head">    
            <h1>Criar post</h1>
        </div>
        <form action="http://localhost:8080/netflix/savepost" method="POST">
            <div class="col-sm-5 col-xs-5">
                <div class="form-group">
                    <label for="input-titulo" class="col-sm-1 col-xs-1 control-label">Titulo</label>
                    <div class="col-sm-11 visible-xs-11">
                        <input id="input-titulo" class="form-control" type="text" name="titulo">
                    </div>
                </div>
                <div class="form-group">
                    <label for="input-conteudo" class="col-sm-1 col-xs-1 control-label">Conteudo</label>
                    <% String conteudo = request.getParameter("conteudo");
                        if (conteudo == null) {
                            conteudo = "";
                        }

                    %>
                    <div class="col-sm-11 col-xs-11">
                        <textarea class="form-control" id="input-conteudo" name="conteudo" rows="10" cols="50"  ><%out.println(conteudo);%></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group">
                        <div class="col-sm-1 col-md-1 col-xs-1">
                            <label for="">Inserir Imagen</label>
                        </div>
                        <div class="col-sm-11 col-md-11 col-xs-11">
                            <input id="input-imagem" name="input-imagem" type="file" class="btn btn-primary"></input>
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary" onclick="$('#InserirVideo').show();$('#conteudo2').val($('#input-conteudo').val());" >Inserir Video</button>
                    <button type="button" class="btn btn-primary" onclick="window.location.href = 'http://localhost:8080/netflix/us/createpost.jsp?conteudo=' + $('#input-conteudo').val(); " >Visualizar</button>
                        <!-- <button type="button" class="btn btn-primary" onclick="window.location.href = 'http://localhost:8080/netflix/savepost?conteudo=' + $('#input-conteudo').val();+ '&titulo=' + $('#input-titulo').val();" >Salvar</button> -->
                    <button type="submit" class="btn btn-primary">Salvar</button>
                </div>
            </div>
            <div class="col-sm-7 col-xs-7" style="display:none" id="divImagem">
                <input type="hidden" name="idFile" id="idFile" />
                <img id="show-imagem" src="#" alt="imagem" />
            </div>
        </form>

        
        <div style="display:none" id="InserirVideo">
            <form action="http://localhost:8080/netflix/upload" method="post"
                  enctype="multipart/form-data">
                <input  style="display:none" type="textarea" id ="conteudo2" name="conteudo">
                <input type="file" name="file" id="file" value="Arquivo"/> 
                <button type="submit" >Inserir</button>
            </form>
        </div>

        <div class="resultado" id="result">
            <p>resultado</p>
            <%out.println(conteudo);%>
        </div >
        <script src="../JS/createpost.js"></script>
    </body>
</html>