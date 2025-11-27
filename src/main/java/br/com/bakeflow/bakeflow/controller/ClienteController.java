package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Endereco;
import br.com.bakeflow.bakeflow.service.ClienteService;
import br.com.bakeflow.bakeflow.service.EnderecoService;
import jakarta.validation.Valid;
import br.com.bakeflow.bakeflow.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastroCliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastroCliente";
    }

    @GetMapping("/relatorio")
    public String listar(Model model) {
        model.addAttribute("cliente", clienteService.findAll());
        return "cadastroCliente";
    }

    @PostMapping
    public String submitForm(@Valid Cliente cliente,
                             BindingResult result,
                             RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.cliente", result);
            attributes.addFlashAttribute("cliente", cliente);
            return "redirect:/cadastroCliente";
        }


        Endereco endereco = cliente.getEndereco();

        // API
        Endereco apiEndereco = enderecoService.buscarCep(endereco.getCep());
        endereco.setLogradouro(apiEndereco.getLogradouro());
        endereco.setBairro(apiEndereco.getBairro());
        endereco.setCidade(apiEndereco.getCidade());
        endereco.setEstado(apiEndereco.getEstado());

        clienteService.save(cliente);

        attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/cadastroCliente";
    }
}

