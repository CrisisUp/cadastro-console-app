package cadastro.gestao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class GestaoAluno {
	public static void main(String[] args) {
		recuperarDados();
		Tela.cabecalho(false);
		Opcoes.opcoes();
		Tela.cabecalho(true);
	}

	private final static String LOCAL = "C:\\Users\\krist\\eclipse-workspace\\cadastro\\src\\cadastro\\gestao\\cadastro.txt";
	private static int nextId = 1;
	private static final Map<Integer, Aluno> lista_alunos = new HashMap<>();

	public static void opcaoCadastrar(Scanner scanner) {
		Imprime.caixaComTextoCor("CADASTRAR ALUNOS ", Imprime.GREEN);
		Aluno novoAluno = new Aluno();
		Util.obterDadosAluno(novoAluno, scanner);
		novoAluno.setId(nextId);
		lista_alunos.put(novoAluno.getId(), novoAluno);
		nextId++;
		Imprime.caixaComTextoCor("Cadastro realizado com sussesso! ", Imprime.GREEN);
	}

	public static void opcaoListar() {
		Imprime.caixaComTextoCor("LISTAGEM DOS ALUNOS", Imprime.YELLOW);
		Util.listarAluno(lista_alunos);
		Imprime.bordas("-", Tela.tamTela);
	}

	public static void opcaoPesquisar(Scanner scanner) {
		Imprime.caixaComTextoCor("PESQUISA POR ALUNO ", Imprime.YELLOW);

		if (lista_alunos.isEmpty()) {
			Imprime.textoCor("Não há alunos cadastrados", Imprime.RED);
			Imprime.bordas("-", Tela.tamTela);
			return;
		}

		Imprime.textoCor("Digite o id do aluno ", Imprime.GREEN);
		Imprime.bordas("-", Tela.tamTela);
		System.out.print("-> ");

		try {
			int id = scanner.nextInt();

			for (Map.Entry<Integer, Aluno> entry : lista_alunos.entrySet()) {
				if (entry.getKey() == id) {
					Aluno aluno = entry.getValue();
					System.out.println("Id: " + aluno.getId());
					System.out.println("Nome: " + aluno.getNome());
					System.out.println("Sobrenome: " + aluno.getSobrenome());
					System.out.println("Gênero: " + aluno.getGenero());
					System.out.println("Idade: " + aluno.getIdade());
					return;
				}
			}

			Imprime.caixaComTextoCor("Aluno com id " + id + " não encontrado", Imprime.RED);
		} catch (InputMismatchException e) {
			Imprime.caixaComTextoCor("Entrada inválida ", Imprime.RED);
			scanner.next();
		}
	}

	public static void opcaoAtualizar(Scanner scanner, int id) {
		Imprime.caixaComTextoCor("ATUALIZAÇÃO DE ALUNOS", Imprime.YELLOW);

		if (lista_alunos.isEmpty()) {
			Imprime.textoCor("Não há alunos cadastrados", Imprime.RED);
			Imprime.bordas("-", Tela.tamTela);
			return;
		}

		Imprime.textoCor("Digite o id para atualizar ", Imprime.GREEN);
		Imprime.bordas("-", Tela.tamTela);
		System.out.print("-> ");

		try {
			id = scanner.nextInt();
		} catch (InputMismatchException e) {
			// Imprime.bordas("-", Tela.tamTela);
			Imprime.caixaComTextoCor("Entrada inválida ", Imprime.RED);
			scanner.next(); // Limpa o buffer
			return;
		}

		// Verifica se o aluno com o Id fornecido existe
		Aluno aluno = lista_alunos.get(id);
		if (aluno == null) {
			Imprime.bordas("-", Tela.tamTela);
			Imprime.textoCor("Aluno com id " + id + " não encontrado", Imprime.YELLOW);
			Imprime.caixaComTextoCor("Atualização cancelada", Imprime.RED);
			return;
		}

		// Atualiza os dados do aluno
		System.out.print("Novo nome:\n-> ");
		aluno.setNome(scanner.next());

		System.out.print("Novo sobrenome:\n-> ");
		aluno.setSobrenome(scanner.next());

		System.out.print("Novo gênero:\n-> ");
		aluno.setGenero(scanner.next());

		System.out.print("Nova data de nascimento (dd/MM/yyyy):\n-> ");
		aluno.setNascimento(scanner.next());

		Imprime.caixaComTextoCor("Informações atualizadas com sucesso! ", Imprime.GREEN);
	}

	public static void opcaoDeletar(Scanner scanner, int id) {
		Imprime.caixaComTextoCor("DELETAR ALUNO", Imprime.GREEN);
		Imprime.textoCor("Certeza que deseja deletar um aluno?[s/n]", Imprime.YELLOW);
		Imprime.bordas("-", Tela.tamTela);
		System.out.print("-> ");
		char confirm = scanner.next().charAt(0);
		
		if (confirm == 's' || confirm == 'S') {
			Imprime.caixaComTextoCor("Informe o id a ser deletado", Imprime.YELLOW);
			System.out.print("-> ");
			try {
				id = scanner.nextInt();
				boolean del = remove(id);
				if (del) {
					Imprime.caixaComTextoCor("O aluno com Id " + id + " foi deletado! ", Imprime.GREEN);
			    } else {
				Imprime.caixaComTextoCor("Id " + id + " não existe! ", Imprime.RED);
			    }
			} catch (InputMismatchException e) {
				Imprime.bordas("-", Tela.tamTela);
		        Imprime.textoCor("Informação inválida! ", Imprime.YELLOW);
		        Imprime.caixaComTextoCor("Atividade cancelada", Imprime.RED);
				scanner.next();
			}
		}
	}

	public static void opcaoSalvar() {
		if (lista_alunos.isEmpty()) {
			Imprime.caixaComTextoCor("Não há alunos cadastrados", Imprime.RED);
			// Imprime.bordas("-", Tela.tamTela);
			return;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOCAL))) {
			for (Aluno aluno : lista_alunos.values()) {
				writer.write(aluno.toString());
				writer.newLine();
			}
			Imprime.caixaComTextoCor("Dados salvos com sucesso!", Imprime.GREEN);
		} catch (IOException e) {
			Imprime.caixaComTextoCor("Erro ao salvar os dados: " + e.getMessage(), Imprime.RED);
		}
	}

	public static boolean remove(int id) {
		String idStr = String.valueOf(id).trim();
		int idInt = Integer.parseInt(idStr);
		if (lista_alunos.containsKey(idInt)) {
			lista_alunos.remove(idInt); // remove AQUI
			return true;
		} else {
			return false;
		}
	}

	public static void recuperarDados() {
		try (BufferedReader reader = new BufferedReader(new FileReader(LOCAL))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] partes = linha.split(",");

				// Verifica se a linha tem todos os dados necessários
				if (partes.length < 5) {
					System.err.println("Linha mal formatada: " + linha);
					continue;
				}

				// Extrai os dados de cada linha
				int id = Integer.parseInt(partes[0]);
				String nome = partes[1];
				String sobrenome = partes[2];
				String genero = partes[3];
				String nascimento = partes[4];

				// Cria um novo aluno para cada linha lida
				Aluno aluno = new Aluno();
				aluno.setId(id);
				aluno.setNome(nome);
				aluno.setSobrenome(sobrenome);
				aluno.setGenero(genero);
				aluno.setNascimento(nascimento);

				// Adiciona o aluno à lista
				lista_alunos.put(id, aluno);

				// Aqui você pode passar os dados para a função recuperarAluno, se necessário
				// recuperarAluno(id, nome, sobrenome, genero, nascimento);

				// Atualiza o nextId para garantir que os próximos Ids sejam únicos
				if (id >= nextId) {
					nextId = id + 1;
				}
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println("Erro ao converter um Id: " + e.getMessage());
		}
	}

	public static void recuperarAluno(int id, String nome, String sobrenome, String genero, String nascimento) {
		Aluno aluno = new Aluno(id, nome, sobrenome, genero, nascimento);
		lista_alunos.put(id, aluno); // Adiciona o aluno ao Map
	}
}
