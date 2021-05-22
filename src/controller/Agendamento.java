package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.Listas.ListaTemas;

public class Agendamento {

	public void Agendamento(String [] auxReserva) {
		
		ListaTemas lt = new ListaTemas();
		try {
			lt.carregarListaTema(lt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean validoRetro, validoFuturo = false;
		Date data = convertendoStringEmData(auxReserva);//converte o vetor no formato data
		
		boolean validoExiste = ValidandoDiaEMes(auxReserva);// Metodo valida se o dia, mes e ano são validos, falta
																// validação do ano.
		if (validoExiste) {
				validoRetro = validandoDataRetro(data);
				if(validoRetro) {
					validoExiste = true;
					validoFuturo = validarAnoAFrente(data);
					if(validoFuturo) {
						validoExiste = true;
					}
				}
		} else {
			JOptionPane.showMessageDialog(null, "ERRO, Data inserida esta incorreta ");
			validoExiste = false;
		}
		
		//se a data for valida para todos os requisitos, entraremos no metodo para agendar na lista duplamente encadeada Agenda.
		if(validoExiste) {
			
			int temaId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do tema em que deseja verificar a disponibilidade. \n" +lt.percorrerVerifica() ));
		}
		
		//Continuar chamando um metodo para adicionar a data em uma lista.
		
	}

	public Date convertendoStringEmData(String[] auxReserva) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");// Criando a mascara para a data
		String data = String.valueOf(auxReserva[0] + "/" + auxReserva[1] + "/" + auxReserva[2]);// Convertendo para sttring para depois converter para
																	// Date
		Date dataAgenda;
		try {// Para converter para o tipo date é necessario o try/catch
			dataAgenda = sdf.parse(data);
			return dataAgenda;
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "ERRO,formato inserido não é o correto");
			e.printStackTrace();// Erro se o formato da mascara vier errado
		}
		
		return null;
	}

	private boolean validarAnoAFrente(Date data) {//Verifica se a data de reserva é maior do que 60 dias
		Boolean valido = true;
		Calendar calendar = Calendar.getInstance();
		calendar.add( Calendar.DAY_OF_MONTH , 365 );
		Date dataFutura = calendar.getTime();
		
		if(data.after(dataFutura)) {
			JOptionPane.showMessageDialog(null, "ERRO,Não é possivel fazer reserva para mais de 365 dias");
			valido = false;
			return valido;
		}
		return valido;
		
	}

	private boolean validandoDataRetro(Date dataAgenda) { // Validando se a data não é anterior a atual
		boolean valido = true;
		Date hoje = new Date();
		
		if (dataAgenda.before(hoje)) {// Validando se a data é anterior a data atual
			valido = false; // Se for invalido retorna falso
			JOptionPane.showMessageDialog(null,"ERRO, Data inserida esta incorreta " + dataAgenda + " ,anterior a atual ");
		}
		return valido;
	}

	public boolean ValidandoDiaEMes(String [] auxReserva) {
		boolean valido = true;
		int diaMes = 31;
		int dia = Integer.parseInt(auxReserva[0]);
		int mes = Integer.parseInt(auxReserva[1]);				
		int ano = Integer.parseInt(auxReserva[2]);
		if (ano >= 2021) {// Valida o ano
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
			JOptionPane.showMessageDialog(null, "ERRO, Ano anterior ao Atual");
		}
		return valido;
	}

	
//	public void criaListaMes(Cliente c, Tema t, int n) throws IOException {
//
//		File dir = new File("C:\\Users\\Usuario\\Documents\\GitHub\\Projeto-ED-ESW2\\");
//		File arq = new File(dir, t.getNomeTema() + "_2021.csv");
//
//		if (dir.exists() && dir.isDirectory()) {
//			JOptionPane.showMessageDialog(null, "Lista do mes " + t.getNomeTema() + " Criada");
//		} else {
//			dir.mkdirs(); // cria uma pasta se não existir, alterei mkdir para mkdirs
//		}
//
//		String conteudo = preencheListaAgendamento(c, t, n);
//		FileWriter fileWriter = new FileWriter(arq, true);
//		PrintWriter print = new PrintWriter(fileWriter);
//		print.write(conteudo);
//		print.flush();
//		print.close();
//		fileWriter.close();
//	}
//
//	private String preencheListaAgendamento(Cliente c, Tema t, int n) throws IOException {
//
//		Date hoje = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("MM_yyyy");
//		sdf.format(hoje);
//
//		StringBuffer buffer = new StringBuffer();
//		// esquerda da tela
//		String linha = "";
//		linha = ("Status: Alugado" + ";ID:" + c.getIdCliente() + ";Nome:" + c.getNome() + ";Endereço:" + c.getEndereco()
//				+ ";CPF:" + c.getCPF() + ";Data nascimento:" + c.getDataNasc() + "ID Tema:" + t.getIdTema()
//				+ ";Nome Tema:" + t.getNomeTema());
//		buffer.append(linha + "\r");
//
//		return buffer.toString();
//	}

}
