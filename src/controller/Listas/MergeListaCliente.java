package controller.Listas;

import controller.Cliente;
import controller.Nos.NoCliente;

public class MergeListaCliente {
	
	private NoCliente inicio;
	
	public MergeListaCliente() {
		this.inicio = null;
	}

	public NoCliente criaVetor(NoCliente aux) { // Faz a contagem do tamanho da lista encadeada, para gerar um vetor
		int listaLength = 0;
		NoCliente aux2 = aux;
		

		while (aux2 != null) {
			listaLength++;
			aux2 = aux2.prox;
		}
		aux2 = aux;
		//System.out.println("O tamanho é " + listaLength);

		Cliente[] vetorClientes = new Cliente[listaLength];
		Cliente auxCliente = null;

		for (int i = 0; i < listaLength; i++) {
			auxCliente = new Cliente(aux.cliente.getIdCliente(), aux.cliente.getNome(), aux.cliente.getEndereco(),
					aux.cliente.getCPF(), aux.cliente.getDataNasc(), aux.cliente.getDataCadastro(), aux.cliente.getNumLocacoes());
			vetorClientes[i] = auxCliente;
			aux = aux.prox;
		}

		int ini = 0;
		int fim = vetorClientes.length - 1;

		mergeSort(vetorClientes, ini, fim);

		//Reorganiza a lista apos o merge.
		for (int i = 0 ; i<listaLength; i++) {
			aux2.cliente.setIdCliente(vetorClientes[i].getIdCliente());
			aux2.cliente.setNome(vetorClientes[i].getNome());
			aux2.cliente.setEndereco(vetorClientes[i].getEndereco());
			aux2.cliente.setCPF(vetorClientes[i].getCPF());
			aux2.cliente.setDataNasc(vetorClientes[i].getDataNasc());
			aux2.cliente.setDataCadastro(vetorClientes[i].getDataCadastro());
			aux2.cliente.setNumLocacoes(vetorClientes[i].getNumLocacoes());
			adicionaFinal(aux2.cliente);
			aux2 = aux2.prox;
		}
		
		return inicio;
	}
	
	public void adicionaFinal(Cliente n){
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

	void mergeSort(Cliente[] lista, int ini, int fim) { // Faz a chamada recursiva até organizar todo o vetor.

		int pos = (ini + fim) / 2, i = ini, j = pos + 1, k = 0;
		Cliente[] lista_aux = new Cliente[fim - ini + 1];

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
				} else if (lista[i].getIdCliente() < lista[j].getIdCliente()) {
					lista_aux[k] = lista[i];
					i++;
				} else {

					lista_aux[k] = lista[j];
					j++;
				}
				k++;
			}
			k = 0;
			for (int z = ini; z <= fim; z++) {

				lista[z] = lista_aux[k];
				k++;
			}

		}

	}

}
