package main;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Acoes;
import utils.StringConstants;

public class RenovadorDeGEMain {
	public static final int qtdGE = 4;
	private static ArrayList<String> accs = new ArrayList<String>();

	public static void main(String[] args) throws IOException, AWTException, InterruptedException {

		// String contas = "eldelock, icaro solar";
		// int qtdVirgulas = 0;
		// for (int i = 0; i < contas.length(); i++) {
		// if (contas.charAt(i) == ',') {
		// qtdVirgulas++;
		// }
		// }
		//
		//
		// for (int i = 0; i < qtdVirgulas + 1; i++) {
		// if (contas.contains(",")) {
		// accs.add(contas.substring(0, contas.indexOf(",")));
		// } else {
		// accs.add(contas);
		// }
		// contas = contas.substring(contas.indexOf(",") + 2);
		// }
		// System.err.println(accs);

		try (BufferedReader br = new BufferedReader(new FileReader(StringConstants.pathPrincipal + "accsSemGE.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				accs.add(line);
			}
		}
		System.err.println(accs);

		for (int i = 0; i < accs.size(); i++) {
			System.out.println(accs.get(i).toUpperCase());
			Acoes.executarPassos(accs.get(i));
			System.err.println("Contas sem upar: " + Acoes.listaContasSemUsarPF);
			Thread.sleep(50);
			Acoes.wait(3);
		}
		Acoes.limparArquivo();
	}

}