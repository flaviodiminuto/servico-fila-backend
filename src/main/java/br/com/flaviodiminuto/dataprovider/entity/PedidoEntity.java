package br.com.flaviodiminuto.dataprovider.entity;

import br.com.flaviodiminuto.StatusPedido;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
@DynamicInsert
public class PedidoEntity extends PanacheEntityBase {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "status_na_fila")
    @ColumnDefault("0")
    private StatusPedido statusPedidoNaFila;

    @Column(name = "data_hora_solicitacao")
    private LocalDateTime dataHoraSolicitacao;

    @Column(name = "data_hora_preparo")
    private LocalDateTime dataHoraPreparo;

    @Column(name = "data_hora_entrega")
    private LocalDateTime dataHoraEntrega;
}
