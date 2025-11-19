package br.com.bakeflow.bakeflow.Controller;

import br.com.bakeflow.bakeflow.repository.EnderecoRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.bakeflow.bakeflow.model.Endereco;
import br.com.bakeflow.bakeflow.repository.ClienteRepository;

@Controller
public class EnderecoController {

    @Autowired
    public EnderecoRepository vr;

    @GetMapping("/cadastroEndereco")

    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("cadastroEndereco");
        mv.addObject("endereco", new Endereco());
        return mv;
    }

    @RequestMapping(value = "/cadastroEndereco", method = RequestMethod.POST)
    @Operation(summary = "endpoint para cadastro de endereco")

    public String form(@Valid Endereco endereco, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastroEndereco";
        }

        vr.save(endereco);
        attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/cadastroEndereco";
    }
}
