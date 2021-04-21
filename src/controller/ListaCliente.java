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

public class ListaCliente  {

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
		//criaListaCliente(n);
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
		//criaListaCliente(n);
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
				//criaListaCliente(n);
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
	
		
	//Percorre a lista e exibe na tela 
	public void percorrer() {

		
		File arquivo = new File("ListaCliente.csv");
		boolean success = (arquivo).delete();
		System.out.println(success);
		
		NoCliente aux = inicio;
		StringBuilder s = new StringBuilder();
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			while (aux != null) {

				s.append("ID: " + aux.cliente.getIdCliente() + ", Nome: " + aux.cliente.getNome() + ", Endereço: "
						+ aux.cliente.getEndereco() + ", CPF: " + aux.cliente.getCPF() + ", Data nascimento: "
						+ aux.cliente.getDataNasc() + ", Data Cadastro: " + aux.cliente.getDataCadastro()
						+ ", Numero locações: " + aux.cliente.getNumLocacoes() + "\n");
				
				try {
					criarListaCliente(aux.cliente);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				aux = aux.prox;
			}

			JOptionPane.showMessageDialog(null, "Lista de clientes cadastrados no sistema: \n" +s.toString());
		}

	}
	
	// Cria um arquivo que abre no excel
	// se não houver uma diretorio  e arquivo ele vai criar automaticamente
	private void criarListaCliente(Cliente c) throws IOException {
		
		String userName = System.getProperty("user.name");
		//System.out.println(userName);
		String diretorio = System.getProperty("user.dir");
		//System.out.println(diretorio);
		
		File dir = new File(diretorio);
		File arq = new File(dir, "ListaCliente.csv");

		if (!dir.exists() && !dir.isDirectory()) {
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

	
	
	//A cada adição  de temas, é chamado o metodo criaLista que chama este, e não sobrescreve o que já existe
		private String preencheListaCliente(Cliente c) throws IOException {

			StringBuffer buffer = new StringBuffer();

			String linha = "";
			linha = ("ID:" + c.getIdCliente() + ";Nome:" + c.getNome() + ";Endereço:" + c.getEndereco() + ";CPF:" + c.getCPF() + ";Data nascimento:" + c.getDataNasc()
					+ ";Data Cadastro:" + c.getDataCadastro() + ";Numero locações:" + c.getNumLocacoes());
			buffer.append(linha + "\r");

			return buffer.toString();
		}


	//Carrega o arquivo e vai lendo linha por linha
	//A cada linha ele chama o metodo divideLinha para fazer o tratamento
	public  Cliente carregarListaCliente(ListaCliente lc2) throws IOException {
		
		String diretorio = System.getProperty("user.dir");
		
		Cliente cliente = null;
		File dir = new File(diretorio);
		File arq = new File(dir, "ListaCliente.csv");

		FileReader ler = new FileReader(arq);
		BufferedReader buffer = new BufferedReader(ler);
		String linha = "";

		linha = buffer.readLine();

		while (linha != null) {
			lc2.adicionaCarregamentoCSV(dividelinha(linha));			
			linha = buffer.readLine();
		}

		ler.close();
		buffer.close();
		
		return cliente;

	}

	//Este metodo recebe uma linha de elementos, separa eles pelo ; deixando o nome e o atibuto
	//depois separa o nome e deixa apenas o atributo
	private static Cliente dividelinha(String linha) throws IOException {
		
		String[] divideLinha = linha.split(";"); //Os itens das colunas vem todos na mesma linha separado pelo ;
		String[] divideAtributo;				//Dessa maneira é dividido e criado um array de elementos
		
		divideAtributo = divideLinha[0].split(":");//Apos a separacão ele vira um array, com o nome do atributo e seu valor
		int IdCliente = Integer.parseInt(divideAtributo[1]);//Esse segundo split deixa apenas o valor
		
		divideAtributo = divideLinha[1].split(":");
		String Nome = (divideAtributo[1]);
		
		divideAtributo = divideLinha[2].split(":");
		String Endereco = (divideAtributo[1]);
		
		divideAtributo = divideLinha[3].split(":");
		String CPF = (divideAtributo[1]);
		
		divideAtributo = divideLinha[4].split(":");
		String DataNasc = (divideAtributo[1]);
		
		divideAtributo = divideLinha[5].split(":");
		String DataCadastro = (divideAtributo[1]);
		
		divideAtributo = divideLinha[6].split(":");
		int NumLocacoes = Integer.parseInt(divideAtributo[1]);

		Cliente cliente = new Cliente(IdCliente, Nome, Endereco, CPF, DataNasc, DataCadastro, NumLocacoes);

		return cliente;

	}


		
//	// ainda vou melhorar, não esta sendo usado
//	public void removeClienteLista(int pos) throws IOException {
//		int cont = 0;
//		String fileName = "ListaCliente.csv";
//		BufferedReader ler = new BufferedReader(new FileReader(fileName));
//		StringBuilder s = new StringBuilder();
//
//		String linha = ler.readLine();
//		NoCliente aux = inicio;
//
//		while (linha != null) {
//
//			if (pos != cont) {
//				s.append("ID:" + aux.cliente.getIdCliente() + ";Nome:" + aux.cliente.getNome() + ";Endereço:"
//						+ aux.cliente.getEndereco() + ";CPF:" + aux.cliente.getCPF() + ";Data nascimento:"
//						+ aux.cliente.getDataNasc() + "; Data Cadastro: " + aux.cliente.getDataCadastro()
//						+ ";Numero locações:" + aux.cliente.getNumLocacoes());
//			}
//			linha = ler.readLine();
//			cont++;
//		}
//
//		ler.close();
//		FileWriter writer = new FileWriter(fileName, true);
//		writer.close();
//
//		FileWriter writer2 = new FileWriter(fileName, true);
//		BufferedWriter bw = new BufferedWriter(writer2);
//
//		while (s != null) {
//			bw.write("ID:" + aux.cliente.getIdCliente() + ";Nome:" + aux.cliente.getNome() + ";Endereço:"
//					+ aux.cliente.getEndereco() + ";CPF:" + aux.cliente.getCPF() + ";Data nascimento:"
//					+ aux.cliente.getDataNasc() + ";Data Cadastro:" + aux.cliente.getDataCadastro()
//					+ ";Numero locações:" + aux.cliente.getNumLocacoes());
//
//		}
//
//	}

}
