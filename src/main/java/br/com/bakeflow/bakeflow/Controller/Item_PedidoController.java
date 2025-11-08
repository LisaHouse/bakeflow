package br.com.bakeflow.bakeflow.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.bakeflow.bakeflow.model.Item_Pedido;
import br.com.bakeflow.bakeflow.repository.Item_PedidoRepository;

@Controller
public class Item_PedidoController {

    @Autowired
    private Item_PedidoRepository itemPedidoRepository;

    @GetMapping("/cadastrarItemPedido")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("cadastro/formItemPedido");
        mv.addObject("itemPedido", new Item_Pedido());
        return mv;
    }

    @RequestMapping(value = "/cadastrarItemPedido", method = RequestMethod.POST)
    public String form(@Valid Item_Pedido itemPedido, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarItemPedido";
        }

        itemPedidoRepository.save(itemPedido);
        attributes.addFlashAttribute("mensagem", "Item do pedido cadastrado com sucesso!");
        return "redirect:/cadastrarItemPedido";
    }
}