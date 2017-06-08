package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import database.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.StringUtil;
import java.sql.ResultSet;


@WebServlet(urlPatterns = "/getpost")
public class GetPostsServlet extends HttpServlet {
 
	 @Override
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)throws IOException
	  {
	      	String id  = request.getParameter("id");
	      	try{
	      		response.setContentType("text/html");
	      		response.getWriter().write(StringUtil.toHtmlEncode(LoadPost(id)));
	      		
	      	}catch(Exception err){
				//tratar
				err.printStackTrace();	
			}
	      	
			
	  }

	  public String LoadPost(String id)throws SQLException{
	  	String conteudo="<html><head><link rel='stylesheet'type='text/css' href='../netflix/CSS/posts.css'/></head><body>";
	  	ConexaoJDBC conexaojdbc = new ConexaoJDBC();
		Connection conexao = null;
		try{
			conexao = conexaojdbc.ConectaBD();
			String sql = "SELECT * FROM posts WHERE id = ?";
			PreparedStatement preparedstatement = conexao.prepareStatement(sql);
	        preparedstatement.setString(1,id);
		    ResultSet result = preparedstatement.executeQuery();
	    	
	    	if(result.next()){
	    		//encontrou
	    		 conteudo += result.getString("conteudo");
	        }
	     }catch(Exception err){
	     	err.printStackTrace();	
	     }finally{
	     	if(conexao!=  null)
	     		conexao.close();
	     }

	     conteudo +="</body></html>";


	     return conteudo;
	  }
}