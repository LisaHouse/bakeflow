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
import br.com.bakeflow.bakeflow.model.Cliente;
import br.com.bakeflow.bakeflow.repository.ClienteRepository;

@Controller
public class ClienteController {

    @Autowired
    public ClienteRepository vr;

    @GetMapping("/cadastrarCliente")

    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("cadastro/formCliente");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

     @RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)

     public String form(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
         ModelAndView mv = new ModelAndView("cadastro/formCliente");

         if (result.hasErrors()) {
             attributes.addFlashAttribute("mensagem", "Verifique os campos...");

             return "redirect:/cadastrarCliente";
         }

     }
    
     vr.save(cliente);
     attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
     return "redirect:/cadastrarCliente";
}
