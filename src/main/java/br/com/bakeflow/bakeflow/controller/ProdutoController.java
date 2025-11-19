package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.model.Produto;
import br.com.bakeflow.bakeflow.repository.EstoqueRepository;
import br.com.bakeflow.bakeflow.repository.ProdutoRepository;
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
@RequestMapping("/cadastroproduto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public String showForm(Model model) {
        if (!model.containsAttribute("produto")) {
            model.addAttribute("produto", new Estoque());
        }
        return "cadastroProduto";
    }

    @PostMapping
    @Operation(summary = "endpoint para produto")
    public String submitForm(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.produto", result);
            attributes.addFlashAttribute("produto", produto);
            return "redirect:/cadastroProduto";
        }

        produtoRepository.save(produto);
        attributes.addFlashAttribute("mensagem", "produto cadastrado com sucesso!");
        return "redirect:/cadastroProduto";
    }
}
