package br.akd.svc.akadia.models.entities.sistema.colaboradores;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_dispensa")
public class DispensaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataDispensa;

    private String motivo;

    private Boolean listaNegra;

    @Lob
    private List<byte[]> anexos;

    @Lob
    private byte[] contratoDispensa;
}
