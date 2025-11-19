package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Item_Pedido;
import br.com.bakeflow.bakeflow.service.Item_PedidoService;
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
@RequestMapping("/cadastroPedido")
public class Item_PedidoController {

    private final Item_PedidoService service;

    public Item_PedidoController(Item_PedidoService service) {
        this.service = service;
    }


    @GetMapping
    public String showForm(Model model) {
        if (!model.containsAttribute("item_Pedido")) {
            model.addAttribute("item_Pedido", new Item_Pedido());
        }
        return "cadastroItem_Pedido";
    }

    @PostMapping
    @Operation(summary = "endpoint para cadastro de item_Pedido")
    public String submitForm(@Valid Item_Pedido item_Pedido, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.item_Pedido", result);
            attributes.addFlashAttribute("item_Pedido", item_Pedido);
            return "redirect:/cadastroItem_Pedido";
        }

        service.save(item_Pedido);
        attributes.addFlashAttribute("mensagem", "estoque cadastrado com sucesso!");
        return "redirect:/cadastroEstoque";
    }
}
