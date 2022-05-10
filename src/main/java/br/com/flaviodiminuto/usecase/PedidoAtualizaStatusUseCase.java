package br.com.flaviodiminuto.usecase;

import br.com.flaviodiminuto.StatusPedido;
import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import br.com.flaviodiminuto.dataprovider.repository.PedidoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
public class PedidoAtualizaStatusUseCase {

    @Inject
    PedidoRepository repository;

    public PedidoEntity execute(Long pedidoId){
        Optional<PedidoEntity> pedidoPersistido = repository.findByIdOptional(pedidoId);
        if(pedidoPersistido.isEmpty()) return null;
        PedidoEntity pedido = pedidoPersistido.get();

        if (pedido.getStatusPedidoNaFila() == StatusPedido.EM_PREPARACAO) {
            pedido.setStatusPedidoNaFila(StatusPedido.AGUARDANDO_RETIRADA);
            pedido.setDataHoraPreparo(LocalDateTime.now());
        } else if(pedido.getStatusPedidoNaFila() == StatusPedido.AGUARDANDO_RETIRADA){
            pedido.setStatusPedidoNaFila(StatusPedido.PEDIDO_RETIRADO);
            pedido.setDataHoraEntrega(LocalDateTime.now());
        }
        repository.atualizaStatusPedido(pedido);
        return pedido;
    }
}
