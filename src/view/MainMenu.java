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

	public static int idCountCliente;
	public static int idCountTema;

	public static void main(String[] args) throws IOException {

		int opc = 0, pos, menuopc;
		
		Pagamento pg = new Pagamento();
		ListaTemas lt = new ListaTemas();
		ListaCliente lc2 = new ListaCliente();
		Agendamento ag = new Agendamento();
		ListaAgenda la = new ListaAgenda();
		OperacoesAuxiliares oa = new OperacoesAuxiliares(); // Classe para não encher o MainMenu

		lt.carregarListaTema(lt);// Carrega a lista predefinida
		lc2.carregarListaCliente(lc2); // Carrega a lista pre definida
		la.carregarListaAgenda(la);

		do {
			menuopc = Integer
					.parseInt(JOptionPane.showInputDialog("1 - Menu temas \n2 - Menu Clientes \n3 - Concluir Agendamento  \n9 - Finalizar"));
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

					case 8: // metodo Carrega a lista de datas disponiveis para o tema

						String dataReserva = JOptionPane.showInputDialog("Datas já agendadas : \n" + la.percorrer() + "\n Informe a data desejada (dd/MM/yyyy)");
						if(la.percorrer().contains(dataReserva)){
							JOptionPane.showMessageDialog(null, "Data indisponível");
							break;
						}
						String[] auxReserva = oa.formatarData(dataReserva);
						if(ag.Agendamento(auxReserva)) {
							int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Clientes disponíveis: \n"+lc2.percorrer(0)+
									"Digite o ID do cliente que deseja realizar o agendamento: " ));
							Cliente cliente = lc2.conferir(idCliente);
							System.out.println("Cliente selecionado: "+idCliente+ " Nome: "+cliente.getNome());
							if(cliente!= null) {
								int idTema = Integer.parseInt(JOptionPane.showInputDialog("Temas disponíveis: \n" 
									+lt.percorrer(0)+"\n Informe o ID do tema desejado: "));
								Tema tema = lt.conferir(idTema);
								if(tema != null) {
									Agenda agenda;
									agenda = ag.realizarAgendamento(auxReserva, cliente, tema);
									la.adicionaFinal(agenda);
									la.percorrer();
								}
								else {
									JOptionPane.showMessageDialog(null, "Tema inválido");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Cliente inválido");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Data inválida");
						};
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
//						lc2.removeClienteLista(pos);
						break;
					case 7:
						lc2.percorrer();
						break;
//					case 8: 
//						lc2.organizarListaMerge();
//						break;
//					case 8: // tem que continuar este metodo, estava no menu 
//						break;
					// case 9: // metodo carrega lista, envia um obejto para poder dar certo e
					// carregar em um
					// objeto dessa classe.
					// lc2.carregarLista(lc2);
					// break;
					case 9:
						lc2.organizarListaMerge();
						break;
					case 0:
						break;
					}
				} while (opc != 0);

			}
			
			if (menuopc == 3) { //Efetua o Pagamento antes faz a validação se o cliente e o tem existem
				Cliente listaCliente = null;
				Tema listaTema = null;
				
				//Confere se o Cliente existe se existir retorna a lista de clientes atuais
				int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente: ")); 
				 listaCliente = lc2.conferir(idCliente);
				if(listaCliente == null) {
					JOptionPane.showMessageDialog(null,"Cliente não existe " );
					break;
				}
				
				//Confere se o Tema existe se existir retorna a lista de Temas atuais
				int idTema = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Tema: ")); 
				listaTema = lt.conferir(idTema);
				if(listaTema == null) {
					JOptionPane.showMessageDialog(null,"Tema não existe " );
					break;
				}
				
				boolean concluir = pg.pagamento(idCliente, idTema, listaCliente, listaTema); 
				
//				if(concluir) {
//					JOptionPane.showMessageDialog(null,
//							"Pagamento Concluido para o cliente com o ID " + idCliente);
//					break;
//				}else {
//					JOptionPane.showMessageDialog(null,"Erro ao concluir o pagamento");
//					break;
//				}
			}

			if (menuopc == 9) {
				JOptionPane.showMessageDialog(null, "Finalizando");
			}

		} while (menuopc != 9);
	}

	// VOU SEPARAR OS METODOS ABAIXO EM OUTRA CLASSE

}
