package com.moeda_estudantil.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import com.moeda_estudantil.Models.HistoricoDAO;
import com.moeda_estudantil.Models.ProfessorDAO;


@Controller
@RequestMapping(path = "/professores")
public class ProfessorController {
    
    @GetMapping
    public ModelAndView getProfessores() {
        ModelAndView modelAndView = new ModelAndView("Professores/index");
        modelAndView.addObject("professores", ProfessorDAO.getProfessores());
        return modelAndView;
    }

    @GetMapping(path = "/{login}/historico")
    public ModelAndView getHistorico(@PathVariable String login) {
        ModelAndView modelAndView = new ModelAndView("Professores/historico");
        modelAndView.addObject("historicos", HistoricoDAO.getInstance().encontrar(ProfessorDAO.encontrar(login)));
        return modelAndView;
    }

    @PostMapping(path = "/{login}/moedas")
    public ModelAndView giveCoins(@PathVariable String login, String loginAluno, int qte, String motivo) {
            ProfessorDAO.getInstance().darMoedas(login, loginAluno, qte, motivo);
            return new ModelAndView("redirect:/professores");
    }

    @GetMapping(path = "/{login}/moedas")
    public ModelAndView getPagMoedas(@PathVariable String login ) {
        ModelAndView modelAndView = new ModelAndView("Professores/moedas");
        modelAndView.addObject("login", login);
        return modelAndView;
    }
}
