package controller;

public class Agenda {
	

	private String dataAgendamento;
	private int temaId;

	public Agenda(String dataAgendamento, int temaId) {
		this.dataAgendamento = dataAgendamento;
		this.temaId = temaId;
	}
	
	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public int getTemaId() {
		return temaId;
	}

	public void setTemaId(int temaId) {
		this.temaId = temaId;
	}
}
