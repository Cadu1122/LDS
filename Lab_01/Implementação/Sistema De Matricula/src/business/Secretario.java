package business;

import java.time.LocalDate;
import java.util.Set;

public class Secretario extends Usuario{

	public Secretario(String nome, String senha) {
		super(nome, senha);
	}
	
	public void setPrazoMatricula(boolean aux, String data)
	{
		if(aux=true) {
			Matricula.setInicio(LocalDate.parse(data));
		}
		else
		{
			Matricula.setPrazo(LocalDate.parse(data));
		}
	}
	
	public void adicionarAluno(Aluno aluno,Set<Aluno>alunos)
	{
		alunos.add(aluno);
	}
	public void adicionarProfessor(Professor professor, Set<Professor> professores)
	{
		professores.add(professor);
	}
	public void adicionarTurma(Turma turma,Set <Turma>turmas)
	{
		turmas.add(turma);
	}
	public void alterarAluno(Aluno aluno,String nome,String senha)
	{
		aluno.setNome(nome);
		aluno.setSenha(senha);
	}
	public void alterarProfessor(Professor professor,String nome,String senha)
	{
		professor.setNome(nome);
		professor.setSenha(senha);
	}
	public void alterarTurma(Turma turma,String nome,boolean obrigatorio)
	{
		turma.setNome(nome);
		turma.setObrigatorio(obrigatorio);
	}
	public void excluirAluno(Aluno aluno,Set<Aluno>alunos)
	{
		alunos.remove(aluno);
	}
	public void excluirProfessor(Professor professor,Set<Professor>professores)
	{
		professores.remove(professor);
	}
	public void excluirTurma(Turma turma,Set<Turma>turmas)
	{
		turmas.remove(turma);
	}
	public void gerarCurriculo()
	{
		
	}
}
