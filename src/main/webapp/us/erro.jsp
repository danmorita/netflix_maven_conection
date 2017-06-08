<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link rel="stylesheet" href="../CSS/semAcesso.css">
</head>
<body background="../Imagens/linho-cinza-textura-de-fundo_1053-253.jpg">
	<div class="conteudo">
		<div class="titulo">
		<% 
			String msg = request.getParameter("msg");
			if (msg == null)
				msg = "Ocorreu um erro desconhecido ao tentar acessar o recurso";

			%>
			<h1>  <%out.println(msg);%></h1>
		</div>
		<div class="logo">
			<img src="../Imagens/220px-Achtung.svg.png" alt="Sem Acesso">
		</div>
		<div class="botao">
			<a class="voltar" href="javascript:window.history.go(-1)">Voltar</a>
		</div>
	</div>
</body>
</html>