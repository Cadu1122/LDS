package com.moeda_estudantil.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moeda_estudantil.Classes.Aluno;
import com.moeda_estudantil.Classes.Vantagem;
import com.moeda_estudantil.Classes.VantagemComprada;
import com.moeda_estudantil.Models.AlunoDAO;
import com.moeda_estudantil.Models.VantagemCompradaDAO;
import com.moeda_estudantil.Models.VantagemDAO;

@Controller
@RequestMapping("/vantagens")
public class VantagemController {
    
    @GetMapping("/{loginAluno}")
    public ModelAndView getVantagens(@PathVariable String loginAluno) {
        ModelAndView modelAndView = new ModelAndView("Alunos/vantagemList");
        modelAndView.addObject("loginAluno", loginAluno);
        modelAndView.addObject("vantagens", VantagemDAO.getInstance().getVantagens());
        return modelAndView;
    }

    @GetMapping("/{loginAluno}/{idVantagem}/comprar")
    public ModelAndView comprarVantagem(@PathVariable String loginAluno, @PathVariable Integer idVantagem) {
        Aluno aluno = AlunoDAO.getInstance().encontrarAluno(loginAluno);
        Vantagem vantagem = VantagemDAO.getInstance().encontrar(idVantagem);
        try {
            VantagemComprada vantagemComprada = new VantagemComprada(aluno, vantagem);
            AlunoDAO.getInstance().alterarAluno(loginAluno, aluno);
            VantagemCompradaDAO.getInstance().salvar(vantagemComprada);
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        return new ModelAndView("redirect:/alunos/" + loginAluno + "/vantagensCompradas");
    }
}
