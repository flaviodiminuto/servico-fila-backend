package br.com.flaviodiminuto.usecase;

import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import br.com.flaviodiminuto.dataprovider.repository.FilaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;

@ApplicationScoped
public class FilaNovaSaveUsecase {

    @Inject
    FilaRepository repository;

    public FilaEntity execute(LocalDate data){
        FilaEntity fila = FilaEntity.builder()
                .data(data)
                .pedidos(new ArrayList<>())
                .build();
        repository.save(fila);
        return fila;
    }
}
