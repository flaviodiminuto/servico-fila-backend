package br.com.flaviodiminuto.dataprovider.repository;

import br.com.flaviodiminuto.controller.model.Pedido;
import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepositoryBase<PedidoEntity, Long> {

    public void atualizaStatusPedido(PedidoEntity pedido){
        update("status_na_fila = ?1 where id = ?2", pedido.getStatusPedidoNaFila().ordinal(), pedido.getId());
    }

    public List<PedidoEntity> findByStatus(int status, Long filaId) {
        String field = "data_hora_solicitacao";
        switch (status){
            case 0:
                field = "data_hora_solicitacao";
                break;
            case 1:
                field = "data_hora_preparo";
                break;
            case 2:
                field = "data_hora_entrega";
                break;
        }

        return list(String.format("status_na_fila = ?1  and fila_id = ?2 order by %s desc", field),
                status,
                filaId);
    }
}
