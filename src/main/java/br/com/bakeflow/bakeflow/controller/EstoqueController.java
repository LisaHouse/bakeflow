package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.model.Produto;
import br.com.bakeflow.bakeflow.service.EstoqueService;
import br.com.bakeflow.bakeflow.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastroEstoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String novo(Model model) {

        model.addAttribute("estoque", new Estoque());
        model.addAttribute("produtos", produtoService.findAll());

        return "cadastroEstoque";
    }


    @PostMapping
    public String salvar(@Valid Estoque estoque, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.estoque", result);
            attributes.addFlashAttribute("estoque", estoque);
            return "redirect:/cadastroEstoque";
        }

        Long idProduto = estoque.getProduto().getIdProduto();
        Produto produto = produtoService.findById(idProduto);
        estoque.setProduto(produto);

        Estoque estoqueExistente = estoqueService.buscarPorProduto(idProduto);

        if (estoqueExistente != null) {
            estoqueExistente.setQuantidade(estoque.getQuantidade());
            estoqueService.save(estoqueExistente);

            attributes.addFlashAttribute("mensagem", "Estoque atualizado com sucesso!");
        } else {
            estoqueService.save(estoque);
            attributes.addFlashAttribute("mensagem", "Estoque cadastrado com sucesso!");
        }

        return "redirect:/cadastroEstoque";
    }



}

