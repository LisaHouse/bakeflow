package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.model.Cliente;
import br.com.bakeflow.bakeflow.model.Item_Pedido;
import br.com.bakeflow.bakeflow.model.Pedido;
import br.com.bakeflow.bakeflow.service.ClienteService;
import br.com.bakeflow.bakeflow.service.PedidoService;
import br.com.bakeflow.bakeflow.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoController(PedidoService pedidoService,
                            ClienteService clienteService,
                            ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    // RELATÓRIO
    @GetMapping("/relatorio")
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.findAll());
        return "relatorio/listaPedido";
    }

    // NOVO PEDIDO
    @GetMapping("/novo")
    public String novoPedido(Model model) {

        if (!model.containsAttribute("pedido")) {
            Pedido p = new Pedido();
            p.setStatus("Aberto"); // ← PADRÃO
            p.adicionarItemVazio();
            model.addAttribute("pedido", p);
        }

        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("produtos", produtoService.findAll());

        return "cadastroPedido";
    }

    // EDITAR PEDIDO
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes attributes) {

        Pedido pedido = pedidoService.findById(id);

        if (pedido == null) {
            attributes.addFlashAttribute("erro", "Pedido não encontrado.");
            return "redirect:/pedidos/relatorio";
        }

        // se o pedido não tiver itens → adiciona um vazio
        if (pedido.getItens().isEmpty()) {
            pedido.adicionarItemVazio();
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("produtos", produtoService.findAll());

        return "cadastroPedido";
    }

    // SALVAR (novo ou edição)
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Pedido pedido,
                         BindingResult result,
                         RedirectAttributes attributes,
                         Model model) {

        boolean editando = pedido.getIdPedido() != null;

        if (pedido.getDataAtualizacao() == null) {
            pedido.setDataAtualizacao(new Date());
        }

        // Remover itens vazios
        Iterator<Item_Pedido> it = pedido.getItens().iterator();
        while (it.hasNext()) {
            Item_Pedido item = it.next();

            if (item.getProduto() == null ||
                    item.getProduto().getIdProduto() == null ||
                    item.getQuantidade() == null ||
                    item.getQuantidade() <= 0) {
                it.remove();
            } else {
                item.setPedido(pedido);
            }
        }

        if (pedido.getItens().isEmpty()) {
            attributes.addFlashAttribute("erro", "Adicione ao menos 1 item ao pedido.");
            attributes.addFlashAttribute("pedido", pedido);
            return "redirect:/pedidos/novo";
        }

        if (result.hasErrors()) {
            attributes.addFlashAttribute("pedido", pedido);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.pedido", result);
            return editando ? "redirect:/pedidos/editar/" + pedido.getIdPedido()
                    : "redirect:/pedidos/novo";
        }

        // vincular cliente completo
        Cliente c = clienteService.findById(pedido.getCliente().getIdCliente());
        pedido.setCliente(c);

        try {
            pedidoService.save(pedido);
        } catch (RuntimeException e) {
            attributes.addFlashAttribute("erro", e.getMessage());
            return editando ? "redirect:/pedidos/editar/" + pedido.getIdPedido()
                    : "redirect:/pedidos/novo";
        }

        attributes.addFlashAttribute("mensagem",
                editando ? "Pedido atualizado com sucesso!"
                        : "Pedido cadastrado com sucesso!");

        return "redirect:/pedidos/relatorio";
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {

        try {
            pedidoService.delete(id);
            attr.addFlashAttribute("mensagem", "Pedido excluído com sucesso!");
        }
        catch (RuntimeException ex) {
            attr.addFlashAttribute("erro", ex.getMessage());
        }

        return "redirect:/pedidos/relatorio";
    }

}
