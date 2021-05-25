package controller.Listas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import controller.Agenda;
import controller.Tema;
import controller.Nos.NoAgenda;
import controller.Nos.NoTema;

//TRANSFORMAR PARA DUPLAMENTE ENCADEADA.

public class ListaAgenda {

	ListaAgenda la;

	private NoAgenda inicio;

	public ListaAgenda() {
		this.inicio = null;
	}

	public void adicionaInicio(Agenda n) throws IOException {
		NoAgenda c = new NoAgenda(n);
		if (inicio != null) {
			c.prox = inicio;
			inicio.anterior = c;
		}
		inicio = c;
	}

	public void adicionaFinal(Agenda n) throws IOException {
		NoAgenda c = new NoAgenda(n);
		if (inicio == null) {
			inicio = c;
			c.anterior = null;
			c.prox = null;
		} else {
			NoAgenda aux = inicio;
			while (aux.prox != null) {
				aux = aux.prox;
			}
			aux.prox = c;
			c.anterior = aux;
			c.prox = null;
		}
	}

	public void adicionaPosicao(Agenda n, int pos) throws IOException {

		NoAgenda c = new NoAgenda(n);

		if (pos == 1) {
			adicionaInicio(n);
		} else {
			NoAgenda aux = inicio;
			int cont = 1;

			while (aux.prox != null && cont < pos - 1) {
				aux = aux.prox;
				cont++;
			}
			if (cont == pos - 1) {
				c.prox = aux.prox;
				aux.prox = c;
				c.anterior = aux;
				// criaListaTema(n);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO, Posição Inválida!");
			}
		}
	}

	public Agenda removeInicio() {
		Agenda c = null;

		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			c = inicio.agendamento;
			inicio = inicio.prox;
			if (inicio != null) {
				inicio.anterior = null;
			}
		}

		return c;
	}

	public Agenda removefinal() {
		Agenda c = null;

		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			if (inicio.prox == null) {
				c = inicio.agendamento;
				inicio = null;
			} else {
				NoAgenda aux1 = inicio;
				NoAgenda aux2 = null;

				while (aux1.prox != null) {
					aux2 = aux1;
					aux1 = aux1.prox;
				}
				c = aux1.agendamento;
				aux2.prox.anterior = null;
				aux2.prox = null;
			}
		}

		return c;
	}

	public Agenda removePosicao(int pos) {
		Agenda c = null;
		int i = 1;
		NoAgenda aux = inicio;

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
					NoAgenda aux2 = aux;

					while (pos > 1) {
						aux2 = aux;
						aux = aux.prox;
						pos--;
					}

					c = aux.agendamento;
					if(aux.prox.prox !=null) {
						aux.prox.anterior = aux2;
					}
					aux2.prox = aux.prox;
					return c;
				}
			}
		}
		return c;
	}

	public String percorrer() {

		// O arquivo será criado toda vez que percorrer, dessa forma a ordem se manterá
		// conforme as inserções.
		String diretorio = System.getProperty("user.dir");
		diretorio = diretorio + "\\src\\Arquivos";
		File dir = new File(diretorio);
		File arquivo = new File(dir, "ListaAgenda.csv");
		boolean success = (arquivo).delete();
		System.out.println(success);

		NoAgenda aux = inicio;
		StringBuilder s = new StringBuilder();
		if (aux == null) {
			// JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			while (aux != null) {

				s.append("ID: " + aux.agendamento.getIdAgendamento() + ", Data: " + aux.agendamento.getDataAgendamento()
						+ ", Tema: " + aux.agendamento.getTema() + ", Cliente: " + aux.agendamento.getClienteId()
						+ ", Endereço: " + aux.agendamento.getEndereco() + ", Hora de início: "
						+ aux.agendamento.getHoraInicio() + ", Hora de término: " + aux.agendamento.getHoraFinal()
						+ ", Forma de pagamento: " + aux.agendamento.getFormaPagamento() + "\n");
				try {
					criaListaAgenda(aux.agendamento);
				} catch (IOException e) {
					e.printStackTrace();
				}
				aux = aux.prox;
			}
			return s.toString();
		}
		return s.toString();
	}
	
	public int percorrerPegarId() {
		
		NoAgenda aux = inicio;
		StringBuilder s = new StringBuilder();
		NoAgenda aux2 = aux;
		if (aux == null) {
			// JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
			return 0;
		} else {
			while (aux != null) {
				aux2 = aux;
				aux = aux.prox;
			}
			System.out.println(aux2.agendamento.getIdAgendamento());
			return aux2.agendamento.getIdAgendamento();
		}		
	}

	// Cria um arquivo que abre no exceláááÁ
	// se não houver uma diretorio e arquivo ele vai criar automaticamente
	private void criaListaAgenda(Agenda c) throws IOException {

		String userName = System.getProperty("user.name");
		// System.out.println(userName);
		String diretorio = System.getProperty("user.dir");
		diretorio = diretorio + "\\src\\Arquivos";
		// System.out.println(diretorio);

		File dir = new File(diretorio);
		File arq = new File(dir, "ListaAgenda.csv");

		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs(); // cria uma pasta se não existir, alterei mkdir para mkdirs
		}

		String conteudo = preencheListaAgenda(c);
		FileWriter fileWriter = new FileWriter(arq, true);
		PrintWriter print = new PrintWriter(fileWriter);
		print.write(conteudo);
		print.flush();
		print.close();
		fileWriter.close();

	}

	// A cada adição de temas, é chamado o metodo criaLista que chama este, e não
	// sobrescreve o que já existe
	private String preencheListaAgenda(Agenda t) throws IOException {

		StringBuffer buffer = new StringBuffer();
		// esquerda da tela
		String linha = "";
		linha = ("ID:" + t.getIdAgendamento() + "; Data:" + t.getDataAgendamento() + "; Tema:" + t.getTema()
				+ "; Cliente:" + t.getClienteId() + "; Endereço:" + t.getEndereco() + "; Hora de início:"
				+ t.getHoraInicio() + "; Hora de término:" + t.getHoraFinal() + "; Forma de pagamento:"
				+ t.getFormaPagamento());
		buffer.append(linha + "\r");

		return buffer.toString();
	}

