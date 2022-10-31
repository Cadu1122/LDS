package com.moeda_estudantil.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moeda_estudantil.Classes.Aluno;
import com.moeda_estudantil.Models.AlunoDAO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping(path = "/alunos")
public class AlunoController {
    AlunoDAO alunoDAO = new AlunoDAO();

    @GetMapping
    public ModelAndView getAluno(Aluno aluno) {
        ModelAndView modelAndView = new ModelAndView("Alunos/index");
        modelAndView.addObject("alunos", alunoDAO.getAlunos());
        return modelAndView;
    }

    @GetMapping(path = "/new")
    public ModelAndView postAluno() {
        ModelAndView modelAndView = new ModelAndView("Alunos/create");
        modelAndView.addObject("aluno", new Aluno());
        return modelAndView;
    }

    @GetMapping(path = "/{login}/edit")
    public ModelAndView putAluno(@PathVariable String login) {
        Aluno aluno = (Aluno) alunoDAO.encontrarAluno(login);
        if (aluno == null) {
            return new ModelAndView("redirect:/alunos");
        } else {
            ModelAndView modelAndView = new ModelAndView("Alunos/update");
            modelAndView.addObject("aluno", aluno);
            return modelAndView;
        }
    }

    @PostMapping
    public ModelAndView createAluno(@Valid Aluno aluno, BindingResult result) { 
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Alunos/create");
            return modelAndView;
        } else {
            alunoDAO.criarAluno(aluno);
            return new ModelAndView("redirect:/alunos");
        }
    }

    @PostMapping(path = "/{login}/edit")
    public ModelAndView updateAluno(@PathVariable String login, @Valid Aluno aluno, BindingResult result) {
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Alunos/" + login + "/edit");
            modelAndView.addObject("aluno", aluno);
            return modelAndView;
        } else {
            alunoDAO.alterarAluno(login, aluno);
            return new ModelAndView("redirect:/alunos");
        }
    }

    @GetMapping(path = "/{login}/delete")
    public ModelAndView deleteAluno(@PathVariable String login) {
        alunoDAO.excluirAluno(login);
        return new ModelAndView("redirect:/alunos");
    }
}

