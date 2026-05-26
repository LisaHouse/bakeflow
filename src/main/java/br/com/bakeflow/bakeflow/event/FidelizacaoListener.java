package br.com.bakeflow.bakeflow.event;

import br.com.bakeflow.bakeflow.client.FidelizacaoClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FidelizacaoListener {

    private final FidelizacaoClient fidelizacaoClient;

    public FidelizacaoListener(FidelizacaoClient fidelizacaoClient) {
        this.fidelizacaoClient = fidelizacaoClient;
    }

    @EventListener
    public void aoFinalizarPedido(PedidoFinalizadoEvent event) {
        fidelizacaoClient.enviarPedidoFinalizado(event);
    }
}
