package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.Listas.ListaAgenda;
import controller.Listas.ListaTemas;

//Classe de opera��es auxiliares para a classe Agenda.

public class Agendamento {

	ListaAgenda ag = new ListaAgenda();

	public boolean Agendamento(String[] auxReserva) {

		ListaTemas lt = new ListaTemas();
		try {
			lt.carregarListaTema(lt);
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean validoRetro, validoFuturo = false;
		Date data = convertendoStringEmData(auxReserva);// converte o vetor no formato data

		boolean validoExiste = ValidandoDiaEMes(auxReserva);// Metodo valida se o dia, mes e ano s�o validos.

		if (validoExiste) {
			validoRetro = validandoDataRetro(data);
			if (validoRetro) {
				validoExiste = true;
				validoFuturo = validarAnoAFrente(data);
				if (validoFuturo) {
					validoExiste = true;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "ERRO, Data inserida esta incorreta ");
			validoExiste = false;
		}
		if (validoExiste) {
			return true;

		} else {
			return false;
		}

	}

	// Realiza agendamento
	public Agenda realizarAgendamento(String[] auxReserva, Cliente cliente, Tema tema, int agId) {
		int idAgendamento = agId;

		idAgendamento = idAgendamento + 1;
		String dataAgendamento = auxReserva[0] + "/" + auxReserva[1] + "/" + auxReserva[2];
		String nomeTema = tema.getNomeTema();
		int idCliente = cliente.getIdCliente();
		String endereco = cliente.getEndereco();
		String horaInicio = JOptionPane.showInputDialog("Digite a hora de in�cio (HH:mm): ");
		String horaFinal = JOptionPane.showInputDialog("Digite a hora de t�rmino (HH:mm): ");
		String formaPagamento = null;
		String status = "null";
		int opc = 0;
	//	do {
	//		opc = Integer
	//				.parseInt(JOptionPane.showInputDialog("Digite a op��o correspondente a forma de pagamento desejada:"
	//						+ "\n 1 - Dinheiro" + "\n 2 - Cart�o D�bito/Cr�dito"));
	//		switch (opc) {
	//		case 1:
	//			formaPagamento = "Dinheiro";
	//			break;
	//		case 2:
	//			formaPagamento = "Cart�o";
	//			break;
    //
	//		}
	//	} while (opc != 1 && opc != 2);

		Agenda locacao = new Agenda(idAgendamento, dataAgendamento, nomeTema, idCliente, endereco, horaInicio,
				horaFinal, formaPagamento, status);
		return locacao;

	}

	public Date convertendoStringEmData(String[] auxReserva) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");// Criando a mascara para a data

		String data = String.valueOf(auxReserva[0] + "/" + auxReserva[1] + "/" + auxReserva[2]);

		Date dataAgenda;
		try {
			dataAgenda = sdf.parse(data);
			return dataAgenda;

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "ERRO,formato inserido n�o � o correto");
			e.printStackTrace();// Erro se o formato da mascara vier errado
		}

		return null;
	}

	// Verifica se a data de reserva � maior do que 1 ano, conforme o requisito n�o
	// � poss�vel agendar para mais de que 365 dias
	private boolean validarAnoAFrente(Date data) {
		Boolean valido = true;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 365);
		Date dataFutura = calendar.getTime();

		if (data.after(dataFutura)) {
			JOptionPane.showMessageDialog(null, "ERRO,N�o � possivel fazer reserva para mais de 365 dias");
			valido = false;
			return valido;
		}
		return valido;

	}

	// Validando se a data n�o � anterior a atual
	private boolean validandoDataRetro(Date dataAgenda) {
		boolean valido = true;
		Date hoje = new Date();

		if (dataAgenda.before(hoje)) {
			valido = false;
			JOptionPane.showMessageDialog(null,
					"ERRO, Data inserida esta incorreta " + dataAgenda + " ,anterior a atual ");
		}
		return valido;
	}

	// Valida se m�s � menor que 12 e maior que 0, e dias de acordo com o m�s
	public boolean ValidandoDiaEMes(String[] auxReserva) {
		boolean valido = true;
		int diaMes = 31;
		int dia = Integer.parseInt(auxReserva[0]);
		int mes = Integer.parseInt(auxReserva[1]);
		int ano = Integer.parseInt(auxReserva[2]);
		if (ano >= 2021) {// Valida o ano
			if (mes > 0 && mes < 13 && dia < 32 && dia >= 1) {

				switch (mes) {
				case 2:
					diaMes -= 3;
					if (dia > diaMes) {
						valido = false;
						JOptionPane.showMessageDialog(null,
								"ERRO, Data inserida esta incorreta, o mes " + mes + " contem menos dias " + dia);
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
						"ERRO, Data inserida esta incorreta m�s = " + mes + " ou dia = " + dia + " invalido");
			}
		} else {
			valido = false;
			JOptionPane.showMessageDialog(null, "ERRO, Ano anterior ao Atual");
		}
		return valido;
	}

}
