package controller;

public class NoCliente {
	

	public Cliente cliente; 
	public NoCliente prox;
	
	public NoCliente(Cliente n) {
		this.cliente = n;
		prox = null;		
		
	}
}
