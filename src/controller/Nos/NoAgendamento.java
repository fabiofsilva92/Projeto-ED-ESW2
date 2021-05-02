package controller.Nos;

import controller.Agenda;

public class NoAgendamento {
	
	public Agenda agendamento;
	public NoAgendamento prox;
	public NoAgendamento anterior;
	
	public NoAgendamento(Agenda agendamento) {
		super();
		this.agendamento = agendamento;
		this.prox = null;
		this.anterior = null;
	}

	
}
