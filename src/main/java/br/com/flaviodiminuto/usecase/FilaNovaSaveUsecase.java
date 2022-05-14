package br.com.flaviodiminuto.usecase;

import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import br.com.flaviodiminuto.dataprovider.repository.FilaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
public class FilaNovaSaveUsecase {

    @Inject
    FilaRepository repository;

    public FilaEntity execute(LocalDate data){
        Optional<FilaEntity> filaPersistida = repository.findByDataOptional(data);
        if(filaPersistida.isPresent()) return filaPersistida.get();

        FilaEntity fila = FilaEntity.builder()
                .data(data)
                .pedidos(new ArrayList<>())
                .build();
        return repository.save(fila);
    }
}
