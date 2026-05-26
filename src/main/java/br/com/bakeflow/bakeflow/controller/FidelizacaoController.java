package br.com.bakeflow.bakeflow.controller;

import br.com.bakeflow.bakeflow.service.FidelizacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fidelizacao")
public class FidelizacaoController {

    private final FidelizacaoService fidelizacaoService;

    public FidelizacaoController(FidelizacaoService fidelizacaoService) {
        this.fidelizacaoService = fidelizacaoService;
    }

    @GetMapping("/relatorio")
    public String listar(Model model) {
        model.addAttribute("fidelizacoes", fidelizacaoService.findAll());
        return "relatorio/listaFidelizacao";
    }
}
