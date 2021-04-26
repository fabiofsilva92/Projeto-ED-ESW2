package view;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.Cliente;
import controller.Tema;

public class OperacoesAuxiliares {

	public  Tema setarTema() {

		String NomeTema = (JOptionPane.showInputDialog("Digite o nome: "));
		int IdTema = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do tema: "));
		double ValorDiaria = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da diária: "));

		Tema tema = new Tema(IdTema, NomeTema, ValorDiaria);

		return tema;
	}

	public  Cliente setarCliente() {
		int NumLocacoes = 0;
		Date hoje = new Date();
		String hojeStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(hoje);
		// System.out.println(hojeStr);

		int IdCliente = (Integer.parseInt(JOptionPane.showInputDialog("id: ")));
		String Nome = (JOptionPane.showInputDialog("Digite o nome do cliente: "));
		String Endereco = (JOptionPane.showInputDialog("Digite o Endereço: "));
		String CPF = (JOptionPane.showInputDialog("Digite o CPF: "));
		String DataNasc = (JOptionPane.showInputDialog("Digite a Data de nascimento: (dd/MM/yyyy)")); // criar metodo
																										// para calcular
		// a diferença da data atual
		// com a de nascimento,
		// somente maiores de 18
		// poderão alugar.

		String DataCadastro = hojeStr;// (JOptionPane.showInputDialog("Digite a Data de Cadastro: ")); //pegar a data
										// automaticamente
		formatarData(hojeStr, DataNasc);

		Cliente cliente = new Cliente(IdCliente, Nome, Endereco, CPF, DataNasc, DataCadastro, NumLocacoes);

		return cliente;
	}

	public  String[] formatarData(String dataReserva) {
		String[] auxReserva = dataReserva.split("/");
		System.out.println(auxReserva[0] + auxReserva[1] + auxReserva[2]);
		return auxReserva;
	}

	// Calculo da idade

	public  void formatarData(String hojeStr, String DataNasc) {

		String[] auxHoje = hojeStr.split("/");
		System.out.println(auxHoje[0] + auxHoje[1] + auxHoje[2]);
		String[] auxNasc = DataNasc.split("/");
		System.out.println((auxNasc[0] + auxNasc[1] + auxNasc[2]));

		int anos = calcAnos(Integer.parseInt(auxHoje[0]), Integer.parseInt(auxHoje[1]), Integer.parseInt(auxHoje[2]),
				Integer.parseInt(auxNasc[0]), Integer.parseInt(auxNasc[1]), Integer.parseInt(auxNasc[2]));

	}

	// um metodo só de testes para o calculo da idade, desconsiderar por enquanto.
	// Conclui esta sendo dividido por 12 para vir o ano correto, se deixar em ano
	// vem a idade errada antes de fazer o aniversario
	public  int calcAnos(int diaAtual, int mesAtual, int anoAtual, int diaNasc, int mesNasc, int anoNasc) {
		Calendar calendar = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.set(anoNasc, mesNasc, diaNasc);

		int meses = (calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH))
				- (b.get(Calendar.YEAR) * 12 + b.get(Calendar.MONTH));

		int anos = (meses / 12);

		return anos;

//		int anos, mesCont, mesTemp, diaVida;
//		anos = anoAtual - anoNasc;
//
//
//	    if (mesAtual<mesNasc) //Caso ainda nao for completado a quantidade de anos
//	    {
//	        anos = anos - 1;
//	        mesCont = anos * 12;
//	        if(diaAtual > diaNasc)
//	        {
//	            mesTemp = (12 - (mesNasc - mesAtual));
//	            diaVida = diaAtual - diaNasc;
//	        }
//	        else
//	        {
//	            mesTemp = (12 - (mesNasc - mesAtual - 1));
//	            diaVida = diaAtual;
//	        }
//	        System.out.println(anos + " anos" + (mesAtual-mesNasc) + " meses" + diaVida +" dias");
//	        mesCont = mesCont + (mesTemp - 1);
//	        System.out.println(mesCont+" meses de vida \n ");
//	        return anos;
//
//	    }
//	    else
//	    {
//	        mesCont = anos * 12;
//	        if(diaAtual > diaNasc)
//	        {
//	            diaVida = diaAtual - diaNasc;
//	        }
//	        else
//	        {
//	            diaVida = diaAtual;
//	        }
//	        System.out.println(anos + " anos" + (mesAtual-mesNasc) + " meses" + diaVida +" dias");
//	        mesTemp = mesAtual - mesNasc;
//	        mesCont = mesCont +  mesTemp;
//	        System.out.println(mesCont+" meses de vida \n ");
//	        return anos;
//	    }

	}
}
