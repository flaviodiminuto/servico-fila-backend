package br.com.flaviodiminuto.controller;

import br.com.flaviodiminuto.controller.mapper.PedidoMapper;
import br.com.flaviodiminuto.controller.model.Pedido;
import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import br.com.flaviodiminuto.usecase.*;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.hibernate.validator.constraints.Range;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Path("/pedidos")
public class PedidosResource {

    @Inject
    PedidoAtualizaStatusUseCase  pedidoAtualizaStatusUseCase;
    @Inject
    FilaAdicionaPedidoUseCase adicionaPedidoUseCase;
    @Inject
    FilaFindByDataUsecase filaFindByDataUsecase;
    @Inject
    FilaNovaSaveUsecase filaNovaSaveUsecase;


    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizaPedido(@PathParam("id") Long pedidoId){

        PedidoEntity pedidoRetorno = pedidoAtualizaStatusUseCase.execute(pedidoId);
        if(pedidoRetorno == null)
            return Response.notModified().build();
        else
            return Response.ok(pedidoRetorno).build();
    }



    @POST
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
