package controller;

import model.Usuario;
import database.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerUsuario{
	public static boolean SaveUser(Usuario user) throws SQLException{
		ConexaoJDBC conexaojdbc = new ConexaoJDBC();
		Connection conexao = null;
		try{
			conexao = conexaojdbc.ConectaBD();
			String sql = "INSERT INTO Usuario"
			+"(username,senha,endereco,email,permissao)"
			+"VALUES(?,?,?,?,?);";
			PreparedStatement preparedstatement = conexao.prepareStatement(sql);
            preparedstatement.setString(1,user.getNome());
            preparedstatement.setString(2,user.getSenha());
            preparedstatement.setString(3,user.getEndereco());
            preparedstatement.setString(4,user.getEmail());
            preparedstatement.setBoolean(5,user.getAdmin());
            preparedstatement.executeUpdate();

		}catch(Exception err){
			//tratar
			err.printStackTrace();	
			return false;
		}finally{
			if(conexao!=null)
				conexao.close();
		}
		
		return true;
		
	}

	public static Usuario LoadByEmail(String email)throws SQLException{
		Usuario user = null;
		ConexaoJDBC conexaojdbc = new ConexaoJDBC();
		Connection conexao = null;
		try{
			conexao = conexaojdbc.ConectaBD();
			String sql = "SELECT * FROM Usuario WHERE email = ?";
			PreparedStatement preparedstatement = conexao.prepareStatement(sql);
	        preparedstatement.setString(1,email);

		    ResultSet result = preparedstatement.executeQuery();
	    	
	    	if(result.next()){
	    		//encontrou
	    		String nome = result.getString("username");
	    		String resultemail = result.getString("email");
	    		String senha = result.getString("senha");
	    		String endereco = result.getString("endereco");
	    		boolean admin = result.getBoolean("permissao");

	    		user = new Usuario(nome,senha,endereco,resultemail,admin);
	     		
	        }
	     }catch(Exception err){
	     	err.printStackTrace();	
	     }finally{
	     	if(conexao!=  null)
	     		conexao.close();
	     }
        
         
         return user;
	}

	public static Usuario LoadByUserName(String username)throws SQLException{
		Usuario user = null;
		ConexaoJDBC conexaojdbc = new ConexaoJDBC();
		Connection conexao = null;
		try{
			conexao = conexaojdbc.ConectaBD();
			String sql = "SELECT * FROM Usuario WHERE username = ?";
			PreparedStatement preparedstatement = conexao.prepareStatement(sql);
	        preparedstatement.setString(1,username);

		    ResultSet result = preparedstatement.executeQuery();
	    	
	    	if(result.next()){
	    		//encontrou
	    		String nome = result.getString("username");
	    		String resultemail = result.getString("email");
	    		String senha = result.getString("senha");
	    		String endereco = result.getString("endereco");
	    		boolean admin = result.getBoolean("permissao");

	    		user = new Usuario(nome,senha,endereco,resultemail,admin);
	     		
	        }
	     }catch(Exception err){
	     	err.printStackTrace();	
	     }finally{
	     	if(conexao!=  null)
	     		conexao.close();
	     }
        
         
         return user;
	}


}