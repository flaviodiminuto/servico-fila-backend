package br.com.flaviodiminuto.usecase;

import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import br.com.flaviodiminuto.dataprovider.repository.FilaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Optional;

@ApplicationScoped
public class FilaFindByDataUsecase {

    @Inject
    FilaRepository repository;

    public Optional<FilaEntity> execute(LocalDate data){

        return repository.findByDataOptional(data);
    }
}
