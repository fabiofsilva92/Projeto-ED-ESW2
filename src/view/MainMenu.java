package view;

import java.io.IOException;
import java.text.DateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.Agendamento;
import controller.Cliente;
import controller.Tema;
import controller.Listas.ListaCliente;
import controller.Listas.ListaTemas;
import controller.Listas.MergeListaCliente;
import view.OperacoesAuxiliares;

public class MainMenu {

	public static int idCountCliente;
	public static int idCountTema;

	public static void main(String[] args) throws IOException {

		int opc = 0, pos, menuopc;

		ListaTemas lt = new ListaTemas();
		ListaCliente lc2 = new ListaCliente();
		Agendamento ag = new Agendamento();
		OperacoesAuxiliares oa = new OperacoesAuxiliares(); // Classe para não encher o MainMenu

		lt.carregarListaTema(lt);// Carrega a lista predefinida
		lc2.carregarListaCliente(lc2); // Carrega a lista pre definida

		do {
			menuopc = Integer
					.parseInt(JOptionPane.showInputDialog("1 - Menu temas \n2 - Menu Clientes \n 9 - Finalizar"));
			// Menu Tema
			if (menuopc == 1) {

				lt.percorrer(); // Mostra a lista
				do {
					opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Adiciona Inicio \n"
							+ "2 - Adiciona Final \n" + "3 - Escolhe posição \n" + "4 - Remove Inicio \n"
							+ "5 - Remove Final\n" + "6 - Escolhe posição para remover\n" + "7 - Exibir lista\n"
							+ "8 - Agendar Reserva\n" + "0 - Voltar Menu Anterior"));

					switch (opc) {
					case 1:
						lt.adicionaInicio(oa.setarTema());
						lt.percorrer();
						break;
					case 2:
						lt.adicionaFinal(oa.setarTema());
						lt.percorrer();
						break;
					case 3:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Informe uma posição para inserção: "));
						lt.adicionaPosicao(oa.setarTema(), pos);
						break;
					case 4:
						JOptionPane.showMessageDialog(null,
								"O elemento removido foi : " + lt.removeInicio().getNomeTema());
						break;
					case 5:
						JOptionPane.showMessageDialog(null,
								"O elemento removido foi : " + lt.removefinal().getNomeTema());
						break;
					case 6:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Digite uma posição para remoção"));
						JOptionPane.showMessageDialog(null,
								"O elemento removido foi: " + lt.removePosicao(pos).getIdTema());
						break;
					case 7:
						lt.percorrer();
						break;

					case 8: // metodo Carrega a lista de datas disponiveis para o tema

						String dataReserva = JOptionPane.showInputDialog("Informa a data desejada (dd/MM/yyyy)");
						String[] auxReserva = oa.formatarData(dataReserva);
						ag.Agendamento(auxReserva);
						break;

					case 0:
						break;
					}
					;
				} while (opc != 0);
			}
			// Menu Cliente
			if (menuopc == 2) {

				lc2.percorrer(); // Mostra a lista
				do {
					opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Adiciona Inicio \n"
							+ "2 - Adiciona Final \n" + "3 - Escolhe posição \n" + "4 - Remove Inicio \n"
							+ "5 - Remove Final\n" + "6 - Escolhe posição para remover\n" + "7 - Exibir lista\n"
							+ "9 - TesteMerge\n" + "0 - Voltar Menu Anterior"));
					switch (opc) {
					case 1:
						Cliente clienteSetado = oa.setarCliente();
						if (clienteSetado != null) {
							lc2.adicionaInicio(clienteSetado);
							lc2.percorrer();
						} else {
							lc2.percorrer();
						}

						break;
					case 2:
						clienteSetado = oa.setarCliente();
						if (clienteSetado != null) {
							lc2.adicionaFinal(oa.setarCliente());
							lc2.percorrer();
						} else {
							lc2.percorrer();
						}
						break;
					case 3:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Informe uma posição para inserção: "));
						clienteSetado = oa.setarCliente();
						if (clienteSetado != null) {
							lc2.adicionaPosicao(oa.setarCliente(), pos);
							lc2.percorrer();
						} else {
							lc2.percorrer();
						}

						break;
					case 4:
						JOptionPane.showMessageDialog(null,
								"O elemento removido foi : " + lc2.removeInicio().getNome());
						break;
					case 5:
						JOptionPane.showMessageDialog(null, "O elemento removido foi: " + lc2.removefinal().getNome());
						break;
					case 6:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Digite uma posição para remoção: "));
						JOptionPane.showMessageDialog(null,
								"O elemento removido foi: " + lc2.removePosicao(pos).getNome());
//						lc2.removeClienteLista(pos);
						break;
					case 7:
						lc2.percorrer();
						break;
//					case 8: // tem que continuar este metodo, estava no menu 
//						break;
					// case 9: // metodo carrega lista, envia um obejto para poder dar certo e
					// carregar em um
					// objeto dessa classe.
					// lc2.carregarLista(lc2);
					// break;
					case 9:
						lc2.organizarLista();
						break;
					case 0:
						break;
					}
				} while (opc != 0);

			}

			if (menuopc == 9) {
				JOptionPane.showMessageDialog(null, "Finalizando");
			}

		} while (menuopc != 9);
	}

	// VOU SEPARAR OS METODOS ABAIXO EM OUTRA CLASSE

}
