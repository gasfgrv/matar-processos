package gusto.fatec.processos.view;

import javax.swing.JOptionPane;

import gusto.fatec.processos.controller.ProcessosController;

public class Main {
	public static void main(String[] args) {
		ProcessosController pc = new ProcessosController();
		pc.listarProcessos(pc.getOS());
		String opc = JOptionPane.showInputDialog("1 - Matar Processo (PID)\n2 - Matar Processo (nome)\n9 - Sair");
		while (!opc.equals("9")) {
			switch (opc) {
			case "1":
				String pid = JOptionPane.showInputDialog("PID");
				while (pc.isNumeric(pid) == false) {
					pid = JOptionPane.showInputDialog("PID inválido, digite novamente");
					if(pc.isNumeric(pid) == true) {
						break;
					}
				}
				pc.matarPID(pc.getOS(), pid);
				JOptionPane.showMessageDialog(null, "Processo Encerrado", "Feito", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
				break;
			case "2":
				String nome = JOptionPane.showInputDialog("Nome");
				pc.matarNome(pc.getOS(), nome);
				JOptionPane.showMessageDialog(null, "Processo Encerrado", "Feito", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
				break;
			default:
				opc = JOptionPane.showInputDialog(
						"Opção Inválida\n1 - Matar Processo (PID)\n2 - Matar Processo (nome)\n9 - Sair");
				break;
			}
		}

	}
}