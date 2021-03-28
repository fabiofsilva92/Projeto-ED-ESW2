package controller;

public class nodeCliente {
	


	private int idCliente; //criar uma statica autoincremental na passagem
	private String Nome;
	private String Endereco;
	private String CPF;
	private String DataNasc; //"dd/MM/yyyy"
	private String DataCadastro; //Utilizar método para pegar automaticamente.
	private int numLocacoes;
	public nodeCliente prox;

	//Construtor
	public nodeCliente(int idCliente, String nome, String endereco, String cPF, String dataNasc, String dataCadastro,
			int numLocacoes) {
		this.idCliente = idCliente;
		this.Nome = nome;
		this.Endereco = endereco;
		this.CPF = cPF;
		this.DataNasc = dataNasc;
		this.DataCadastro = dataCadastro;
		this.numLocacoes = numLocacoes;
		this.prox = null;
	}

	
	//Getters and Setters
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		this.Endereco = endereco;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		this.CPF = cPF;
	}

	public String getDataNasc() {
		return DataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.DataNasc = dataNasc;
	}

	public String getDataCadastro() {
		return DataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.DataCadastro = dataCadastro;
	}

	public int getNumLocacoes() {
		return numLocacoes;
	}

	public void setNumLocacoes(int numLocacoes) {
		this.numLocacoes = numLocacoes;
	}
	
}
