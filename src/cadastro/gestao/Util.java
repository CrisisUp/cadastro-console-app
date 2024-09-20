package cadastro.gestao;

import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Util {
	public static void obterDadosAluno(Aluno novoAluno, Scanner scanner) {
        Imprime.dados("Nome");
        novoAluno.setNome(scanner.next());

        Imprime.dados("Sobrenome");
        novoAluno.setSobrenome(scanner.next());

        Imprime.dados("Gênero");
        novoAluno.setGenero(scanner.next());

        Imprime.dados("Data de nascimento (dd/MM/yyyy)");
        String dataNascimento = scanner.next();

        if (!validarData(dataNascimento)) {
            Imprime.caixaComTextoCor("Data de nascimento inválida! Tente novamente.", Imprime.RED);
            return; // Sai do método para evitar cadastro com data incorreta
        }

        novoAluno.setNascimento(dataNascimento);
    }

    public static boolean validarData(String data) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            formato.parse(data); // Verifica se a data é válida
            return true;
        } catch (ParseException e) {return false;}
    }
	
	public static void listarAluno(Map<Integer, Aluno> lista_alunos) {
		if (lista_alunos.isEmpty()) {
			Imprime.textoCor("Não há alunos cadastrados", Imprime.RED);
			return;
		}
		
		for (Map.Entry<Integer, Aluno> entry : lista_alunos.entrySet()) {
			int id = entry.getKey();
			Aluno aluno = entry.getValue();
			System.out.println();
			System.out.println("-> Id: " + id);
			System.out.println("-> Nome: " + aluno.getNome());
			System.out.println("-> Sobrenome: " + aluno.getSobrenome());
			System.out.println("-> Gênero: " + aluno.getGenero());
			System.out.println("-> Data de nascimento: " + aluno.getNascimento());
		}
	}
}
