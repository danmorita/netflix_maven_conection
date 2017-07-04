package controller;

import java.util.ArrayList;
import model.Posts;
import database.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerPosts{
	public boolean savePost(Posts posts)throws SQLException{
	  	ConexaoJDBC conexaojdbc = new ConexaoJDBC();
			Connection conexao = null;
			try{
				conexao = conexaojdbc.ConectaBD();
				String sql = "INSERT INTO posts"
				+"(conteudo,titulo,id_file)"
				+"VALUES(?,?,?);";
				PreparedStatement preparedstatement = conexao.prepareStatement(sql);
	            preparedstatement.setString(1,posts.getConteudo() + posts.getIdFile());
              preparedstatement.setString(2,posts.getTitulo());
              preparedstatement.setLong(3,1);
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

	public ArrayList<Posts> findByConteudo(String procura)throws SQLException{
      ArrayList<Posts> listaPosts= new ArrayList<Posts>();
      ConexaoJDBC conexaojdbc = new ConexaoJDBC();
      Connection conexao = null;
    try{
      conexao = conexaojdbc.ConectaBD();
      String sql = "SELECT * FROM posts WHERE conteudo LIKE ? ";
      PreparedStatement preparedstatement = conexao.prepareStatement(sql);
      preparedstatement.setString(1,"%"+procura+"%");
      ResultSet result = preparedstatement.executeQuery();
      Posts posts;
        while(result.next()){
        	posts = new Posts();
        	posts.setId(result.getInt("id"));
        	posts.setConteudo(result.getString("conteudo"));
            listaPosts.add(posts);
        }
       }catch(Exception err){
        err.printStackTrace();  
       }finally{
        if(conexao!=  null)
          conexao.close();
       }

       return listaPosts;
    }

    public Posts findById(int id)throws SQLException{
      ConexaoJDBC conexaojdbc = new ConexaoJDBC();
      Connection conexao = null;
      Posts posts = null;
    try{
      conexao = conexaojdbc.ConectaBD();
      String sql = "SELECT * FROM posts WHERE id = ? ";
      PreparedStatement preparedstatement = conexao.prepareStatement(sql);
      preparedstatement.setInt(1,id);
      ResultSet result = preparedstatement.executeQuery();
      
        if(result.next()){
          posts = new Posts();
          posts.setId(result.getInt("id"));
          posts.setConteudo(result.getString("conteudo"));
          posts.setTitulo(result.getString("titulo"));
          posts.setIdFile(result.getInt("id_file"));
        }
       }catch(Exception err){
        err.printStackTrace();  
       }finally{
        if(conexao!=  null)
          conexao.close();
       }

       return posts;
    }

    public ArrayList<Posts> findAll()throws SQLException{
      ArrayList<Posts> listaPosts= new ArrayList<Posts>();
      ConexaoJDBC conexaojdbc = new ConexaoJDBC();
      Connection conexao = null;
    try{
      conexao = conexaojdbc.ConectaBD();
      String sql = "SELECT * FROM posts order by id limit 2 ";
      PreparedStatement preparedstatement = conexao.prepareStatement(sql);
      ResultSet result = preparedstatement.executeQuery();
      Posts posts;
        while(result.next()){
          posts = new Posts();
          posts.setConteudo(result.getString("conteudo"));
          posts.setIdFile(result.getInt("id_file"));
          posts.setTitulo(result.getString("titulo"));
          posts.setId(result.getInt("id"));
            listaPosts.add(posts);
        }
       }catch(Exception err){
        err.printStackTrace();  
       }finally{
        if(conexao!=  null)
          conexao.close();
       }

       return listaPosts;
    }
}