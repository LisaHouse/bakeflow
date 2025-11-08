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
import br.com.bakeflow.bakeflow.model.Estoque;
import br.com.bakeflow.bakeflow.repository.EstoqueRepository;

@Controller
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping("/cadastrarEstoque")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("cadastro/formEstoque");
        mv.addObject("estoque", new Estoque());
        return mv;
    }

    @RequestMapping(value = "/cadastrarEstoque", method = RequestMethod.POST)
    public String form(@Valid Estoque estoque, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarEstoque";
        }

        estoqueRepository.save(estoque);
        attributes.addFlashAttribute("mensagem", "Item de estoque cadastrado com sucesso!");
        return "redirect:/cadastrarEstoque";
    }
}