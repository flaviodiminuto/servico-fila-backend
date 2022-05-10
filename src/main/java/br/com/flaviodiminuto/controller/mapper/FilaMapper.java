package br.com.flaviodiminuto.controller.mapper;

import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import br.com.flaviodiminuto.controller.model.Fila;

import java.util.stream.Collectors;

public class FilaMapper {
    public static Fila toModel(FilaEntity entity){
        return Fila.builder().data(entity.getData())
                .pedidos(entity.getPedidos()
                        .stream()
                        .map(PedidoMapper::toModel)
                        .collect(Collectors.toList()))
                .build();
    }
}
