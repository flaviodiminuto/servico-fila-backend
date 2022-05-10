package br.com.flaviodiminuto.controller;

import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import br.com.flaviodiminuto.usecase.PedidoAtualizaStatusUseCase;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/pedidos")
public class PedidosResource {

    @Inject
    PedidoAtualizaStatusUseCase  pedidoAtualizaStatusUseCase;

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
}
