package gusto.fatec.processos.controller;

import gusto.fatec.processos.model.OSEnum;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ProcessosController {

    private static final Logger LOGGER = Logger.getLogger(ProcessosController.class.getName());

    public void listarProcessos(OSEnum os) {
        StringBuilder saida = new StringBuilder();
        String comando = os.listarProcessos();

        try {
            Process processo = Runtime.getRuntime().exec(comando);
            InputStream stream = processo.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader buffer = new BufferedReader(reader);
            saida = montarSaidas(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String msg = saida.toString();
        LOGGER.info(msg);
    }

    private StringBuilder montarSaidas(BufferedReader buffer) throws IOException {
        StringBuilder saida = new StringBuilder();
        String linha = buffer.readLine();

        while (linha != null) {
            saida.append(linha).append("\n");
            linha = buffer.readLine();
        }

        return saida;
    }

    public void matarPID(OSEnum os, String pid) {
        String cmdPid = os.matarPID();
        mataProcesso(cmdPid + pid);
    }

    public void matarNome(OSEnum os,String nome) {
        String cmdNome = os.matarNome();
        mataProcesso(cmdNome + nome);
    }

    private void mataProcesso(String comando) {
        try {
            Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    public boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}