//
	public Agenda carregarListaAgenda(ListaAgenda lt) throws IOException {

		// String userName = System.getProperty("user.name");
		String diretorio = System.getProperty("user.dir");
		diretorio = diretorio + "\\src\\Arquivos";

		Agenda agenda = null;
		File dir = new File(diretorio);
		File arq = new File(dir, "ListaAgenda.csv");

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

		return agenda;

	}

	// Este metodo recebe uma linha de elementos, separa eles pelo ; deixando o nome
	// e o atibuto
	// depois separa o nome e deixa apenas o atributo
	private static Agenda dividelinha(String linha) throws IOException {

		String[] divideLinha = linha.split(";"); // Os itens das colunas vem todos na mesma linha separado pelo ;
		String[] divideAtributo; // Dessa maneira é dividido e criado um array de elementos

		divideAtributo = divideLinha[0].split(":");// Apos a separacão ele vira um array, com o nome do atributo e seu
													// valor
		int idAgendamento = Integer.parseInt(divideAtributo[1]);// Esse segundo split deixa apenas o valor

		divideAtributo = divideLinha[1].split(":");
		String dataAgendamento = (divideAtributo[1]);

		divideAtributo = divideLinha[2].split(":");
		String temaId = (divideAtributo[1]);

		divideAtributo = divideLinha[3].split(":");
		int clienteId = Integer.parseInt(divideAtributo[1]);

		divideAtributo = divideLinha[4].split(":");
		String endereco = (divideAtributo[1]);

		divideAtributo = divideLinha[5].split(":");
		String horaInicio = (divideAtributo[1]);

		divideAtributo = divideLinha[6].split(":");
		String horaFinal = (divideAtributo[1]);

		divideAtributo = divideLinha[7].split(":");
		String formaPagamento = (divideAtributo[1]);

		Agenda agendamento = new Agenda(idAgendamento, dataAgendamento, temaId, clienteId, endereco, horaInicio,
				horaFinal, formaPagamento);

		return agendamento;

	}

	public Agenda conferir(int id) {

		NoAgenda aux = inicio;
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Tema Vázia");
			return null;
		} else {
			while (aux != null) {
				if (aux.agendamento.getIdAgendamento() == id) {
					return aux.agendamento;
				} else {
					aux = aux.prox;
				}
			}
		}

		return null;
	}
	
}
