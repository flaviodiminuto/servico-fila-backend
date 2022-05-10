package br.com.flaviodiminuto.controller;

import br.com.flaviodiminuto.controller.mapper.FilaMapper;
import br.com.flaviodiminuto.controller.mapper.PedidoMapper;
import br.com.flaviodiminuto.controller.model.Pedido;
import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import br.com.flaviodiminuto.usecase.FilaAdicionaPedidoUseCase;
import br.com.flaviodiminuto.usecase.FilaFindByDataUsecase;
import br.com.flaviodiminuto.usecase.FilaNovaSaveUsecase;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Path("/filas")
public class FilasResource {

    DateTimeFormatter dataBrFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Inject
    public FilaFindByDataUsecase filaFindByDataUsecase;
    @Inject
    public FilaNovaSaveUsecase filaNovaSaveUsecase;
    @Inject
    public FilaAdicionaPedidoUseCase adicionaPedidoUseCase;

    @GET
    public Response listaAsUltimasCemFilas() {
        var fila = filaFindByDataUsecase.execute(LocalDate.now());
        return Response.ok(fila.orElse(null)).build();
    }

    @POST
    @Transactional
    public Response adicionarFila() {
        FilaEntity fila = filaNovaSaveUsecase.execute(LocalDate.now());
        return Response.ok(FilaMapper.toModel(fila)).build();
    }

    @POST
    @Path("/pedidos")
    @Transactional
    public Response adicionarPedido( @Valid Pedido pedido){
        pedido.dataHoraSolicitacao = LocalDateTime.now();
        pedido.nome_cliente = pedido.nome_cliente.toUpperCase();
        FilaEntity fila;
        Optional<FilaEntity> filaOptional = filaFindByDataUsecase.execute(LocalDate.now());
        fila = filaOptional.orElse(filaNovaSaveUsecase.execute(LocalDate.now()));
        adicionaPedidoUseCase.executar(fila, PedidoMapper.toEntity(pedido));
        return Response.status(Response.Status.CREATED).build();
    }
}