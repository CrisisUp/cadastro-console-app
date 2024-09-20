package cadastro.gestao;

import java.util.Scanner;

public class Opcoes {
	public static void opcoes() {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		do {
			int opcao = Menu.menu(scanner);
			try {
				switch (opcao) {
				case 0 -> {scanner.close(); return;}
				case 1 -> GestaoAluno.opcaoCadastrar(scanner);
				case 2 -> GestaoAluno.opcaoListar();
				case 3 -> GestaoAluno.opcaoPesquisar(scanner);
				case 4 -> GestaoAluno.opcaoAtualizar(scanner, id);
				case 5 -> GestaoAluno.opcaoDeletar(scanner, id);
				case 6 -> GestaoAluno.opcaoSalvar();
				default -> Imprime.caixaComTextoCor("Digite um número válido! ", Imprime.RED);
				}
			} catch (NumberFormatException e) {
				Imprime.bordas("-", Tela.tamTela);
				Imprime.textoCor("Opção inválida!", Imprime.RED);
				Imprime.textoCor("Digite um número entre 0 e 6", Imprime.YELLOW);
			} 
		}
		while (true);
	}
}

