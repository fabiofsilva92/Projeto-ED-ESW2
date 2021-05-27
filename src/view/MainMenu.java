package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.Agenda;
import controller.Agendamento;
import controller.Cliente;
import controller.Tema;
import controller.Listas.ListaAgenda;
import controller.Listas.ListaCliente;
import controller.Listas.ListaTemas;
import controller.Nos.NoCliente;
import controller.Nos.NoTema;

public class MainMenu {

	public static void main(String[] args) throws IOException {

		int opc = 0, pos, menuopc;

		Pagamento pg = new Pagamento();
		ListaTemas lt = new ListaTemas();
		ListaCliente lc2 = new ListaCliente();
		Agendamento ag = new Agendamento();
		ListaAgenda la = new ListaAgenda();
		OperacoesAuxiliares oa = new OperacoesAuxiliares();

		lt.carregarListaTema(lt);// Carrega a lista predefinida de temas
		lc2.carregarListaCliente(lc2); // Carrega a lista predefinida de clientes
		la.carregarListaAgenda(la); // Carrega a lista predefinida de agendamentos

		do {

			menuopc = Integer.parseInt(JOptionPane
					.showInputDialog("1 - Menu temas \n2 - Menu Clientes \n3 - Concluir Agendamento  \n9 - Finalizar"));
			// Menu Tema
			if (menuopc == 1) {

				lt.percorrer(); // Mostra a lista
				do {

					opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Adiciona Inicio \n"
							+ "2 - Adiciona Final \n" + "3 - Escolhe posição \n" + "4 - Remove Inicio \n"
							+ "5 - Remove Final \n" + "6 - Escolhe posição para remover \n" + "7 - Exibir lista \n"
							+ "8 - Agendar Reserva \n" + "0 - Voltar Menu Anterior"));

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

					case 8: // Carrega a lista de datas já agendadas e realiza novos agendamentos.

						String dataReserva = JOptionPane.showInputDialog(
								"Datas já agendadas : \n" + la.percorrer() + "\n Informe a data desejada (dd/MM/yyyy)");
						if (la.percorrer().contains(dataReserva)) {
							JOptionPane.showMessageDialog(null, "Data indisponível");
							break;
						}
						String[] auxReserva = oa.formatarData(dataReserva);
						if (ag.Agendamento(auxReserva)) {
							int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Clientes disponíveis: \n"
									+ lc2.percorrer(0) + "Digite o ID do cliente que deseja realizar o agendamento: "));
							Cliente cliente = lc2.conferir(idCliente);

							if (cliente != null) {
								System.out.println("Cliente selecionado: " + idCliente + " Nome: " + cliente.getNome());
								int idTema = Integer.parseInt(JOptionPane.showInputDialog("Temas disponíveis: \n"
										+ lt.percorrer(0) + "\n Informe o ID do tema desejado: "));
								Tema tema = lt.conferir(idTema);
								if (tema != null) {
									Agenda agenda;
									agenda = ag.realizarAgendamento(auxReserva, cliente, tema, la.percorrerPegarId());
									la.adicionaFinal(agenda);
									la.percorrer();
								} else {
									JOptionPane.showMessageDialog(null, "Tema inválido");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Cliente inválido");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Data inválida");
						}
						;
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
							+ "8 - TesteMerge\n" + "0 - Voltar Menu Anterior"));
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
							lc2.adicionaFinal(clienteSetado);
							lc2.percorrer();
						} else {
							lc2.percorrer();
						}
						break;
					case 3:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Informe uma posição para inserção: "));
						clienteSetado = oa.setarCliente();
						if (clienteSetado != null) {
							lc2.adicionaPosicao(clienteSetado, pos);
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
						break;
					case 7:
						lc2.percorrer();
						break;
					case 8:
						lc2.organizarListaMerge();
						break;
					case 0:
						break;
					}
				} while (opc != 0);

			}

			if (menuopc == 3) { // Efetua o pagamento do agendamento previamente realizado nos menus de temas.
				do {
					Cliente listaCliente = null;
					Tema listaTema = null;
					System.out.println(la.percorrer());

					int idAgendamento;

					String id = JOptionPane.showInputDialog("Agendamentos : \n" + la.percorrer()
							+ "\n Digite o id do agendamento para realizar o pagamento: ");
					if (id == null) {
						JOptionPane.showMessageDialog(null, "Id Inválido");
						break;
					}
					try {
						idAgendamento = Integer.parseInt(id);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Id Inválido2");
						break;
					}

					System.out.println(idAgendamento);
					Agenda agendamento = la.conferir(idAgendamento);
					if (agendamento == null) {
						JOptionPane.showMessageDialog(null, "Agendamento não existe ");
						break;
					}
					if (agendamento.getStatus().contains("Pago")) {
						JOptionPane.showMessageDialog(null, "Agendamento já foi pago");
						break;
					}
					// Confere se o Cliente existe se existir retorna a lista de clientes atuais
					int idCliente = agendamento.getClienteId();
					System.out.println(idCliente);
					listaCliente = lc2.conferir(idCliente);
					if (listaCliente == null) {
						JOptionPane.showMessageDialog(null, "Cliente não existe ");
						break;
					}

					// Confere se o Tema existe se existir retorna a lista de Temas atuais
					String Tema = agendamento.getTema();
					listaTema = lt.conferir(Tema);
					if (listaTema == null) {
						JOptionPane.showMessageDialog(null, "Tema não existe ");
						break;
					}

					boolean concluir = pg.pagamento(listaCliente, listaTema, agendamento);

					if (concluir) {
						agendamento.setStatus("Pago");
						System.out.println(la.percorrer());
					}

					opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Continuar \n 0 - Voltar"));
				} while (opc != 0);
			}

			if (menuopc == 9) {
				JOptionPane.showMessageDialog(null, "Finalizando");
			}

		} while (menuopc != 9);
	}
}
