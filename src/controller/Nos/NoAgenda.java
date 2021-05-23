package controller.Nos;

import controller.Agenda;

public class NoAgenda {
	
	public Agenda agendamento;
	public NoAgenda prox;
	public NoAgenda anterior;
	
	public NoAgenda(Agenda agendamento) {
		this.agendamento = agendamento;
		this.prox = null;
		this.anterior = null;
	}

	
}
