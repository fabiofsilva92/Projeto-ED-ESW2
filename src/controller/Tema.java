package controller;

public class Tema {
	
	private int idTema;
	private String nomeTema;
	private double valorDiaria;
	

	//Construtor da lista dinamica de temas
	public Tema(int idTema, String nomeTema, double valorDiaria) {
		this.idTema = idTema;
		this.nomeTema = nomeTema;
		this.valorDiaria = valorDiaria;
	}

	//Getters and Setters
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	public String getNomeTema() {
		return nomeTema;
	}
	public void setNomeTema(String nomeTema) {
		this.nomeTema = nomeTema;
	}
	public double getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}


}
