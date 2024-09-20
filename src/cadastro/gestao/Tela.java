package cadastro.gestao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Tela {
	static int tamTela = 44; // Valor MÍNIMO = 44, deve ser número PAR
	public static Boolean cabecalho(boolean c) {
		SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		
		if (c == false) {
			System.out.println();
			Imprime.bordas("*", Tela.tamTela);
			System.out.println("Programa Iniciado em: " + formData.format(data));
		} else {
			Imprime.bordas("*", Tela.tamTela);
			System.out.println("Programa Finalizado em: " + formData.format(data));
		}
		
		imprimeCabecalho("CRIS CORPORATION", Imprime.GREEN);
		imprimeCabecalho("SOFTWARE DE GESTÃO DE PESSOAS ", Imprime.PURPLE);
		imprimeSubCabe("Produzido por: ", "Cristiano B. Pessoa");
		Imprime.bordas("*", Tela.tamTela);
		System.out.println();
		return c;
	}
	
	public static void imprimeCabecalho(String c, String cor) {
		System.out.println("*".repeat((tamTela - c.length() - 6) / 2) 
				+ "   " + cor + String.valueOf(c) + Imprime.RESET + "   " + 
				"*".repeat((tamTela - c.length() - 6) / 2));
	}
	
	public static void imprimeSubCabe(String g, String s) {
		System.out.println(" ".repeat(tamTela - g.length() - s.length()) + g + Imprime.GRAY +s + Imprime.RESET);
	}
}

