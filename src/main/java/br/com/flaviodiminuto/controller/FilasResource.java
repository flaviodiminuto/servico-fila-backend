package br.com.flaviodiminuto.controller;

import br.com.flaviodiminuto.controller.mapper.FilaMapper;
import br.com.flaviodiminuto.controller.mapper.PedidoMapper;
import br.com.flaviodiminuto.controller.model.Pedido;
import br.com.flaviodiminuto.dataprovider.entity.FilaEntity;
import br.com.flaviodiminuto.dataprovider.entity.PedidoEntity;
import br.com.flaviodiminuto.usecase.FilaFindByDataUsecase;
import br.com.flaviodiminuto.usecase.FilaNovaSaveUsecase;
import br.com.flaviodiminuto.usecase.PedidoFindByStatusUseCase;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.hibernate.validator.constraints.Range;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Path("/filas")
public class FilasResource {

    @Inject
    public FilaFindByDataUsecase filaFindByDataUsecase;
    @Inject
    public FilaNovaSaveUsecase filaNovaSaveUsecase;
    @Inject
    PedidoFindByStatusUseCase pedidosFindByStatusUseCase;

    @GET
    public Response getFila() {
        var fila = filaFindByDataUsecase.execute(LocalDate.now());
        return Response.ok(fila.orElse(null)).build();
    }

    @POST
    @Transactional
    public Response adicionarFila() {
        FilaEntity fila = filaNovaSaveUsecase.execute(LocalDate.now());
        return Response.ok(FilaMapper.toModel(fila)).build();
    }


    @GET
    @Path("/{id}/pedidos")
    public Response listaPedidosPorFila(@PathParam ("id")Long filaId,
                                        @Range(min = 0,max = 2) @QueryParam
                                 int status){
        List<PedidoEntity> pedidos = pedidosFindByStatusUseCase.executar(status, filaId);
        List<Pedido> pedidosModel = pedidos
                .stream()
                .map(PedidoMapper::toModel)
                .collect(Collectors.toList());

        return Response.ok(pedidosModel).build();
    }
}