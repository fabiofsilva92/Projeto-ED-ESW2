package controller.Listas;

import javax.swing.JOptionPane;

import controller.Nos.NoCliente;

public class MergeListaCliente {

	public void criaVetor(NoCliente aux) { //Faz a contagem do tamanho da lista encadeada, para gerar um vetor 
		int ta = 0;
		NoCliente aux2 = aux;

		while (aux2 != null) {
			ta++;
			aux2 = aux2.prox;
		}
		System.out.println("O tamanho é " + ta);

		Object[] vetor = new Object[ta];
		//Após o vetor criado cria uma String dos dados do cliente e armazena no vetor.
		for (int i = 0; i < ta; i++) {
			vetor[i] = (aux.cliente.getNome() + ", " + aux.cliente.getIdCliente() + ", " + aux.cliente.getEndereco()
					+ ", " + aux.cliente.getCPF() + ", " + aux.cliente.getDataNasc());
			aux = aux.prox;
		}
// Teste de uso Split para pegar o id, estava dentro do merge,que não funcionou 		
//		String[] id2 = lista[j].toString().split(",");
//		int id22 = Integer.parseInt(id2[0]);
		
//		String[] id1 = lista[i].toString().split(",");
//		int id11 = Integer.parseInt(id1[0]);

		int ini = 0;
		int fim = vetor.length - 1;

		mergeSort(vetor, ini, fim);

		StringBuilder s = new StringBuilder();
		//Após o merge organizar o vetor e ele retorna e armazena no StringBuilder para uma melhor apresentação.
		for (int i = 0; i <= fim; i++) {
			s.append(vetor[i].toString()+"\n");
			System.out.println(vetor[i].toString());
		}
		JOptionPane.showMessageDialog(null, "Lista de clientes cadastrados no sistema: \n" + s.toString());
	}

	void mergeSort(Object[] lista, int ini, int fim) { //Faz a chamada recursiva até organizar todo o vetor.

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
					i++;							//Vai fazer a comparação usando o compareTo  se o valor de a segunda String
													//se for menor vai retornar um numero positivo e vai adicionar na lista  o nome na posição i
													//for maior, vai retornar um numero negativo, e vai adicionar o nome na posição j
				} else if (lista[i].toString().toLowerCase().compareTo( lista[j].toString().toLowerCase()) < 0) {
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
