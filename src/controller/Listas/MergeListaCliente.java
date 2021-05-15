package controller.Listas;

import javax.swing.JOptionPane;

import controller.Nos.NoCliente;

public class MergeListaCliente {

	public void criaVetor(NoCliente aux) {
		int ta = 0;
		NoCliente aux2 = aux;

		while (aux2 != null) {
			ta++;
			aux2 = aux2.prox;
		}
		System.out.println("Valor tamanho é " + ta);

		Object[] vetor = new Object[ta];
		for (int i = 0; i < ta; i++) {
			vetor[i] = (aux.cliente.getNome() + ", " + aux.cliente.getIdCliente() + ", " + aux.cliente.getEndereco()
					+ ", " + aux.cliente.getCPF() + ", " + aux.cliente.getDataNasc());

			System.out.println(vetor[i].toString());
			aux = aux.prox;
		}
//		
//		String[] id2 = lista[j].toString().split(",");
//		int id22 = Integer.parseInt(id2[0]);
//
//		String[] id1 = lista[i].toString().split(",");
//		int id11 = Integer.parseInt(id1[0]);

		int ini = 0;
		int fim = vetor.length - 1;

		mergeSort(vetor, ini, fim);

		System.out.println("---------------------------------");

		for (int i = 0; i <= fim; i++) {
			System.out.println(vetor[i] + " - ");

		}

	}

	void mergeSort(Object[] lista, int ini, int fim) {

		int pos = (ini + fim) / 2, i = ini, j = pos + 1, k = 0;
		Object[] lista_aux = new Object[fim - ini + 1];

		if (ini < fim) {
			mergeSort(lista, ini, pos);
			mergeSort(lista, pos + 1, fim);

			while (i <= pos || j <= fim) {
		
				
				if (i > pos) {
					lista_aux[k] = lista[j];
					j++;
				} else if (j > fim) {
					lista_aux[k] = lista[i];
					i++;
				} else if (lista[i].toString().toLowerCase().compareTo( lista[j].toString().toLowerCase()) > 0) {
					lista_aux[k] = lista[i];
					i++;
				} else {

					lista_aux[k] = lista[j];
					j++;
				}
				k++;
			}
			k = 0;
			for (i = ini; i <= fim; i++) {

				lista[i] = lista_aux[k];
				k++;
			}

		}

	}

}
