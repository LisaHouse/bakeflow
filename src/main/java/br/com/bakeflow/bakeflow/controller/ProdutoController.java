package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.model.Produto;
import br.com.bakeflow.bakeflow.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastroProduto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastroProduto";
    }

    @GetMapping("/relatorio")
    public String listar(Model model) {
        model.addAttribute("produto", produtoService.findAll());
        return "relatorio/listaProduto";
    }

    @PostMapping
    public String salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.produto", result);
            attributes.addFlashAttribute("produto", produto);
            return "redirect:/cadastroProduto";
        }

        produtoService.save(produto);

        attributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
        return "redirect:/cadastroProduto";
    }
}
