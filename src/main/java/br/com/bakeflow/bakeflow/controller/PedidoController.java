package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.model.Item_Pedido;
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
@RequestMapping("/cadastroPedido")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public String showForm(Model model) {

        // Caso a tela seja carregada pela primeira vez
        if (!model.containsAttribute("pedido")) {
            Pedido pedido = new Pedido();

            // Garante ao menos 1 item no formulário
            pedido.getItens().add(new Item_Pedido());

            model.addAttribute("pedido", pedido);
        }

        return "cadastroPedido";
    }

    @PostMapping
    @Operation(summary = "endpoint para cadastro de pedido")
    public String submitForm(
            @Valid Pedido pedido,
            BindingResult result,
            RedirectAttributes attributes
    ) {
        // Validação do Pedido + Itens
        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.pedido", result);
            attributes.addFlashAttribute("pedido", pedido);
            return "redirect:/cadastroPedido";
        }

        // Associa o pedido a cada item antes de salvar
        pedido.getItens().forEach(item -> item.setPedido(pedido));

        service.save(pedido);

        attributes.addFlashAttribute("mensagem", "Pedido cadastrado com sucesso!");
        return "redirect:/cadastroPedido";
    }
}
