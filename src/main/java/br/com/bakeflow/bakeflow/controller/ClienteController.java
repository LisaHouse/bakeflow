package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.service.ClienteService;
import jakarta.validation.Valid;
import br.com.bakeflow.bakeflow.model.Cliente;
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

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public String showForm(Model model) {
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }
        return "cadastroCliente";
    }

    @PostMapping
    public String submitForm(@Valid Cliente cliente, BindingResult result,
                             RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.cliente", result);
            attributes.addFlashAttribute("cliente", cliente);
            return "redirect:/cadastroCliente";
        }

        service.save(cliente);
        attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/cadastroCliente";
    }
}
