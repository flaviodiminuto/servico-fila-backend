package br.com.flaviodiminuto.dataprovider.repository;

import br.com.flaviodiminuto.Status;
import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepositoryBase<PedidoEntity, Long> {

    public void atualizaStatusPedido(PedidoEntity pedido){
        update("status_na_fila = ?1 where id = ?2", pedido.getStatusNaFila().ordinal(), pedido.getId());
    }
}
