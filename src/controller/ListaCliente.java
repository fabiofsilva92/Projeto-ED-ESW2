package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ListaCliente {

	private NoCliente inicio;

	public ListaCliente() {
		this.inicio = null;
	}

	
	//Tive que criar este metodo pois os outros metodos de adicionas, envia para a loista csv, 
	//podemos melhorar depois esse metodo. E apenas adicionar os clientes  quando for percorrer  
	public void adicionaCarregamentoCSV(Cliente n) throws IOException {
		NoCliente c = new NoCliente(n);
		c.prox = inicio;
		inicio = c;
	}
	
	public void adicionaInicio(Cliente n) throws IOException {
		NoCliente c = new NoCliente(n);
		c.prox = inicio;
		inicio = c;
		criaListaCliente(n);
	}

	public void adicionaFinal(Cliente n) throws IOException {
		if (inicio == null) {
			NoCliente c = new NoCliente(n);
			inicio = c;
			c.prox = null;
		} else {
			NoCliente aux = inicio;
			while (aux.prox != null) {
				aux = aux.prox;
			}
			NoCliente c = new NoCliente(n);
			aux.prox = c;
			c.prox = null;
		}
		criaListaCliente(n);
	}

	public void adicionaPosicao(Cliente n, int pos) throws IOException {

		NoCliente c = new NoCliente(n);

		if (pos == 1) {
			adicionaInicio(n);
		} else {
			NoCliente aux = inicio;
			int cont = 1;

			while (aux.prox != null && cont < pos - 1) {
				aux = aux.prox;
				cont++;
			}
			if (cont == pos - 1) {
				c.prox = aux.prox;
				aux.prox = c;
				criaListaCliente(n);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO, Posição Inválida!");
			}
		}
	}

	public Cliente removeInicio() {
		Cliente c = null;

		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			c = inicio.cliente;
			inicio = inicio.prox;
		}

		return c;
	}

	public Cliente removefinal() {
		Cliente c = null;

		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			if (inicio.prox == null) {
				c = inicio.cliente;
				inicio = null;
			} else {
				NoCliente aux1 = inicio;
				NoCliente aux2 = null;

				while (aux1.prox != null) {
					aux2 = aux1;
					aux1 = aux1.prox;
				}
				c = aux1.cliente;
				aux2.prox = null;
			}
		}

		return c;
	}

	public Cliente removePosicao(int pos) {
		Cliente c = null;
		int i = 1;
		NoCliente aux = inicio;

		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			if (pos == 1) {
				c = removeInicio();
				return c;
			} else {
				while (aux.prox != null) {
					aux = aux.prox;
					i++;
				}

				if (pos > i || pos <= 0) {
					JOptionPane.showMessageDialog(null, "ERRO, Posição Invalida");
					return c;
				} else if (pos == i) {
					c = removefinal();
					return c;
				} else {
					aux = inicio;
					NoCliente aux2 = aux;

					while (pos > 1) {
						aux2 = aux;
						aux = aux.prox;
						pos--;
					}

					c = aux.cliente;
					aux2.prox = aux.prox;

					return c;
				}
			}
		}
		return c;
	}

	private void criaListaCliente(Cliente c) throws IOException {
		// Cria um arquivo que abre no bloco de notas
		// se não houver uma pasta dessas ele vai criar automaticamente

		File dir = new File("C:\\Users\\Usuario\\Documents\\GitHub\\Projeto-ED-ESW2\\");
		File arq = new File(dir, "ListaCliente.csv");

		if (dir.exists() && dir.isDirectory()) {
			JOptionPane.showMessageDialog(null, "Lista Preenchida Criada");

		} else {
			dir.mkdirs(); // cria uma pasta se não existir, alterei mkdir para mkdirs

		}

		String conteudo = preencheListaCliente(c);
		FileWriter fileWriter = new FileWriter(arq, true);
		PrintWriter print = new PrintWriter(fileWriter);
		print.write(conteudo);
		print.flush();
		print.close();
		fileWriter.close();

	}

	//preenche no arquivo csv, apenas os valores dos atributos
	private String preencheListaCliente(Cliente c) throws IOException {

		StringBuffer buffer = new StringBuffer();

		String linha = "";
		linha = (c.getIdCliente() + ";" + c.getNome() + ";" + c.getEndereco() + ";" + c.getCPF() + ";" + c.getDataNasc()
				+ ";" + c.getDataCadastro() + ";" + c.getNumLocacoes());
		buffer.append(linha + "\r");

		return buffer.toString();
	}

	public void percorrer() {

		NoCliente aux = inicio;
		StringBuilder s = new StringBuilder();
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			while (aux != null) {

				s.append("ID: " + aux.cliente.getIdCliente() + "; Nome: " + aux.cliente.getNome() + "; Endereço: "
						+ aux.cliente.getEndereco() + "; CPF: " + aux.cliente.getCPF() + ", Data nascimento: "
						+ aux.cliente.getDataNasc() + "; Data Cadastro: " + aux.cliente.getDataCadastro()
						+ "; Numero locações: " + aux.cliente.getNumLocacoes() + "\n");

				aux = aux.prox;
			}

			JOptionPane.showMessageDialog(null, s.toString());
		}

	}

	// ainda vou melhorar, nem esta sendo usado
	public void removeClienteLista(int pos) throws IOException {
		int cont = 0;
		String fileName = "ListaCliente.csv";
		BufferedReader ler = new BufferedReader(new FileReader(fileName));
		StringBuilder s = new StringBuilder();

		String linha = ler.readLine();
		NoCliente aux = inicio;

		while (linha != null) {

			if (pos != cont) {
				s.append("ID: " + aux.cliente.getIdCliente() + "; Nome: " + aux.cliente.getNome() + "; Endereço: "
						+ aux.cliente.getEndereco() + "; CPF: " + aux.cliente.getCPF() + "; Data nascimento: "
						+ aux.cliente.getDataNasc() + "; Data Cadastro: " + aux.cliente.getDataCadastro()
						+ "; Numero locações: " + aux.cliente.getNumLocacoes());
			}
			linha = ler.readLine();
			cont++;
		}

		ler.close();
		FileWriter writer = new FileWriter(fileName, true);
		writer.close();

		FileWriter writer2 = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(writer2);

		while (s != null) {
			bw.write("ID: " + aux.cliente.getIdCliente() + "; Nome: " + aux.cliente.getNome() + "; Endereço: "
					+ aux.cliente.getEndereco() + "; CPF: " + aux.cliente.getCPF() + "; Data nascimento: "
					+ aux.cliente.getDataNasc() + "; Data Cadastro: " + aux.cliente.getDataCadastro()
					+ "; Numero locações: " + aux.cliente.getNumLocacoes());

		}

	}

	
	//Carrega a lista do arquivo .csv, mas ele vem como uma lista de elementos separados por ;
	//com o metodo divide linha ele separa
	public  Cliente carregarLista(ListaCliente lc2) throws IOException {
		
		Cliente cliente = null;
		File dir = new File("C:\\Users\\Usuario\\Documents\\GitHub\\Projeto-ED-ESW2");
		File arq = new File(dir, "ListaCliente.csv");

		FileReader ler = new FileReader(arq);
		BufferedReader buffer = new BufferedReader(ler);
		String linha = "";

		linha = buffer.readLine();

		while (linha != null) {
			lc2.adicionaInicio(dividelinha(linha));
			
			linha = buffer.readLine();
		}

		ler.close();
		buffer.close();
		
		return cliente;

	}

	//Este metodo recebe uma linha de elementos, separa eles pelo ; e insere nos atributos 
	private static Cliente dividelinha(String linha) throws IOException {
		
		String[] divideLinha = linha.split(";"); //dividndo os elementos em um array
		System.out.println((divideLinha[0]));
		int IdCliente = Integer.parseInt(divideLinha[0]);
		String Nome = (divideLinha[1]);
		String Endereco = (divideLinha[2]);
		String CPF = (divideLinha[3]);
		String DataNasc = (divideLinha[4]);
		String DataCadastro = (divideLinha[5]);
		int NumLocacoes = Integer.parseInt(divideLinha[6]);

		Cliente cliente = new Cliente(IdCliente, Nome, Endereco, CPF, DataNasc, DataCadastro, NumLocacoes);

		return cliente;

	}
}
