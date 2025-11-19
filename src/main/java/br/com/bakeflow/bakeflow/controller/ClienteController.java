package br.com.bakeflow.bakeflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import br.com.bakeflow.bakeflow.model.Cliente;
import br.com.bakeflow.bakeflow.repository.ClienteRepository;
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

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public String showForm(Model model) {
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }
        return "cadastroCliente";
    }

    @PostMapping
    @Operation(summary = "endpoint para cadastro de cliente")
    public String submitForm(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            // Repassa o objeto cliente e os erros usando flash attributes (se for necessário redirecionar)
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.cliente", result);
            attributes.addFlashAttribute("cliente", cliente);
            return "redirect:/cadastroCliente";
            // alternativa sem redirect (melhor para mostrar erros imediatamente):
            // return "cadastroCliente";
        }

        clienteRepository.save(cliente);
        attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/cadastroCliente";
    }
}
