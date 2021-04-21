package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class Agendamento {

	public void Agendamento(ListaTemas lt, int id, int dia, int mes, int ano) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		boolean validoExiste = ValidandoDiaEMes(dia, mes,ano);//Metodo valida se o dia, mes e ano são validos, falta validação do ano.
		if (validoExiste) {
			boolean validoRetro = validandoDataRetro(dia, mes, ano);
		}else {
			JOptionPane.showMessageDialog(null, "ERRO, Data inserida esta incorreta ");
		}


	}

	private boolean validandoDataRetro(int dia, int mes, int ano) { //Validando se a data não é anterior a atual
		boolean valido = true;
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//Criando a mascara para a data
		String data = String.valueOf(dia +"/"+ mes+"/" + ano);//Convertendo para sttring para depois converter para Date
		Date dataAgenda;
		try {//Para converter para  o tipo date é necessario o try/catch
			dataAgenda = sdf.parse(data);
			
			if (dataAgenda.before(hoje)) {//Validando se a data é anterior a data atual
				valido = false; //Sefor invalido retorna falso 
				JOptionPane.showMessageDialog(null,
						"ERRO, Data inserida esta incorreta " + data +" ,anterior a atual ");
				valido = false;
			} else {//Cso seja correta continua a aplicação
				JOptionPane.showMessageDialog(null,
						"Data correta");
			}
		} catch (ParseException e) {
			e.printStackTrace();//Erro se o formato da mascara vier errado
			valido = false;
		}
					
		return valido;
	}

	public boolean ValidandoDiaEMes(int dia, int mes, int ano) {
		boolean valido = true;
		int diaMes = 31;
		if(ano >= 2021) {//Valida o ano
		if (1 < mes && mes < 13 && dia < 32 && dia > 1) { //Valida se o mes é menos que 12 ou maior que 0 
														// e o dia é meno que 32 e maior que 0
			switch (mes) { //valida se o mes tem a quantidade de dias informado.
			case 2:
				diaMes -= 3;
				if (dia > diaMes) {
					valido = false;
					JOptionPane.showMessageDialog(null,
							"ERRO, Data inserida esta incorreta, mes contem menos dias " + dia);
				} else {
					JOptionPane.showMessageDialog(null, "Data correta fevereiro " + dia);
				}
				break;
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				if (dia > diaMes) {
					valido = false;
					JOptionPane.showMessageDialog(null,
							"ERRO, Data inserida esta incorreta, mes contem menos dias " + dia);
				} else {
					valido = false;
					JOptionPane.showMessageDialog(null, "Data correta " + dia);
				}
				break;
			case 4:	case 6:	case 9:	case 11:
				diaMes--;
				if (dia > diaMes) {
					JOptionPane.showMessageDialog(null,
							"ERRO, Data inserida esta incorreta, mes contem menos dias " + dia);
					valido = false;
				} else {
					JOptionPane.showMessageDialog(null, "Data correta " + dia);
				}
				break;
			}

		} else {
			valido = false;
			JOptionPane.showMessageDialog(null,
					"ERRO, Data inserida esta incorreta mês = " + mes + " ou dia = " + dia + " invalido");
		}
	}else {
		valido = false;
		JOptionPane.showMessageDialog(null, "ERRO, Ano anterior" + valido);
	}
		JOptionPane.showMessageDialog(null, valido);
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
