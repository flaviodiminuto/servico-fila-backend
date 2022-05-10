package br.com.flaviodiminuto.controller.model;

import br.com.flaviodiminuto.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@JsonRootName("pedido")
public class Pedido{

    @JsonProperty("id")
    public final Long id;

    @JsonProperty("nome_cliente")
    @NotBlank
    public String nome_cliente;

    @JsonProperty("status_na_fila")
    public Status statusNaFila;

    @JsonProperty("data_hora_solicitacao")
    public LocalDateTime dataHoraSolicitacao;

    @JsonProperty("data_hora_preparo")
    public final LocalDateTime dataHoraPreparo;

    @JsonProperty("data_hora_entrega")
    public final LocalDateTime dataHoraEntrega;
}
