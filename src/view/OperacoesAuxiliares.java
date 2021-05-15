package view;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import controller.Agendamento;
import controller.Cliente;
import controller.Tema;

public class OperacoesAuxiliares {
	
	Agendamento agd = new Agendamento();

	public  Tema setarTema() {

		String NomeTema = (JOptionPane.showInputDialog("Digite o nome: "));
		int IdTema = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do tema: "));
		double ValorDiaria = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da diária: "));

		Tema tema = new Tema(IdTema, NomeTema, ValorDiaria);

		return tema;
	}

	public  Cliente setarCliente() {
		Cliente cliente;
		
		int NumLocacoes = 0;
		Date hoje = new Date();
		String hojeStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(hoje);
		// System.out.println(hojeStr);

		int IdCliente = (Integer.parseInt(JOptionPane.showInputDialog("id: ")));
		String Nome = (JOptionPane.showInputDialog("Digite o nome do cliente: "));
		String Endereco = (JOptionPane.showInputDialog("Digite o Endereço: "));
		String CPF = (JOptionPane.showInputDialog("Digite o CPF: "));
		String DataNasc = (JOptionPane.showInputDialog("Digite a Data de nascimento: (dd/MM/yyyy)")); // criar metodo
										
		String [] auxNasc = formatarData(DataNasc);
		
		Date Nascimento = agd.convertendoStringEmData(auxNasc);
		
		int anos = verificaIdade(hoje, Nascimento); //Verificando se idade.
		String DataCadastro = hojeStr;
		
		if(anos >= 18) {
			boolean valido = ValidandoDiaEMes(auxNasc);
			if(valido) {
				cliente = new Cliente(IdCliente, Nome, Endereco, CPF, DataNasc, DataCadastro, NumLocacoes);
				return cliente;
			}
			else {
				System.out.println("Data de nascimento inválida de acordo com os requisitos do sistema");
				cliente = null;
				return cliente;
			}
		}
		else {
			System.out.println("Data de nascimento inválida de acordo com os requisitos do sistema");
			cliente = null;
			return cliente;
		}

		// para calcular
		// a diferença da data atual
		// com a de nascimento,
		// somente maiores de 18
		// poderão alugar.

	}
	
	public int verificaIdade(Date hoje, Date Nascimento) {
		
		long diffEmMil = Math.abs(hoje.getTime() - Nascimento.getTime());
		System.out.println(diffEmMil);
		
		long diff = TimeUnit.DAYS.convert(diffEmMil, TimeUnit.MILLISECONDS);
		int anos = Math.abs(((int)diff)/ 365);
		
		System.out.println("Diferença entre datas: " + diff + " dias e " + anos + " anos");
		return anos;
		
	}

	public  String[] formatarData(String dataReserva) {
		String[] auxReserva = dataReserva.split("/");
		System.out.println(auxReserva[0] + auxReserva[1] + auxReserva[2]);
		return auxReserva;
	}
	public boolean ValidandoDiaEMes(String [] auxReserva) {
		boolean valido = true;
		int diaMes = 31;
		int dia = Integer.parseInt(auxReserva[0]);
		int mes = Integer.parseInt(auxReserva[1]);				
		int ano = Integer.parseInt(auxReserva[2]);
		if (ano <= 2021) {// Valida o ano
			if ( mes > 0 && mes < 13 && dia < 32 && dia >= 1) { // Valida se o mes é menos que 12 ou maior que 0
																// e o dia é meno que 32 e maior que 0
				switch (mes) { // valida se o mes tem a quantidade de dias informado.
				case 2:
					diaMes -= 3;
					if (dia > diaMes) {
						valido = false;
						JOptionPane.showMessageDialog(null,
								"ERRO, Data inserida esta incorreta, o mes " + mes +" contem menos dias " + dia);
					} 
					break;
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					if (dia > diaMes) {
						valido = false;
						JOptionPane.showMessageDialog(null,
								"ERRO, Data inserida esta incorreta, mes contem menos dias " + dia);
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					diaMes--;
					if (dia > diaMes) {
						JOptionPane.showMessageDialog(null,
								"ERRO, Data inserida esta incorreta, mes contem menos dias " + dia);
						valido = false;
					} 
					break;
				}

			} else {
				valido = false;
				JOptionPane.showMessageDialog(null,
						"ERRO, Data inserida esta incorreta mês = " + mes + " ou dia = " + dia + " invalido");
			}
		} else {
			valido = false;
			JOptionPane.showMessageDialog(null, "ERRO, Ano Maior que o Atual");
		}
		return valido;
	}
}

