package model;

public class Posts {
	private int id;
	private String conteudo;
	private String titulo;
	private int idFile;

	public Posts(int id, String conteudo, String titulo){
		this.id = id;
		this.conteudo = conteudo;
		this.titulo = titulo;
	} 
	public Posts(){
		
	}
	public String getConteudo(){
		return conteudo;
	}
	public void setConteudo(String conteudo){
		this.conteudo = conteudo;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getTitulo(){
		return titulo;
	}
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public int getIdFile(){
		return idFile;
	}
	public void setIdFile(int idFile){
		this.idFile = idFile;
	}
}