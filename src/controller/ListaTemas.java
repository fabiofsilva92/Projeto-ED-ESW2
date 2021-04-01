package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ListaTemas {
	
	private NoTema inicio;

	public ListaTemas() {
		this.inicio = null;
	}

	public void adicionaInicio(Tema n) throws IOException {
		NoTema c = new NoTema(n);
		c.prox = inicio;
		inicio = c;
		criaListaTema(n);
	}

	public void adicionaFinal(Tema n) throws IOException {
		if (inicio == null) {
			NoTema c = new NoTema(n);
			inicio = c;
			c.prox = null;
			criaListaTema(n);
		} else {
			NoTema aux = inicio;
			while (aux.prox != null) {
				aux = aux.prox;
			}
			NoTema c = new NoTema(n);
			aux.prox = c;
			c.prox = null;
			criaListaTema(n);
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
				criaListaTema(n);
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

		NoTema aux = inicio;
		StringBuilder s = new StringBuilder();
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "ERRO, Lista Vázia");
		} else {
			while (aux != null) {

				s.append("ID: " + aux.tema.getIdTema() + ", Nome: " + aux.tema.getNomeTema() + ", Valor da Diária: R$"
						+ aux.tema.getValorDiaria()+" \n");

				aux = aux.prox;
			}

			JOptionPane.showMessageDialog(null, s.toString());
		}
	}
	
	private void criaListaTema(Tema t) throws IOException {
		// Cria  um arquivo que abre no bloco de notas 
		// se não houver uma pasta dessas ele vai criar  automaticamente
		
		File dir = new File("C:\\Users\\Usuario\\Documents\\GitHub\\Projeto-ED-ESW2");
		File arq = new File(dir, "ListaCliente.txt");
		int i = -1;

		if (dir.exists() && dir.isDirectory()) {
			JOptionPane.showMessageDialog(null, "Lista Preenchida Criada");
		} else {
			dir.mkdirs(); // cria uma pastase não existir, alterei mkdir para mkdirs
			JOptionPane.showMessageDialog(null, "Lista Criada");
		}

		String conteudo = preencheListaTema(t);
		FileWriter fileWriter = new FileWriter(arq);
		PrintWriter print = new PrintWriter(fileWriter);
		print.write(conteudo);
		print.flush();
		print.close();
		fileWriter.close();

	}

	private String preencheListaTema(Tema t) throws IOException {

		StringBuffer buffer = new StringBuffer();
		String fileName = "ListaCliente.txt";
		BufferedWriter gravar = new BufferedWriter(new FileWriter(fileName)); // para gravar em um arquivo que aparece a
																				// esquerda da tela
		String linha = "";
		linha = ("Clientes");
		buffer.append(linha + "\n\r"); // vai adicionar as informações no arquivo.txt
		gravar.write(linha);
		gravar.newLine();
		linha = ("ID Tema: " + t.getIdTema() + "Nome Tema: " + t.getNomeTema() + "Valor Diaria: " + t.getValorDiaria());
		buffer.append(linha + "\n\r");
		gravar.write(linha);
		gravar.newLine();
		gravar.close();

		return buffer.toString();
	}

}
