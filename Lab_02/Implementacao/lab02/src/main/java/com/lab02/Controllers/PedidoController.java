package com.lab02.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lab02.Classes.GerenciamentoDeAlugueis.Pedido;
import com.lab02.Classes.Perfis.Cliente;
import com.lab02.Models.PedidoDAO;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private PedidoDAO pedidoDAO = new PedidoDAO();

    @GetMapping
    public ModelAndView getPedido(Cliente cliente) {
        ModelAndView modelAndView = new ModelAndView("Pedidos/index");
        modelAndView.addObject("pedidos", pedidoDAO.getPedidos().stream()
        .filter(p -> p.getLocatario().equals(cliente))
        .toList()
        );
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView createPedido(Cliente cliente, @Valid Pedido pedido, BindingResult result) { 
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Pedidos/create");
            return modelAndView;
        } else {
            pedidoDAO.criarPedido(pedido);
            return new ModelAndView("redirect:/clientes");
        }
    }
}