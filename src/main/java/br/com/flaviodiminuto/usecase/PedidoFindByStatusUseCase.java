package br.com.flaviodiminuto.usecase;

import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import br.com.flaviodiminuto.dataprovider.repository.PedidoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PedidoFindByStatusUseCase {

    @Inject
    PedidoRepository repository;

    public List<PedidoEntity> executar(int status, Long filaId){
        return repository.findByStatus(status, filaId);
    }

}
