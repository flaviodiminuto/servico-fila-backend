package br.com.flaviodiminuto.usecase;

import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import br.com.flaviodiminuto.dataprovider.repository.FilaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FilaAdicionaPedidoUseCase {
    @Inject
    FilaRepository repository;

    public FilaEntity executar(FilaEntity fila, PedidoEntity pedido){

        fila.getPedidos().add(pedido);
        repository.getEntityManager().merge(fila);
        return fila;
    }
}
