package controller.Listas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import controller.Tema;
import controller.Nos.NoCliente;
import controller.Nos.NoTema;

public class ListaTemas {
	
	private NoTema inicio;

	public ListaTemas() {
		this.inicio = null;
	}
	

	//Tive que criar este metodo pois os outros metodos adiciona, envia para a lista csv, 
	//podemos melhorar depois esse metodo. E apenas adicionar no final  
	public void adicionaCarregamentoCSV(Tema n) throws IOException {
		NoTema c = new NoTema(n);
		c.prox = inicio;
		inicio = c;
	}
	

	public void adicionaInicio(Tema n) throws IOException {
		NoTema c = new NoTema(n);
		c.prox = inicio;
		inicio = c;
		//criaListaTema(n);
	}

	public void adicionaFinal(Tema n) throws IOException {
		if (inicio == null) {
			NoTema c = new NoTema(n);
			inicio = c;
			c.prox = null;
			//criaListaTema(n);
		} else {
			NoTema aux = inicio;
			while (aux.prox != null) {
				aux = aux.prox;
			}
			NoTema c = new NoTema(n);
			aux.prox = c;
			c.prox = null;
			//criaListaTema(n);
		}
	}

	public void adicionaPosicao(Tema n, int pos) throws IOException {

		NoTema c = new NoTema(n);

		if (pos == 1) {
			adicionaInicio(n);
		} else {
			NoTema aux = inicio;
			int cont = 1;

			while (aux.prox != null && cont < pos - 1) {
				aux = aux.prox;
				cont++;
			}
			if (cont == pos - 1) {
				c.prox = aux.prox;
				aux.prox = c;
				//criaListaTema(n);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO, Posição Inválida!");
			}
		}
	}

	public Tema removeInicio() {
		Tema c = null;

		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			c = inicio.tema;
			inicio = inicio.prox;
		}

		return c;
	}

	public Tema removefinal() {
		Tema c = null;

		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			if (inicio.prox == null) {
				c = inicio.tema;
				inicio = null;
			} else {
				NoTema aux1 = inicio;
				NoTema aux2 = null;

				while (aux1.prox != null) {
					aux2 = aux1;
					aux1 = aux1.prox;
				}
				c = aux1.tema;
				aux2.prox = null;
			}
		}

		return c;
	}

	public Tema removePosicao(int pos) {
		Tema c = null;
		int i = 1;
		NoTema aux = inicio;

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
					NoTema aux2 = aux;

					while (pos > 1) {
						aux2 = aux;
						aux = aux.prox;
						pos--;
					}

					c = aux.tema;
					aux2.prox = aux.prox;
					return c;
				}
			}
		}
		return c;
	}

	public void percorrer() {
		
		//O arquivo será criado toda vez que percorrer, dessa forma a ordem se manterá conforme as inserções.
		String diretorio = System.getProperty("user.dir");
		diretorio = diretorio + "\\src\\Arquivos";
		File dir = new File(diretorio);
		File arquivo = new File(dir, "ListaTema.csv");
		boolean success = (arquivo).delete();
		System.out.println(success);
		
		NoTema aux = inicio;
		StringBuilder s = new StringBuilder();
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			while (aux != null) {

				s.append("ID: " + aux.tema.getIdTema() + ", Nome: " + aux.tema.getNomeTema() + ", Valor da Diária: R$"
						+ aux.tema.getValorDiaria()+" \n");
				try {
					criaListaTema(aux.tema);
				} catch (IOException e) {
					e.printStackTrace();
				}
				aux = aux.prox;
			}
			JOptionPane.showMessageDialog(null, "Lista de temas já disponíveis : \n"+s.toString());
		}
	}
	public String percorrerVerifica() {
		

		NoTema aux = inicio;
		StringBuilder s = new StringBuilder();
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			while (aux != null) {

				s.append("ID: " + aux.tema.getIdTema() + ", Nome: " + aux.tema.getNomeTema() + ", Valor da Diária: R$"
						+ aux.tema.getValorDiaria()+" \n");
				try {
					criaListaTema(aux.tema);
				} catch (IOException e) {
					e.printStackTrace();
				}
				aux = aux.prox;
			}
		}
		return s.toString();
	}
	
	// Cria um arquivo que abre no excel
	// se não houver uma diretorio  e arquivo ele vai criar automaticamente
	private void criaListaTema(Tema c) throws IOException {
		
		String userName = System.getProperty("user.name");
		//System.out.println(userName);
		String diretorio = System.getProperty("user.dir");
		diretorio = diretorio + "\\src\\Arquivos";
		//System.out.println(diretorio);
		
		File dir = new File(diretorio);
		File arq = new File(dir, "ListaTema.csv");

		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs(); // cria uma pasta se não existir, alterei mkdir para mkdirs
		}

		String conteudo = preencheListaTema(c);
		FileWriter fileWriter = new FileWriter(arq, true);
		PrintWriter print = new PrintWriter(fileWriter);
		print.write(conteudo);
		print.flush();
		print.close();
		fileWriter.close();

	}
	
	//A cada adição  de temas, é chamado o metodo criaLista que chama este, e não sobrescreve o que já existe
	private String preencheListaTema(Tema t) throws IOException {

		StringBuffer buffer = new StringBuffer();
																				// esquerda da tela
		String linha = "";
		linha = ("ID Tema:" + t.getIdTema() + ";Nome Tema:" + t.getNomeTema() + ";Valor Diaria:" + t.getValorDiaria());
		buffer.append(linha + "\r");
		
		return buffer.toString();
	}
	
	public Tema carregarListaTema(ListaTemas lt) throws IOException {
		
		//String userName = System.getProperty("user.name");
		String diretorio = System.getProperty("user.dir");
		diretorio = diretorio + "\\src\\Arquivos";
		
		Tema tema= null;
		File dir = new File(diretorio);
		File arq = new File(dir, "ListaTema.csv");

		FileReader ler = new FileReader(arq);
		BufferedReader buffer = new BufferedReader(ler);
		String linha = "";

		linha = buffer.readLine();

		while (linha != null) {
			lt.adicionaFinal(dividelinha(linha));			
			linha = buffer.readLine();
		}

		ler.close();
		buffer.close();
		
		return tema;

	}

	//Este metodo recebe uma linha de elementos, separa eles pelo ; deixando o nome e o atibuto
	//depois separa o nome e deixa apenas o atributo
	private static Tema dividelinha(String linha) throws IOException {
		
		String[] divideLinha = linha.split(";"); //Os itens das colunas vem todos na mesma linha separado pelo ;
		String[] divideAtributo;				//Dessa maneira é dividido e criado um array de elementos
		
		divideAtributo = divideLinha[0].split(":");//Apos a separacão ele vira um array, com o nome do atributo e seu valor
		int idTema = Integer.parseInt(divideAtributo[1]);//Esse segundo split deixa apenas o valor
		
		divideAtributo = divideLinha[1].split(":");
		String nomeTema = (divideAtributo[1]);
		
		divideAtributo = divideLinha[2].split(":");
		Double valorDiaria = Double.parseDouble(divideAtributo[1]);
		

		Tema tema = new Tema(idTema, nomeTema, valorDiaria);

		return tema;

	}


	public Tema conferir(int id) {

		NoTema aux = inicio;
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Tema Vázia");
			return null;
		} else {
			while (aux != null) {
				if(aux.tema.getIdTema() == id) {return aux.tema;}
				else {aux = aux.prox;}
			}
		}
		
		return null;
	}

}
