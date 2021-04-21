package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.Agendamento;
import controller.Cliente;
import controller.ListaCliente;
import controller.ListaTemas;
import controller.Tema;

public class MainMenu {

	public static int idCountCliente;
	public static int idCountTema;

	public static void main(String[] args) throws IOException {

		int opc = 0, pos, menuopc;

		ListaTemas lt = new ListaTemas();
		ListaCliente lc2 = new ListaCliente();
		Agendamento ag = new Agendamento();

		do {
			menuopc = Integer
					.parseInt(JOptionPane.showInputDialog("1 - Menu temas \n2 - Menu Clientes \n9 - Finalizar"));
			//Menu Tema
			if (menuopc == 1) {
				do {
					opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Adiciona Inicio \n"
							+ "2 - Adiciona Final \n" + "3 - Escolhe posição \n" + "4 - Remove Inicio \n"
							+ "5 - Remove Final\n" + "6 - Escolhe posição para remover\n" + "7 - Exibir lista\n"
							+ "8 - Carregar lista\n" + "9 - Agendar Reserva\n"+ "0 - Voltar Menu Anterior"));

					switch (opc) {
					case 1:
						// tema = setarTema(tema2);
						// int id = Integer.parseInt(JOptionPane.showInputDialog("id : "));
						// String teminha = JOptionPane.showInputDialog("Digite o nome :");
						lt.adicionaInicio(setarTema());
						lt.percorrer();
						break;
					case 2:
						lt.adicionaFinal(setarTema());
						lt.percorrer();
						break;
					case 3:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Informe uma posição para inserção: "));
						lt.adicionaPosicao(setarTema(), pos);
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
					case 8: //metodo carrega lista, envia um obejto para poder dar certo e carregar em um objeto dessa classe.
						lt.carregarListaTema(lt);
						break;
					case 9: //metodo Carrega a lista de datas disponiveis para o tema
						
						lt.carregarListaTema(lt);  
						int dia = Integer.parseInt(JOptionPane.showInputDialog("Informe o dia: ")); 
						int mes = Integer.parseInt(JOptionPane.showInputDialog("Informe o numero do mês: ")); 
						int ano = Integer.parseInt(JOptionPane.showInputDialog("Informe o Ano: "));
						int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do Tema: "));
						lt.carregarListaTema(lt);  
						ag.Agendamento(lt, id, dia, mes, ano);
						break;
					case 0:
						break;
					}
				} while (opc != 0);
			}
			//Menu Cliente
			if (menuopc == 2) {
				do {
					opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Adiciona Inicio \n"
							+ "2 - Adiciona Final \n" + "3 - Escolhe posição \n" + "4 - Remove Inicio \n"
							+ "5 - Remove Final\n" + "6 - Escolhe posição para remover\n" + "7 - Exibir lista\n" +
							"8 - Remove lista\n" + "9 - Carregar Lista\n"
							+ "0 - Voltar Menu Anterior"));
					switch (opc) {
					case 1:
						lc2.adicionaInicio(setarCliente());
						lc2.percorrer();
						break;
					case 2:
						lc2.adicionaFinal(setarCliente());
						break;
					case 3:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Informe uma posição para inserção: "));
						lc2.adicionaPosicao(setarCliente(), pos);
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
					case 9: //metodo carrega lista, envia um obejto para poder dar certo e carregar em um objeto dessa classe.
						lc2.carregarLista(lc2);
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

	public static Tema setarTema() {

		String NomeTema = (JOptionPane.showInputDialog("Digite o nome: "));
		int IdTema = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do tema: "));
		double ValorDiaria = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da diária: "));

		Tema tema = new Tema(IdTema, NomeTema, ValorDiaria);

		return tema;
	}

	public static Cliente setarCliente() {
		int NumLocacoes = 0;

		int IdCliente = (Integer.parseInt(JOptionPane.showInputDialog("id: ")));
		String Nome = (JOptionPane.showInputDialog("Digite o nome do cliente: "));
		String Endereco = (JOptionPane.showInputDialog("Digite o Endereço: "));
		String CPF = (JOptionPane.showInputDialog("Digite o CPF: "));
		String DataNasc = (JOptionPane.showInputDialog("Digite a Data de nascimento: "));
		String DataCadastro = (JOptionPane.showInputDialog("Digite a Data de Cadastro: "));
		NumLocacoes++;

		Cliente cliente = new Cliente(IdCliente, Nome, Endereco, CPF, DataNasc, DataCadastro, NumLocacoes);

		return cliente;
	}

	
	
}
