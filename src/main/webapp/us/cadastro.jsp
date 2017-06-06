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
	<div class="mid">
		<div class="caixaEntrar">
			<h1>Sign Up</h1>	
			<form action="http://localhost:8080/netflix/cadastro" method="POST">
				<div class="login">
				    <span>User Login</span>
					<% String username = request.getParameter("username");
		                if(username == null){
		                  	username = "";
		                } 
		            %>
					<div class="email">
						<input type="text" name="nome" required="required" value= <%=username%> >
						<p><%
			                String usernameerro = request.getParameter("usernameerro");
			                if(usernameerro != null){
			                  out.println("Username is alreally in use, chosee other!");
			                }
			             %></p>
					</div>
					<span>Email</span>
					<div class="email">
						<% String email = request.getParameter("email");
		                if(email == null){
		                  	email = "";
		                } 
		          	    %>
						<input type="text" name="email" required="required" value= <%=email%> >
						<p><%
			                String emailerro = request.getParameter("emailerro");
			                if(emailerro != null){
			                  out.println("Email is alreally in use, chosee other!");
			                }
			             %></p>	
					</div>
					<span>Adress</span>
					<div class="email">
						<% String endereco = request.getParameter("endereco");
		                if(endereco == null){
		                  	endereco = "";
		                } 
		          	    %>
						<input type="text" name="endereco" required="required" value= <%=endereco%> >
					</div>
					<span>Password</span>
					<div class="senha">
						<input type="password" name="senha" required="required">
					</div>
					<div class="forget">
						<a href="">Forgot your email or password?</a>
					</div>
					<div class="botao">
						<input class="boto" type="submit" name="Sign in"/>
					</div>
					<div class="lembre">
						<input type="checkbox" name="lembre" value="html"/>Remember me<br/>
					</div>
				</div>
			</form>
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