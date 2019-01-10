package br.edu.denis.entidade;

public class Nota {
	
	//variaveis
	private int id;
	private String titulo;
	private String descricao;
	
	//consturtor
	public Nota() {
		
	}
	
	
	//getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
