// Import required java libraries
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import model.Usuario;
import controller.ControllerUsuario;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/cadastro")
public class CadastroServlet extends HttpServlet {
 
 @Override
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)throws IOException
  {
      	String nome = request.getParameter("nome");
      	String email = request.getParameter("email");
      	String endereco = request.getParameter("endereco");
        String senha = request.getParameter("senha");

        //validacao dos campos 

        Usuario user = null;

        //verifica se ja existe usuario com esse email

        try{
           user = ControllerUsuario.LoadByEmail(email);
         }catch(Exception err){
            err.printStackTrace();
         }

         if(user != null){
         	   //ja existe usuario cadastrado com esse email 
         	    response.sendRedirect("http://localhost:8080/netflix/us/cadastro.jsp?emailerro=true&email="
         	    						+email
         	    						+"&username="+nome
         	    						+"&endereco="+endereco);
         }else{
         		try{
         			 user = ControllerUsuario.LoadByUserName(nome);
         		}catch(Exception err){
		            err.printStackTrace();
		        }

		         if(user != null){
         	  			 //ja existe usuario cadastrado com esse nome de usuario 
         	    		response.sendRedirect("http://localhost:8080/netflix/us/cadastro.jsp?usernameerro=true&email="
         	    						+email
         	    						+"&username="+nome
         	    						+"&endereco="+endereco);
        		 }else{
        		 		//cria o usuario e persiste no banco 
		         	    user = new Usuario(nome,senha,endereco,email,false);
				        boolean result = false;
				        try{
				        	result = ControllerUsuario.SaveUser(user);
				        }catch(Exception err){
							//tratar
							err.printStackTrace();	
						}

				        if(result){
				        	response.sendRedirect("http://localhost:8080/netflix/us/cadastrado.html");
				        }else{
				        	//erro ao cadastrar tente novamente
				        }
        		 }
         		
         }
  }
  
}