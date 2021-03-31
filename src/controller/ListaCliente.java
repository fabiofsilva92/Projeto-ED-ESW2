package controller;

import javax.swing.JOptionPane;

public class ListaCliente {

	private NoCliente inicio;

	public ListaCliente() {
		this.inicio = null;
	}

	public void adicionaInicio(Cliente n) {
		NoCliente c = new NoCliente(n);
		c.prox = inicio;
		inicio = c;
	}

	public void adicionaFinal(Cliente n) {
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
	}

	public void adicionaPosicao(Cliente n, int pos) {

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

	public void percorrer() {

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

				aux = aux.prox;
			}

			JOptionPane.showMessageDialog(null, s.toString());
		}

	}

}
