package cadastro.gestao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Aluno implements Map<Integer, Aluno>{
	private static int nextId = 1;
	private int id;
	private String nome, sobrenome, genero;
	private String nascimento;
	
	public Aluno() {}

	public Aluno(int id, String nome, String sobrenome, String genero, String nascimento) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = genero;
		this.nascimento = nascimento;
	}
	
	public static int getNextId() {return nextId;}
	public static void setNextId(int nextId) {Aluno.nextId = nextId;}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public String getSobrenome() {return sobrenome;}
	public void setSobrenome(String sobrenome) {this.sobrenome = sobrenome;}
	
	public String getGenero() {return genero;}
	public void setGenero(String genero) {this.genero = genero;}

	public String getNascimento() {return nascimento;}
	public void setNascimento(String nascimento) {this.nascimento = nascimento;}
	
	public int getIdade() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate nasc = LocalDate.parse(nascimento, format);
		return (int) ChronoUnit.YEARS.between(nasc, LocalDate.now());
	}
	
	

	@Override
	public String toString() {
		return id + "," + nome + "," + sobrenome + "," + genero + "," + nascimento;
	}

	@Override
	public int size() {return 0;}

	@Override
	public boolean isEmpty() {return false;}

	@Override
	public boolean containsKey(Object key) {return false;}

	@Override
	public boolean containsValue(Object value) {return false;}

	@Override
	public Aluno get(Object key) {return null;}

	@Override
	public Aluno put(Integer key, Aluno value) {return null;}

	@Override
	public Aluno remove(Object key) {return null;}

	@Override
	public void putAll(Map<? extends Integer, ? extends Aluno> m) {}

	@Override
	public void clear() {}

	@Override
	public Set<Integer> keySet() {return null;}

	@Override
	public Collection<Aluno> values() {return null;}

	@Override
	public Set<Entry<Integer, Aluno>> entrySet() {return null;}

	@Override
	public int hashCode() {return Objects.hash(id);}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {return true;}
		if (obj == null) {return false;}
		if (getClass() != obj.getClass()) {return false;}
		Aluno other = (Aluno) obj;
		return id == other.id;
	}	
}
