<!DOCTYPE html>
<html lang="en-us">
<head>
  <meta charset="utf-8"/>
  <title>Netflix</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="../CSS/layoutEntrar.css"/>
</head>
<body>
  <div class="head">
    <div class="ico">
      <a href="index.html" ><img class="logo" src="../Imagens/logo.png"/></a>
    </div>
  </div>
  <%

  	if(request.getSession().getAttribute("logado")!= null){
  			response.sendRedirect("http://localhost:8080/netflix/timeline");
  	}
  %>
  <div class="mid">
    <div class="caixaEntrar">
      <h1>Sign In</h1>  
      <form action="http://localhost:8080/netflix/login" method="POST">
        <div class="login">
          <span>Email</span>
          <div class="email">
            <% String email = request.getParameter("email");
                if(email == null){
                  email="";
                } 
            %>
            <input type="email" name="email" id="numero" required="required" value= <%=email%>  >
             <p>
           <%
                String emailerro = request.getParameter("emailerro");
                if(emailerro != null){
                  out.println("Nao existe usuario cadastrado com esse endereco de email!");
                }
           %>
           </p>
          </div>
          
          <span>Password</span>
          <div class="senha">
            <input type="password" name="senha" id="senha" required="required">
             <p><%
                String senhaerro = request.getParameter("senhaerro");
                if(senhaerro != null){
                  out.println("Parece que voce informou uma senha errada, tente novamente!");
                }
             %></p>
          </div>
          <div class="botao">
            <button type="submit">Sign In</button>
          </div>
        </div>
      </form>
      <div class="inscreva">
        <span>New to Netflix?</span>
        <a href="http://localhost:8080/netflix/us/cadastro.jsp">Sign up now.</a>
      </div>
    </div>
  </div>
  <!--rodape-->
    <div class="rodape">
      <div class="duvidas">
        <span>Questions? Call </span>
        <a href="" class="ligue">0800-887-0201</a>
      </div>
      <div class="termos1">
        <a href="" class="cartao">Gift Card Terms</a>
        <a href="" class="uso">Terms of Use</a>
        <a href="" class="declaracao">Privacy Statement</a>
      </div>
      
      <!--seletor de idiomas-->
      <select class = "idiomas" onchange="tradutor()" id="idioma">
          <option value="PT" selected="selected" >PORTUGUES</option>
          <option value="EN" >ENGLISH</option>
      
      </select>      
    </div> 
</body>
</html>
 
