package br.com.flaviodiminuto.controller.mapper;

import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import br.com.flaviodiminuto.controller.model.Pedido;

public class PedidoMapper {
    public static Pedido toModel(PedidoEntity entity){
        return Pedido.builder()
                .id(entity.getId())
                .nome_cliente(entity.getNomeCliente())
                .dataHoraSolicitacao(entity.getDataHoraSolicitacao())
                .dataHoraPreparo(entity.getDataHoraPreparo())
                .dataHoraEntrega(entity.getDataHoraEntrega())
                .statusNaFila(entity.getStatusNaFila())
                .build();
    }

    public static PedidoEntity toEntity(Pedido model){
        return PedidoEntity.builder()
                .id(model.id)
                .nomeCliente(model.nome_cliente)
                .dataHoraSolicitacao(model.dataHoraSolicitacao)
                .dataHoraPreparo(model.dataHoraPreparo)
                .dataHoraEntrega(model.dataHoraEntrega)
                .statusNaFila(model.statusNaFila)
                .build();
    }
}
