package br.com.flaviodiminuto.dataprovider.repository;

import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Optional;

@ApplicationScoped
public class FilaRepository implements PanacheRepositoryBase<FilaEntity, Long> {
    private Logger logger = LoggerFactory.getLogger( getClass().getName());

    public FilaEntity save(FilaEntity entity){
        if(entity == null) {
            logger.warn("Fila informada é nula");
            return null;
        }

        LocalDate data = entity.getData() == null?
                LocalDate.now(): entity.getData();

        if(findByDataOptional(data).isPresent()) {
            logger.warn("Fila já existe " + entity);
            return entity;
        } else {
            logger.warn("Salvando nova fila " + entity);
            entity.persist();
            return entity;
        }
    }

    public Optional<FilaEntity> findByDataOptional(LocalDate data) {
        return find("data", data).firstResultOptional();
    }
}
