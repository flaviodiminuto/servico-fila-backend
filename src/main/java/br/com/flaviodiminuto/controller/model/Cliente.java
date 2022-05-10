package br.com.flaviodiminuto.controller.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;

@Builder
@JsonRootName("cliente")
public class Cliente {
    public final Long id;

    public final String nome;
}
