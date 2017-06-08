<!DOCTYPE html>
<html>
    <head>
        <title>Create a new Post</title>
        <link rel="stylesheet" type="text/css" href="../CSS/createpost.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body background="../Imagens/linho-cinza-textura-de-fundo_1053-253.jpg">
        <div class="head">    
            <h1>Criar post</h1>
        </div>
        <div class="mid">
            <form >
                <div class="caixa">
                    <p>Titulo</p>
                    <input type="text" name="titulo">
                    <p>Conteudo</p>
                    <% String conteudo = request.getParameter("conteudo");
                        if (conteudo == null) {
                            conteudo = "";
                        }
                        if(request.getSession().getAttribute("logado")== null){
                            response.sendRedirect("http://localhost:8080/netflix/us/erro.jsp?msg=Voce%20precisa%20estar%20logado%20para%20acessar%20este%20recurso");
                        }else if(request.getSession().getAttribute("admin")== null){
                                    response.sendRedirect("http://localhost:8080/netflix/us/erro.jsp?msg=Voce%20precisa%20ser%20um%20administrador%20para%20acessar%20este%20recurso");
                        }

                    %>
                    <textarea name="message" id="cont" rows="10" cols="50"  ><%out.println(conteudo);%></textarea>


                    <div class="botao">
                        <button type="button" onclick="$('#InserirImagen').show();$('#conteudo').val($('#cont').val());">Inserir Imagen</button>
                        <button type="button" onclick="$('#InserirVideo').show();$('#conteudo2').val($('#cont').val());" >Inserir Video</button>
                        <button type="button" onclick="window.location.href = 'http://localhost:8080/netflix/us/createpost.jsp?conteudo=' + $('#cont').val();" >Visualizar</button>
                        <button type="button"  onclick="window.location.href = 'http://localhost:8080/netflix/savepost?conteudo=' + $('#cont').val();" >Salvar</button>
                    </div>
                </div>

            </form>

            <div style="display:none" id="InserirImagen">
                <form action="http://localhost:8080/netflix/upload" method="post"
                      enctype="multipart/form-data">
                    <input  style="display:none" type="textarea" id ="conteudo" name="conteudo">
                    <input type="file" name="file" id="file" value="Arquivo"/> 
                    <button type="submit" >Inserir</button>
                </form>
            </div>
            <div style="display:none" id="InserirVideo">
                <form action="http://localhost:8080/netflix/upload" method="post"
                      enctype="multipart/form-data">
                    <input  style="display:none" type="textarea" id ="conteudo2" name="conteudo">
                    <input type="file" name="file" id="file" value="Arquivo"/> 
                    <button type="submit" >Inserir</button>
                </form>
            </div>
        </div>

        <div class="resultado" id="result">
            <p>resultado</p>
            <%out.println(conteudo);%>
        </div >

    </body>
</html>