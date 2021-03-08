package gusto.fatec.processos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class ProcessosController {
	private static final Logger LOGGER = Logger.getLogger(ProcessosController.class.getName());
	private static final boolean WINDOWS = System.getProperty("os.name").contains("Windows");
	private static final boolean LINUX = System.getProperty("os.name").contains("Linux");


	public String listarProcessos() {
		String comando = null;
		StringBuilder saida= new StringBuilder();

		if (WINDOWS) {
			comando = "TASKLIST /FO TABLE";
		} else if (LINUX) {
			comando = "ps -A";
		}

		try {
			Process processo = Runtime.getRuntime().exec(comando);
			InputStream stream = processo.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String linha = buffer.readLine();
			while (linha != null) {
				saida.append(linha).append("\n");
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saida.toString();
	}

	public void matarPID(String pid) {
		String cmdPid = null;
		if (WINDOWS) {
			cmdPid = "TASKKILL /PID ";
		} else if (LINUX) {
			cmdPid = "kill -9 ";
		}
		mataProcesso(cmdPid + pid);
	}

	public void matarNome(String nome) {
		String cmdNome = null;
		if (WINDOWS) {
			cmdNome = "TASKKILL /IM ";
		} else if (LINUX) {
			cmdNome = "killall -9 ";
		}
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
			parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}