package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.repository.EstoqueRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastroestoque")
public class EstoqueController {

    private final EstoqueRepository estoqueRepository;

    public EstoqueController(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    @GetMapping
    public String showForm(Model model) {
        if (!model.containsAttribute("estoque")) {
            model.addAttribute("estoque", new Estoque());
        }
        return "cadastroEstoque";
    }

    @PostMapping
    @Operation(summary = "endpoint para cadastro de estoque")
    public String submitForm(@Valid Estoque estoque, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.estoque", result);
            attributes.addFlashAttribute("estoque", estoque);
            return "redirect:/cadastroEstoque";
        }

        estoqueRepository.save(estoque);
        attributes.addFlashAttribute("mensagem", "estoque cadastrado com sucesso!");
        return "redirect:/cadastroEstoque";
    }
}
