package com.lab02.Controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lab02.Classes.Cliente;
import com.lab02.DTOS.ClienteDTO;
import com.lab02.Models.UsuarioDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping(path = "/clientes")
public class ClienteController {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @GetMapping
    public ModelAndView getUsuario(ClienteDTO clienteDTO) {
        ModelAndView modelAndView = new ModelAndView("Clientes/index");
        modelAndView.addObject("clientes", usuarioDAO.getUsuarios().stream()
        .filter(u -> u instanceof Cliente)
        .map(u -> (Cliente) u)
        .toList());
        return modelAndView;
    }

    @GetMapping(path = "/new")
    public ModelAndView postCliente() {
        ModelAndView modelAndView = new ModelAndView("Clientes/create");
        modelAndView.addObject("clienteDTO", new ClienteDTO());
        return modelAndView;
    }

    @GetMapping(path = "/{id}/edit")
    public ModelAndView putUsuario(@PathVariable Integer id) {
        Cliente cliente = (Cliente) usuarioDAO.encontrarUsuario(id);
        if (cliente == null) {
            return new ModelAndView("redirect:/clientes");
        } else {
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            ModelAndView modelAndView = new ModelAndView("Clientes/update");
            modelAndView.addObject("clienteDTO", clienteDTO);
            modelAndView.addObject("clienteID", cliente.getId());
            return modelAndView;
        }
    }

    @PostMapping
    public ModelAndView createCliente(@Valid ClienteDTO clienteDTO, BindingResult result) { 
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Clientes/create");
            return modelAndView;
        } else {
            usuarioDAO.criarUsuario(clienteDTO.toCliente());
            return new ModelAndView("redirect:/clientes");
        }
    }

    @PostMapping(path = "/{id}/edit")
    public ModelAndView updateCliente(@PathVariable Integer id, @Valid ClienteDTO clienteDTO, BindingResult result) {
        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("Clientes/" + id + "/edit");
            modelAndView.addObject("clienteDTO", clienteDTO);
            modelAndView.addObject("clienteID", id);
            return modelAndView;
        } else {
            usuarioDAO.alterarCliente(id, clienteDTO.toCliente());
            return new ModelAndView("redirect:/clientes");
        }
    }

    @GetMapping(path = "/{id}/delete")
    public ModelAndView deleteCliente(@PathVariable Integer id) {
        usuarioDAO.excluirUsuario(id);
        return new ModelAndView("redirect:/clientes");
    }
}
