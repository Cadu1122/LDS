package com.moeda_estudantil.Models;

import java.util.List;

import com.moeda_estudantil.Classes.Professor;
import com.moeda_estudantil.Classes.Historico;
import com.moeda_estudantil.Classes.Aluno;
//import com.moeda_estudantil.Models.HistoricoDAO;

public class ProfessorDAO {
    private static final ProfessorDAO PROFESSOR_DAO = new ProfessorDAO();

    public static final ProfessorDAO getInstance() {
        return PROFESSOR_DAO;
    }

    private static List<Professor> professores = List.of(
        new Professor("Joaquim22", "5678", "Joaquim Santos", "456.189.723-85",
        "PUC Minas", "Computação"),
        new Professor("Geraldo47", "7842", "Geraldo Carvalho", "564.812.812-76",
        "UFMG", "Comunicação"));

    public static Professor encontrar(String login) {
        return professores.stream()
            .filter(u -> u.getLogin().equals(login))
            .findFirst()
            .orElse(null);
    }

    public static List<Professor> getProfessores() {
        return professores;
    }
    
    public boolean darMoedas(String login, String loginAluno, int qte, String motivo){
        Professor p =  encontrar(login);
        Aluno a= AlunoDAO.getInstance().encontrarAluno(loginAluno);
        if(p == null) {
            return false;
        } else {
            Historico h = new Historico(p, a, qte, motivo);
            p.darMoedas(a, qte);
            HistoricoDAO.getInstance().salvar(h);
            AlunoDAO.getInstance().alterarAluno(loginAluno, a);
            return true;
        }
        

    }

    
    
}
