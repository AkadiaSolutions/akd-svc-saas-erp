package br.akd.svc.akadia.models.entities.site.empresa;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_dados_emp_deletada")
public class DadosEmpresaDeletadaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataRemocao;
    private String horaRemocao;
    private String cnpj;
    private String razaoSocial;
    private String inscricaoMunicipal;
    private String inscricaoEstadual;
}
