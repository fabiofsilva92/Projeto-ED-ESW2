package controller.Nos;

import controller.Tema;

public class NoTema {
	
	public Tema tema;
	public NoTema prox;
	
	//Construtor da lista dinamica de temas
	public NoTema(Tema tema) {
		this.tema = tema;
		this.prox = null;
	}
}
