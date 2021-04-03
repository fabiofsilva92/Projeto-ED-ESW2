package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.time.Period;
import java.util.Date;

import javax.swing.JOptionPane;

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

		do {
			menuopc = Integer
					.parseInt(JOptionPane.showInputDialog("1 - Menu temas \n2 - Menu Clientes \n9 - Finalizar"));
			// Menu Tema
			if (menuopc == 1) {
				lt.carregarListaTema(lt); //Carrega a lista predefinida
				lt.percorrer(); //Mostra a lista
				do {
					opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Adiciona Inicio \n"
							+ "2 - Adiciona Final \n" + "3 - Escolhe posição \n" + "4 - Remove Inicio \n"
							+ "5 - Remove Final\n" + "6 - Escolhe posição para remover\n" + "7 - Exibir lista\n"
							+ "0 - Voltar Menu Anterior"));
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
					// case 8: //metodo carrega lista, envia um obejto para poder dar certo e
					// carregar em um objeto dessa classe.
					// lt.carregarListaTema(lt);
					// break;
					case 0:
						break;
					}
				} while (opc != 0);
			}
			// Menu Cliente
			if (menuopc == 2) {
				lc2.carregarListaCliente(lc2); //Carrega a lista pre definida
				lc2.percorrer(); //Mostra a lista
				do {
					opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Adiciona Inicio \n"
							+ "2 - Adiciona Final \n" + "3 - Escolhe posição \n" + "4 - Remove Inicio \n"
							+ "5 - Remove Final\n" + "6 - Escolhe posição para remover\n" + "7 - Exibir lista\n"));
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
					//case 9: // metodo carrega lista, envia um obejto para poder dar certo e carregar em um
							// objeto dessa classe.
						//lc2.carregarLista(lc2);
						//break;
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
	
	//VOU SEPARAR OS METODOS ABAIXO EM OUTRA CLASSE

	public static Tema setarTema() {

		String NomeTema = (JOptionPane.showInputDialog("Digite o nome: "));
		int IdTema = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do tema: "));
		double ValorDiaria = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da diária: "));

		Tema tema = new Tema(IdTema, NomeTema, ValorDiaria);

		return tema;
	}

	public static Cliente setarCliente() {
		int NumLocacoes = 0;
		Date hoje = new Date();
		String hojeStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(hoje);
		// System.out.println(hojeStr);

		int IdCliente = (Integer.parseInt(JOptionPane.showInputDialog("id: ")));
		String Nome = (JOptionPane.showInputDialog("Digite o nome do cliente: "));
		String Endereco = (JOptionPane.showInputDialog("Digite o Endereço: "));
		String CPF = (JOptionPane.showInputDialog("Digite o CPF: "));
		String DataNasc = (JOptionPane.showInputDialog("Digite a Data de nascimento: ")); // criar metodo para calcular
																							// a diferença da data atual
																							// com a de nascimento,
																							// somente maiores de 18
																							// poderão alugar.
		
		String DataCadastro = hojeStr;// (JOptionPane.showInputDialog("Digite a Data de Cadastro: ")); //pegar a data
										// automaticamente
		NumLocacoes++; // Esse atributo deverá ser aumentado somente no menu de agendamento, o padrão
						// deve ser 0;
		
		formatarData(hojeStr, DataNasc);

		Cliente cliente = new Cliente(IdCliente, Nome, Endereco, CPF, DataNasc, DataCadastro, NumLocacoes);

		return cliente;
	}

	public static void formatarData(String hojeStr, String DataNasc) {

		String[] auxHoje = hojeStr.split("/");
		System.out.println(auxHoje[0] + auxHoje[1] + auxHoje[2]);
		String[] auxNasc = DataNasc.split("/");
		System.out.println((auxNasc[0] + auxNasc[1] + auxNasc[2]));

		int anos = calcAnos(Integer.parseInt(auxHoje[0]),Integer.parseInt(auxHoje[1]),
				Integer.parseInt(auxHoje[2]),Integer.parseInt(auxNasc[0]),Integer.parseInt(auxNasc[1]),Integer.parseInt(auxNasc[2]));
				
	}
	
	//um metodo só de testes para o calculo da idade, desconsiderar por enquanto.
	public static int calcAnos (int diaAtual ,int mesAtual,int anoAtual,int diaNasc,int mesNasc,int anoNasc){    

		int anos, mesCont, mesTemp, diaVida;
		anos = anoAtual - anoNasc;


	    if (mesAtual<mesNasc) //Caso ainda nao for completado a quantidade de anos
	    {
	        anos = anos - 1;
	        mesCont = anos * 12;
	        if(diaAtual > diaNasc)
	        {
	            mesTemp = (12 - (mesNasc - mesAtual));
	            diaVida = diaAtual - diaNasc;
	        }
	        else
	        {
	            mesTemp = (12 - (mesNasc - mesAtual - 1));
	            diaVida = diaAtual;
	        }
	        System.out.println(anos + " anos" + (mesAtual-mesNasc) + " meses" + diaVida +" dias");
	        mesCont = mesCont + (mesTemp - 1);
	        System.out.println(mesCont+" meses de vida \n ");
	        return anos;

	    }
	    else
	    {
	        mesCont = anos * 12;
	        if(diaAtual > diaNasc)
	        {
	            diaVida = diaAtual - diaNasc;
	        }
	        else
	        {
	            diaVida = diaAtual;
	        }
	        System.out.println(anos + " anos" + (mesAtual-mesNasc) + " meses" + diaVida +" dias");
	        mesTemp = mesAtual - mesNasc;
	        mesCont = mesCont +  mesTemp;
	        System.out.println(mesCont+" meses de vida \n ");
	        return anos;
	    }


	}
}
