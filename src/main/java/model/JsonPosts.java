package model;

import java.util.ArrayList;


public class JsonPosts{
	private String titulo;
	private ArrayList<Posts> postsLista;

	public String getTitulo(){
		return this.titulo;
	}
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	public ArrayList<Posts> getPostLista(){
		return this.postsLista;
	}
	public void setPostsLista(ArrayList<Posts> postsLista){
		this.postsLista = postsLista;
	}
}