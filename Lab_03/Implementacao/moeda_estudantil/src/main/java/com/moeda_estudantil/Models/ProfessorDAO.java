package com.moeda_estudantil.Models;

import java.util.List;

import com.moeda_estudantil.Classes.Professor;

public class ProfessorDAO {

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
}
