package view;

import javax.swing.JOptionPane;

import controller.Cliente;
import controller.ListaCliente;
import controller.ListaTemas;
import controller.Tema;

public class MainMenu {

		public static int idCountCliente;
		public static int idCountTema;

		public static void main(String[] args) {

			int opc = 0, pos, menuopc;

			ListaTemas lt = new ListaTemas();
			ListaCliente lc2 = new ListaCliente();
			
			do {
				menuopc = Integer.parseInt(JOptionPane.showInputDialog("1 - Aciona o menu de temas \n 2- Aciona o menu de Clientes"));
			} while(menuopc>2 || menuopc<1);
			
			
			if(menuopc == 1) {
				do {
					opc = Integer.parseInt(JOptionPane.showInputDialog("1- Adiciona Inicio \n " + "2 - Adiciona Final \n "
							+ "3 - Escolhe posição \n " + "4 - Remove Inicio \n " + "5 - Remove Final\n"
							+ "6 - Esolhe posição para remover\n" + "7 - Exibir lista;" ));

					switch (opc) {

					case 1:
						//tema = setarTema(tema2);
						//int id = Integer.parseInt(JOptionPane.showInputDialog("id : "));
						//String teminha = JOptionPane.showInputDialog("Digite o nome :");
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
						JOptionPane.showMessageDialog(null, "O elemento removido foi : " + lt.removeInicio().getNomeTema());
						break;

					case 5:
						JOptionPane.showMessageDialog(null, "O elemento removido foi : " + lt.removefinal().getNomeTema());
						break;

					case 6:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Digite uma posição para remoção"));
						JOptionPane.showMessageDialog(null, "O elemento removido foi: " + lt.removePosicao(pos).getIdTema());
						break;

					case 7:
						lt.percorrer();
						break;


					}
				} while (opc != 0);
			}
			else
			{
				do {
					opc = Integer.parseInt(JOptionPane.showInputDialog("1- Adiciona Inicio \n " + "2 - Adiciona Final \n "
							+ "3 - Escolhe posição \n " + "4 - Remove Inicio \n " + "5 - Remove Final\n"
							+ "6 - Esolhe posição para remover\n" + "7 - Exibir lista;"));

					switch (opc) {

					case 1:
						lc2.adicionaInicio(setarCliente());
						lt.percorrer();
						break;

					case 2:				
						lc2.adicionaFinal(setarCliente());
						break;

					case 3:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Informe uma posição para inserção: "));
						lc2.adicionaPosicao(setarCliente(), pos);
						break;

					case 4:
						JOptionPane.showMessageDialog(null, "O elemento removido foi : " + lc2.removeInicio().getNome());
						break;

					case 5:
						JOptionPane.showMessageDialog(null, "O elemento removido foi : " + lc2.removefinal().getNome());
						break;

					case 6:
						pos = Integer.parseInt(JOptionPane.showInputDialog("Digite uma posição para remoção"));
						JOptionPane.showMessageDialog(null, "O elemento removido foi: " + lc2.removePosicao(pos).getNome());
						break;

					case 7:
						lt.percorrer();
						break;


					}
				} while (opc != 0);

			}
			
		}

		public static Tema setarTema( ) {
			
			String NomeTema = (JOptionPane.showInputDialog("Digite o nome :"));
			int IdTema = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do tema : "));
			double ValorDiaria = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da diária: "));
			
			Tema tema = new Tema(IdTema, NomeTema, ValorDiaria);

			return tema;
		}

		public static Cliente setarCliente() {

			int IdCliente = (Integer.parseInt(JOptionPane.showInputDialog("id : ")));
			String Nome = (JOptionPane.showInputDialog("Digite o nome do cliente:"));
			String Endereco = ("");//Deixei assim para testes
			String CPF = ("");           //Deixei assim para testes
			String DataNasc = ("");      //Deixei assim para testes
			String DataCadastro = (" "); //Deixei assim para testes
			int NumLocacoes = (0);       //Deixei assim para testes

			Cliente cliente = new Cliente(IdCliente, Nome, Endereco, CPF, DataNasc, DataCadastro, NumLocacoes);
			
			return cliente;
		}

	}
