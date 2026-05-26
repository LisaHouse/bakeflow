package br.com.bakeflow.bakeflow.event;

import br.com.bakeflow.bakeflow.service.FidelizacaoService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FidelizacaoListener {

    private final FidelizacaoService fidelizacaoService;

    public FidelizacaoListener(FidelizacaoService fidelizacaoService) {
        this.fidelizacaoService = fidelizacaoService;
    }

    @EventListener
    public void aoFinalizarPedido(PedidoFinalizadoEvent event) {
        fidelizacaoService.processarPedidoFinalizado(event);
    }
}
