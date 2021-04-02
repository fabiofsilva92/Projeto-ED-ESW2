import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import controller.Cliente;

public class podeApagar {
	
	
	public static void main(String[] args) throws IOException {
		
		
		lerListaTxt();
		
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
