package br.com.flaviodiminuto.controller.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
@JsonRootName("fila")
public class Fila {
    private final LocalDate data;
    private final List<Pedido> pedidos;
}
