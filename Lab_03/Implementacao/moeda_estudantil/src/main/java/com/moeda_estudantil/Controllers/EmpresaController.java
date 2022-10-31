package com.moeda_estudantil.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moeda_estudantil.Classes.Empresa;
import com.moeda_estudantil.Models.EmpresaDAO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping(path = "/empresas")
public class EmpresaController {
    EmpresaDAO empresaDAO = new EmpresaDAO();

    @GetMapping
    public ModelAndView getEmpresa(Empresa empresa) {
        ModelAndView modelAndView = new ModelAndView("Empresas/index");
        modelAndView.addObject("empresas", empresaDAO.getEmpresas());
        return modelAndView;
    }

    @GetMapping(path = "/new")
    public ModelAndView postEmpresa() {
        ModelAndView modelAndView = new ModelAndView("Empresas/create");
        modelAndView.addObject("empresa", new Empresa());
        return modelAndView;
    }

    @GetMapping(path = "/{login}/edit")
    public ModelAndView putEmpresa(@PathVariable String login) {
        Empresa empresa = (Empresa) empresaDAO.encontrarEmpresa(login);
        if (empresa == null) {
            return new ModelAndView("redirect:/empresas");
        } else {
            ModelAndView modelAndView = new ModelAndView("Empresas/update");
            modelAndView.addObject("empresa", empresa);
            return modelAndView;
        }
    }

    @PostMapping
    public ModelAndView createEmpresa(@Valid Empresa empresa, BindingResult result) { 
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Empresas/create");
            return modelAndView;
        } else {
            empresaDAO.criarEmpresa(empresa);
            return new ModelAndView("redirect:/empresas");
        }
    }

    @PostMapping(path = "/{login}/edit")
    public ModelAndView updateEmpresa(@PathVariable String login, @Valid Empresa empresa, BindingResult result) {
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Empresas/" + login + "/edit");
            modelAndView.addObject("empresa", empresa);
            return modelAndView;
        } else {
            empresaDAO.alterarEmpresa(login, empresa);
            return new ModelAndView("redirect:/empresas");
        }
    }

    @GetMapping(path = "/{login}/delete")
    public ModelAndView deleteEmpresa(@PathVariable String login) {
        empresaDAO.excluirEmpresa(login);
        return new ModelAndView("redirect:/empresas");
    }
}