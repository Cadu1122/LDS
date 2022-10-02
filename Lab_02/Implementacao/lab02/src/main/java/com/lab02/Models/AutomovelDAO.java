package com.lab02.Models;

import java.util.Set;

import com.lab02.Classes.GerenciamentoDeAlugueis.Automovel;
import com.lab02.Classes.Perfis.Agente;

public class AutomovelDAO {
    private static final Agente agenteModelo = new Agente(1, "Rogério", "nwrjnog", "1234");
    private static final Set<Automovel> automoveis = Set.of(new Automovel(1, 2022, agenteModelo, agenteModelo, "Chevrolet", "THZ-0259", "Agile"),
    new Automovel(2, 2020, agenteModelo, agenteModelo, "Fiat", "ZWT-0048", "Toro"));

    public static Automovel encontrarAutomovel(Integer matricula) {
        return automoveis.stream()
                .filter(a -> a.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);
    }
}
