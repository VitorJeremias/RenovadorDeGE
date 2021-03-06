package modelo;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.RenovadorDeGEMain;
import utils.StringConstants;

public class Acoes {
	public static ArrayList<String> listaContasSemUsarPF = new ArrayList<String>();
	public static final Random random = new Random(System.currentTimeMillis());

	public static void executarPassos(String acc) throws AWTException, IOException, HeadlessException, InterruptedException {
		campoLogin(acc);
		InputManager.escrever(acc);
		botaoLogin(acc);
		botaoJogar(acc);
		botaoHoundsmoor(acc);
		fecharJanela(acc);
		if (temPF(acc)) {
			Amigos(acc);
			topAmigos(acc);
			vitorMonticelli(acc);
			grandesEdificios(acc);
			atividade(acc);
			abrirAleatorio(RenovadorDeGEMain.qtdGE);
			mouseNeutro();
			todaABarra(acc);
			fecharJanela(acc);
			fecharJanela(acc);
		} else {
			listaContasSemUsarPF.add(acc);
			System.err.println(acc + " Adicionado na lista");
			escreverNoArquivo(acc);
		}
		logout(acc);
		sair(acc);
		sair2(acc);
	}

	public static void limparArquivo() throws IOException {
		@SuppressWarnings("resource")
		BufferedWriter writer = new BufferedWriter(new FileWriter(StringConstants.pathPrincipal + "accsSemGE.txt"));
		writer.write("");
	}

	public static void escreverNoArquivo(String acc) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(StringConstants.pathPrincipal + "accsSemGE.txt", true));
		writer.append(acc + "\n");
		writer.close();

	}

	public static boolean temPF(String acc) throws HeadlessException, AWTException, IOException {
		return esperarImagemComLimite(EnumImagens.TEM_1_PF, acc);
	}

	public static void mouseNeutro() throws AWTException {
		InputManager.clicker = new Robot();
		InputManager.clicker.mouseMove(0, 0);
	}

	public static void abrirAleatorio(int qtdGE) throws AWTException {
		InputManager.clicker = new Robot();
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		InputManager.clicker.mouseMove(x, y + random(29, qtdGE + 1)); // Clica no Abrir de um dos n primeiros GEs aleatoriamente
		InputManager.clicker.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
		InputManager.clicker.delay(20);
		InputManager.clicker.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
	}

	public static int random(int range, int multiple) {
		int valor = 29;
		do {
			valor = new Random().nextInt(multiple) * range;
		} while (valor == 0);
		System.out.println("GE Nº " + valor / range);
		return valor;
	}

	public static void atividade(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.ATIVIDADE, acc);
	}

	public static void grandesEdificios(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.GRANDES_EDIFICIOS, acc);
	}

	public static void vitorMonticelli(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.VITOR_MONTICELLI, acc);
	}

	public static void sair2(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.SAIR2, acc);
	}

	public static void sair(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.SAIR, acc);
	}

	public static void logout(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.LOGOUT, acc);
	}

	public static void campoLogin(String acc) throws InterruptedException, HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.CAMPO_LOGIN, acc);
		Thread.sleep(1000);
	}

	public static void botaoLogin(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.LOGIN, acc);
	}

	public static void botaoJogar(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.JOGAR, acc);
	}

	public static void botaoHoundsmoor(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.HOUNDSMOOR, acc);
		wait(10);
	}

	public static void fecharJanela(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.FECHAR_JANELA, acc);
	}

	public static void Amigos(String acc) throws HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.AMIGOS, acc);
	}

	public static void topAmigos(String acc) throws InterruptedException, HeadlessException, AWTException, IOException {
		compararImagens(EnumImagens.TOP_AMIGOS, acc);
		Thread.sleep(1000);
	}

	public static void todaABarra(String acc) throws HeadlessException, AWTException, IOException, InterruptedException {
		Boolean temPF = false;
		Thread.sleep(1500);
		temPF = temPF || esperarImagemComLimite(EnumImagens.TODA_A_BARRA_10, acc);
		Thread.sleep(200);
		compararImagens(EnumImagens.TODA_A_BARRA_10, acc);
		Thread.sleep(200);
		temPF = temPF || esperarImagemComLimite(EnumImagens.TODA_A_BARRA_1_DIGITO, acc);
		compararImagens(EnumImagens.TODA_A_BARRA_1_DIGITO, acc);
		Thread.sleep(200);
		if (!temPF) {
			listaContasSemUsarPF.add(acc);
			System.err.println(acc + " Adicionado na lista");
		}
	}

	public static void wait(int segundos) throws AWTException {
		InputManager.clicker = new Robot();
		System.out.print("Wait " + segundos + "s: ");
		for (int i = 0; i < segundos - 1; i++) {
			InputManager.clicker.delay(1000);
			System.out.print(i + 1 + ", ");
		}
		InputManager.clicker.delay(1000);
		System.out.println(segundos);
	}

	public static boolean esperarImagemComLimite(EnumImagens imagem, String acc) throws HeadlessException, AWTException, IOException {
		BufferedImage bi = ImageIO.read(new File(imagem.getPath()));
		boolean achou = false;
		boolean fail = true;
		int count = 0;
		while (achou == false && count < imagem.getParametrosDaImagem().getMaxCount()) {
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					if (!achou) {
						boolean invalid = false;
						int k = x, l = y;
						for (int a = 0; a < bi.getWidth(); a++) {
							l = y;
							for (int b = 0; b < bi.getHeight(); b++) {
								if (bi.getRGB(a, b) != image.getRGB(k, l)) {
									invalid = true;
									break;
								} else {
									l++;
								}
							}
							if (invalid) {
								break;
							} else {
								k++;
							}
						}
						if (!invalid) {
							achou = true;
							System.out.println(imagem.getAcao() + ": Achou! " + " " + acc);
							fail = false;
						}
					}
				}
			}
			count++;
		}
		if (fail) {
			System.out.println(imagem.getAcao() + ": Nao achou! " + acc);
			achou = false;
		}
		return achou;
	}

	public static void acharImagem(EnumImagens imagem, double widthMult, double heigthMult, int maxCount, String acc) throws HeadlessException, AWTException, IOException {
		BufferedImage bi = ImageIO.read(new File(imagem.getPath()));
		boolean achou = false;
		boolean fail = true;
		int count = 0;
		while (achou == false && count < maxCount) {
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					if (!achou) {
						boolean invalid = false;
						int k = x, l = y;
						for (int a = 0; a < bi.getWidth(); a++) {
							l = y;
							for (int b = 0; b < bi.getHeight(); b++) {
								if (bi.getRGB(a, b) != image.getRGB(k, l)) {
									invalid = true;
									break;
								} else {
									l++;
								}
							}
							if (invalid) {
								break;
							} else {
								k++;
							}
						}
						if (!invalid) {
							InputManager.clickEvent(k - (bi.getWidth() * widthMult), l - (bi.getHeight() * heigthMult)); // Clica no centro do objeto
							achou = true;
							System.out.println(imagem.getAcao() + ": OK! " + " " + acc);
							fail = false;
						}
					}
				}
			}
			count++;
		}
		if (fail) {
			System.out.println(imagem.getAcao() + ": FAIL! " + acc);
		}
	}

	public static void compararImagens(EnumImagens imagem, String acc) throws HeadlessException, AWTException, IOException {
		acharImagem(imagem, imagem.getParametrosDaImagem().getWidthMult(), imagem.getParametrosDaImagem().getHeigthMult(), imagem.getParametrosDaImagem().getMaxCount(), acc);
	}

}
