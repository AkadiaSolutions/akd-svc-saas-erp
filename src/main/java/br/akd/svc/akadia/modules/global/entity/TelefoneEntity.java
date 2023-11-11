package br.akd.svc.akadia.modules.global.entity;

import br.akd.svc.akadia.modules.global.enums.TipoTelefoneEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_telefone")
public class TelefoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prefixo;

    private String numero;

    @Enumerated(EnumType.STRING)
    private TipoTelefoneEnum tipoTelefone;

    public String getTelefoneCompleto() {
        return "(" + this.getPrefixo() + ") " + this.getNumero();
    }
}
