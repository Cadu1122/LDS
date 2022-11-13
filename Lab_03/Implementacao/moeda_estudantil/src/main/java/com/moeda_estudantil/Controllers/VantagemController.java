package com.moeda_estudantil.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moeda_estudantil.Models.VantagemDAO;

@Controller
@RequestMapping("/vantagens")
public class VantagemController {
    
    @GetMapping
    public ModelAndView getVantagens() {
        ModelAndView modelAndView = new ModelAndView("Alunos/vantagemList");
        modelAndView.addObject("vantagens", VantagemDAO.getInstance().getVantagens());
        return modelAndView;
    }
}
