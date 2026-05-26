package br.com.bakeflow.bakeflow.service;

import br.com.bakeflow.bakeflow.event.PedidoFinalizadoEvent;
import br.com.bakeflow.bakeflow.model.FidelizacaoCliente;
import br.com.bakeflow.bakeflow.repository.FidelizacaoClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FidelizacaoService {

    private final FidelizacaoClienteRepository repository;

    public FidelizacaoService(FidelizacaoClienteRepository repository) {
        this.repository = repository;
    }

    public List<FidelizacaoCliente> findAll() {
        return repository.findAll();
    }

    public void processarPedidoFinalizado(PedidoFinalizadoEvent event) {
        int pontosGanhos = event.getValorTotal().intValue();

        FidelizacaoCliente fidelizacao = repository
                .findByClienteId(event.getClienteId())
                .orElseGet(() -> {
                    FidelizacaoCliente novo = new FidelizacaoCliente();
                    novo.setClienteId(event.getClienteId());
                    novo.setClienteNome(event.getClienteNome());
                    novo.setPontos(0);
                    novo.setBeneficiosGerados(0);
                    return novo;
                });

        int novosPontos = fidelizacao.getPontos() + pontosGanhos;

        while (novosPontos >= 100) {
            fidelizacao.setBeneficiosGerados(fidelizacao.getBeneficiosGerados() + 1);
            novosPontos -= 100;
        }

        fidelizacao.setPontos(novosPontos);

        repository.save(fidelizacao);
    }
}
