package cadastro.gestao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	public static int menu(Scanner scanner) {
		int opcao = 0;
		System.out.println();
		Imprime.bordasCor("*", Tela.tamTela, Imprime.COR);
		imprimirTextoDentroCaixa("DIGITE A OPÇÃO DESEJADA", Imprime.YELLOW);
		Imprime.bordasCor("*", Tela.tamTela, Imprime.COR);
		imprimirOpcao("0 - Sair do Programa");
		imprimirOpcao("1 - Cadastrar       ");
		imprimirOpcao("2 - Listar          ");
		imprimirOpcao("3 - Pesquisar       ");
		imprimirOpcao("4 - Atualizar       ");
		imprimirOpcao("5 - Deletar         ");
		imprimirOpcao("6 - Salvar          ");
		Imprime.bordasCor("*", Tela.tamTela, Imprime.COR);
		while (true) {
		    try {
		    	System.out.print("-> ");
		        opcao = scanner.nextInt();
		        break; // Sai do loop se a entrada for válida
		    } catch (InputMismatchException e) {
		        Imprime.caixaComTextoCor("Entrada inválida ", Imprime.RED);
		        Imprime.textoCor("Digite um inteiro válido [0-6] ", Imprime.YELLOW);
		        Imprime.bordas("-", Tela.tamTela);
		        scanner.next();
		    }
		}
		return opcao;
	}
	
	public static final int ESP = ((Tela.tamTela - 20) / 2) - 1;
	public static void imprimirOpcao(String op) {
		System.out.println(Imprime.COR + "*" + Imprime.RESET + " ".repeat(ESP) + String.valueOf(op) + 
						   " ".repeat(ESP) + Imprime.COR + "*" + Imprime.RESET);
	}
	
	public static void imprimirTextoDentroCaixa(String s, String cor) {
		final int vazio = (Tela.tamTela - (s.length() + 2 * 1)) / 2;
		final String r = Imprime.RESET;
		final String c = Imprime.COR;
		System.out.println(c + "*" + r + " ".repeat(vazio) + cor + 
						   String.valueOf(s) + r + " ".repeat((vazio) + 1) 
						   + c + "*" + r);
	}
}


