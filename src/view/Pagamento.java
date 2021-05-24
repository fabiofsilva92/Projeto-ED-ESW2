package view;

import javax.swing.JOptionPane;

import controller.Cliente;
import controller.Tema;

public class Pagamento {

	public boolean pagamento(int idCliente, int idTema, Cliente cliente, Tema tema) {
		Cliente c = cliente;
		Tema t = tema;
		System.out.println("Cheguei");

		JOptionPane.showMessageDialog(null, "O Pagamento de " + t.getValorDiaria() + " foi efetuado pelo cliente "
				+ c.getNome() + " o seu ID é " + c.getIdCliente() + " foi concluido com sucesso ");

		return false;
	}

}
