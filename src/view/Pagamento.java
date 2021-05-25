package view;

import javax.swing.JOptionPane;

import controller.Agenda;
import controller.Cliente;
import controller.Tema;

public class Pagamento {

	public boolean pagamento(Cliente cliente, Tema tema, Agenda agendamento) {
		Cliente c = cliente;
		Tema t = tema;
		System.out.println("Cheguei");
		if(cliente.getNumLocacoes() > 40) {
			JOptionPane.showMessageDialog(null, "O Pagamento de " + (tema.getValorDiaria()*0.90) + " foi efetuado pelo cliente "
					+ cliente.getNome() + " #ID :" + cliente.getIdCliente() + ", com "+agendamento.getFormaPagamento()+" foi concluido com sucesso \n --Obteve desconto");
		}
		else {
			JOptionPane.showMessageDialog(null, "O Pagamento de " + tema.getValorDiaria() + " foi efetuado pelo cliente "
					+ cliente.getNome() + " #ID : " + cliente.getIdCliente() + ", com "+agendamento.getFormaPagamento()+" foi concluido com sucesso ");
		}


		return false;
	}

}
