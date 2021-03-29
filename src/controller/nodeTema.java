package controller;

public class nodeTema {
	
	public Tema tema;
	public nodeTema prox;
	
	//Construtor da lista dinamica de temas
	public nodeTema(Tema tema) {
		this.tema = tema;
		this.prox = null;
	}
}
