package com.lab02.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lab02.Classes.GerenciamentoDeAlugueis.Automovel;
import com.lab02.Classes.GerenciamentoDeAlugueis.Pedido;
import com.lab02.Classes.Perfis.Cliente;
import com.lab02.Classes.Perfis.Usuario;
import com.lab02.Models.AutomovelDAO;
import com.lab02.Models.PedidoDAO;
import com.lab02.Models.UsuarioDAO;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private PedidoDAO pedidoDAO = new PedidoDAO();

    @GetMapping("/{id}")
    public ModelAndView getPedido(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Pedidos/index");
        modelAndView.addObject("pedidos", pedidoDAO.getPedidos().stream()
        .filter(p -> p.getLocatario().getId().equals(id))
        .toList());
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView criarPedido() {
        ModelAndView modelAndView = new ModelAndView("Pedidos/create");
        return modelAndView;
    }
    
    @PostMapping
    public ModelAndView createPedido(Integer id, Integer idAutomovel) { 
        Usuario usuario = UsuarioDAO.encontrarUsuario(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/pedidos/" + id);
        Automovel automovel = AutomovelDAO.encontrarAutomovel(idAutomovel);
        if(automovel != null && usuario != null && usuario instanceof Cliente) {
            pedidoDAO.criarPedido(new Pedido((Cliente) usuario, automovel));
        }
        return modelAndView;
    }
}