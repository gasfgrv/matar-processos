package gusto.fatec.processos.view;

import gusto.fatec.processos.controller.ProcessosController;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    public static void main(String[] args) {
        ProcessosController pc = new ProcessosController();
        pc.listarProcessos();

        String opc = showInputDialog("Opção Inválida\n1 - Matar Processo (PID)\n2 - Matar Processo (nome)\n9 - Sair");

        while (!opc.equals("9")) {
            switch (opc) {
                case "1": {
                    matarPorPid(pc);
                    System.exit(0);
                    break;
                }
                case "2": {
                    matarProcessoPorNome(pc);
                    System.exit(0);
                    break;
                }
                default: {
                    showMessageDialog(null,
                            "Opção inválida",
                            "Erro",
                            WARNING_MESSAGE);
                    break;
                }
            }
        }

    }

    private static void matarPorPid(ProcessosController pc) {
        String pid = showInputDialog("PID");
        boolean isNumeric = pc.isNumeric(pid);

        while (!isNumeric) {
            pid = showInputDialog("PID inválido, digite novamente");
            isNumeric = pc.isNumeric(pid);
        }

        pc.matarPID(pid);
        showMessageDialog(null,
                "Processo Encerrado",
                "Feito",
                PLAIN_MESSAGE);
    }

    private static void matarProcessoPorNome(ProcessosController pc) {
        String nome = showInputDialog("Nome");
        pc.matarNome(nome);
        showMessageDialog(null,
                "Processo Encerrado",
                "Feito",
                PLAIN_MESSAGE);
    }
}