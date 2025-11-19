package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.service.PedidoService;
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
@RequestMapping("/cadastropedido")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public String showForm(Model model) {
        if (!model.containsAttribute("pedido")) {
            model.addAttribute("pedido", new Pedido());
        }
        return "cadastroPedido";
    }

    @PostMapping
    @Operation(summary = "endpoint para cadastro de pedido")
    public String submitForm(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.estoque", result);
            attributes.addFlashAttribute("pedido", pedido);
            return "redirect:/cadastroEstoque";
        }

        service.save(pedido);
        attributes.addFlashAttribute("mensagem", "estoque cadastrado com sucesso!");
        return "redirect:/cadastroPedido";
    }
}
