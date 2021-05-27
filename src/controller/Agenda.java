package controller;

public class Agenda {

	private int idAgendamento;

	private String dataAgendamento;
	private String tema;
	private int clienteId;
	private String endereco;
	private String horaInicio;
	private String horaFinal;
	private String formaPagamento;
	private String status;



	public Agenda(int idAgendamento, String dataAgendamento, String tema, int clienteId, String endereco,
			String horaInicio, String horaFinal, String formaPagamento, String status) {
		this.idAgendamento = idAgendamento;
		this.dataAgendamento = dataAgendamento;
		this.tema = tema;
		this.clienteId = clienteId;
		this.endereco = endereco;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.formaPagamento = formaPagamento;
		this.status = status;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getTema() {
		return tema;
	}

	public void setTemaId(String tema) {
		this.tema = tema;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public int getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(int idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
