package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.model.Item_Pedido;
import br.com.bakeflow.bakeflow.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", service.findAll());
        return "pedido/lista";
    }

    // FORM PARA NOVO
    @GetMapping("/novo")
    public String novo(Model model) {
        if (!model.containsAttribute("pedido")) {
            Pedido pedido = new Pedido();
            // garante 1 linha de item para o formulário
            pedido.getItens().add(new Item_Pedido());
            model.addAttribute("pedido", pedido);
        }
        return "pedido/form";
    }


    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        Pedido pedido = service.findById(id);
        if (pedido == null) {
            attributes.addFlashAttribute("erro", "Pedido não encontrado");
            return "redirect:/pedidos";
        }
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            pedido.getItens().add(new Item_Pedido());
        }
        model.addAttribute("pedido", pedido);
        return "pedido/form";
    }


    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Pedido pedido, BindingResult result, RedirectAttributes attributes) {

        // validação simples: garantir pelo menos um item
        if (pedido.getItens() == null || pedido.getItens().stream().allMatch(i ->
                (i.getQuantidade() == null || i.getQuantidade() <= 0) &&
                        (i.getValor() == null || i.getValor().compareTo(java.math.BigDecimal.ZERO) <= 0)
        )) {
            result.reject("itens.empty", "Adicione ao menos 1 item válido ao pedido.");
        }

        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.pedido", result);
            attributes.addFlashAttribute("pedido", pedido);
            return "redirect:/pedidos/novo";
        }

        service.save(pedido);
        attributes.addFlashAttribute("mensagem", "Pedido salvo com sucesso!");
        return "redirect:/pedidos";
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        service.delete(id);
        attributes.addFlashAttribute("mensagem", "Pedido removido com sucesso!");
        return "redirect:/pedidos";
    }
}
