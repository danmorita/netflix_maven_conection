package model;

public class Arquivo{
	private String id;
	private String conteudo;
	private String extensao;

	public Arquivo(String id ,String conteudo,String extensao){
		this.id = id;
		this.conteudo = conteudo;
		this.extensao = extensao;
	}

	public Arquivo(String conteudo,String extensao){
		this.id = null;
		this.conteudo = conteudo;
		this.extensao = extensao;
	}

	public Arquivo(){
	}
	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public String getConteudo(){
		return conteudo;
	}

	public String getExtensao(){
		return extensao;
	}
}