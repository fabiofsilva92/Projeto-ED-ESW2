import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JOptionPane;

import controller.Agendamento;

public class podeApagar {
	
	
	public static void main(String[] args) throws IOException {
		
//		
//		Date hoje = new Date();
//		System.out.println(hoje);
//		
//		Calendar hoje2 = Calendar.getInstance();
//		hoje2.add(Calendar.MONTH, 3);
////		System.out.println(hoje2);
//		System.out.println();
//		
//		int dia = hoje2.get(Calendar.DAY_OF_MONTH);
//		int mes = hoje2.get(Calendar.MONTH);
//		int ano = hoje2.get(Calendar.YEAR);
//		
//		System.out.println(dia+"/"+mes+"/" + ano);
		
//		Calendar calendar = Calendar.getInstance();
//		Calendar calendar2 = Calendar.getInstance();
//		calendar.setTime( new java.util.Date() );
//		System.out.println(calendar2.getTime());
//		calendar.add( Calendar.MONTH , 10 );
//		System.out.println( calendar.getTime() );
//		
//		if(calendar2.before(calendar)) {
//			System.out.println("AA Data correta");
//		}else {
//			System.out.println("AA Data errada");
//		}
//		
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		String data = "20-02-2021";
//		
//		try {
//			Date novaData = sdf.parse(data);
//			System.out.println(novaData);
//			if(novaData.before(hoje) ) {
//				System.out.println("Data anterior");
//			}else {
//				System.out.println("Data correta");
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		
		int dia = 30;
		int mes = 3;
		int ano= 2021;
		boolean valido = true;
		Date hoje = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = String.valueOf(dia +"/"+ mes+"/" + ano);
		Date dataAgenda;
		try {
			dataAgenda = sdf.parse(data);
			System.out.println(dataAgenda);
			
			if (dataAgenda.before(hoje)) {
				valido = false;
				JOptionPane.showMessageDialog(null,
						"ERRO, Data inserida esta incorreta " + data +" ,anterior a atual ");
			} else {				
				JOptionPane.showMessageDialog(null,
						"Data correta");
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,
					"Modelo de data incorreto");
			e.printStackTrace();
		}
				
//		
		
//		Locale locale = Locale.getDefault();
//		System.out.println(locale);
		
//		Date hoje = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("MM_yyyy");
//		
//		System.out.println(sdf.format(hoje));
		
//		Agendamento ag = new Agendamento();
//		
//		ag.ValidandoDiaEMes(28, 02);
		
//		GregorianCalendar calendar = new GregorianCalendar();
//		int diaAtual = calendar.get(GregorianCalendar.DAY_OF_MONTH);
//		int mesAtual = (calendar.get(GregorianCalendar.MONTH ) + 1);
//		
//		System.out.println(diaAtual + " mes " + (mesAtual));
		
		
	}
	
	
public static void lerListaTxt() throws IOException {
		

		File dir = new File("C:\\Users\\Usuario\\Documents\\GitHub\\Projeto-ED-ESW2");
		File arq = new File(dir, "ListaCliente.txt");
		
		FileReader ler = new FileReader(arq);
		BufferedReader buffer = new BufferedReader(ler);
		String linha = "";
		
		while((linha = buffer.readLine()) != null) {
			System.out.println(linha);
		}
		
		ler.close();
		buffer.close();
		
			
	}

}
