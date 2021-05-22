package controller;

public class Agenda {

	private int idAgendamento;


	private String dataAgendamento;
	private int temaId;
	private String endereco;
	private String dataFesta;
	private String horaInicio;
	private String horaFinal;
	private String formaPagamento;

	public Agenda(int idAgendamento, String dataAgendamento, int temaId, String endereco, String dataFesta, String horaInicio,
			String horaFinal, String formaPagamento) {
		this.idAgendamento = idAgendamento;
		this.dataAgendamento = dataAgendamento;
		this.temaId = temaId;
		this.endereco = endereco;
		this.dataFesta = dataFesta;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.formaPagamento = formaPagamento;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataFesta() {
		return dataFesta;
	}

	public void setDataFesta(String dataFesta) {
		this.dataFesta = dataFesta;
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
}
