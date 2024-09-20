package cadastro.gestao;

public class Imprime {
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String GRAY = "\u001B[37m";
	
	public static final String COR = BLUE;
	
	public static void bordas(String s, int t) {
		System.out.println(String.valueOf(s).repeat(t));
	}
	
	public static void bordasCor(String s, int t, String cor) {
		System.out.println(cor + (String.valueOf(s).repeat(t) + RESET));
	}
	
	public static void texto(String t) {
		System.out.println("*" + " ".repeat((Tela.tamTela - (t.length() + 2 * 1)) / 2) + String.valueOf(t) + " ".repeat(((Tela.tamTela - (t.length() + 2 * 1)) / 2) + 1) + "*");
	}
	
	public static void textoCor(String t, String cor) {
		System.out.println("*" + " ".repeat((Tela.tamTela - (t.length() + 2 * 1)) / 2) + cor + String.valueOf(t) + RESET + " ".repeat(((Tela.tamTela - (t.length() + 2 * 1)) / 2) + 1) + "*");
	}
	
	public static void caixaDeTexto(String t) {
		bordas("-", Tela.tamTela);
		texto(t);
		bordas("-", Tela.tamTela);
	}
	
	public static void caixaComTextoCor(String t, String cor) {
		bordas("-", Tela.tamTela);
		textoCor(t, cor);
		bordas("-", Tela.tamTela);
	}
	
	public static void dados(String d) {
		System.out.println(String.valueOf(d) + ":");
		System.out.print("-> ");
	}
}
