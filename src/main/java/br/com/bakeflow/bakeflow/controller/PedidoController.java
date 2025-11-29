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

    // lista pedidos com excluir ou alterar
    @GetMapping("/relatorio")
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.findAll());
        return "relatorio/listaPedido";
    }

    // incluir pedido
    @GetMapping("/novo")
    public String novo(Model model) {

        Pedido pedido = new Pedido();


        pedido.getItens().add(new Item_Pedido());

        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("produtos", produtoService.findAll());

        return "pedido/form";
    }

    // salvar pedido
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Pedido pedido,
                         BindingResult result,
                         RedirectAttributes attributes,
                         Model model) {

        //ajuste da data do pedido
        if (pedido.getDataAtualizacao() == null) {
            pedido.setDataAtualizacao(new Date());
        }

        //tratamento de inserir pedido sem produto selecionado
        Iterator<Item_Pedido> it = pedido.getItens().iterator();
        while (it.hasNext()) {
            Item_Pedido item = it.next();

            if (item.getProduto() == null ||
                    item.getProduto().getIdProduto() == null ||
                    item.getQuantidade() == null ||
                    item.getQuantidade() <= 0) {
                it.remove();
            } else {
                // vincular item ↦ pedido
                item.setPedido(pedido);
            }
        }

        if (pedido.getItens().isEmpty()) {
            attributes.addFlashAttribute("erro", "Adicione ao menos 1 item ao pedido.");
            return "redirect:/pedidos/novo";
        }

        if (result.hasErrors()) {
            attributes.addFlashAttribute("pedido", pedido);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.pedido", result);
            return "redirect:/pedidos/novo";
        }

        //busca cliente
        Cliente c = clienteService.findById(pedido.getCliente().getIdCliente());
        pedido.setCliente(c);

        //salvar pedido
        try {
            pedidoService.save(pedido);
        } catch (RuntimeException e) {
            attributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/pedidos/novo";
        }

        attributes.addFlashAttribute("mensagem", "Pedido cadastrado com sucesso!");
        return "redirect:/pedidos/novo";
    }
}
