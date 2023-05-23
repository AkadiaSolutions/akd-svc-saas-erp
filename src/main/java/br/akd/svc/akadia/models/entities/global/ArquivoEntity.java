package br.akd.svc.akadia.models.entities.global;

import br.akd.svc.akadia.models.enums.global.TipoArquivoEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_arquivo")
public class ArquivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long tamanho;
    @Enumerated(EnumType.STRING)
    private TipoArquivoEnum tipo;
    @Lob
    private byte[] arquivo;

}
