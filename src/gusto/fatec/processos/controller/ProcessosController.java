package gusto.fatec.processos.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {
	public ProcessosController() {
		super();
	}

	public String getOS() {
		return System.getProperty("os.name").toString();
	}

	public String listarProcessos(String so) {
		String comando = null;
		String saida="";
		if (so.contains("Windows")) {
			comando = "TASKLIST /FO TABLE";
		} else if (so.contains("Linux")) {
			comando = "ps -A";
		}
		try {
			Process processo = Runtime.getRuntime().exec(comando);
			InputStream stream = processo.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String linha = buffer.readLine();
			while (linha != null) {
				saida += linha + "\n";
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saida;
	}

	public void matarPID(String so, String pid) {
		String cmdPid = null;
		if (so.contains("Windows")) {
			cmdPid = "TASKKILL /PID ";
		} else if (so.contains("Linux")) {
			cmdPid = "kill -9 ";
		}
		mataProcesso(cmdPid + pid);
	}

	public void matarNome(String so, String nome) {
		String cmdNome = null;
		if (so.contains("Windows")) {
			cmdNome = "TASKKILL /IM ";
		} else if (so.contains("Linux")) {
			cmdNome = "killall -9 ";
		}
		mataProcesso(cmdNome + nome);
	}

	private void mataProcesso(String comando) {
		try {
			Runtime.getRuntime().exec(comando);
		} catch (IOException e) {
			System.out.println(e.getMessage());
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