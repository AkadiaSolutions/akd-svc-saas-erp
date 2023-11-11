package br.akd.svc.akadia.modules.erp.precos.models.entity;

import br.akd.svc.akadia.modules.erp.precos.models.dto.request.PrecoRequest;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.modules.web.models.entity.empresa.EmpresaEntity;
import br.akd.svc.akadia.modules.erp.precos.models.enums.TipoPrecoEnum;
import br.akd.svc.akadia.modules.erp.precos.models.entity.id.PrecoId;
import br.akd.svc.akadia.exceptions.InvalidRequestException;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PrecoId.class)
@Table(name = "tb_preco")
@org.hibernate.annotations.Table(appliesTo = "tb_preco", comment = "Tabela de preços de produtos")
public class PrecoEntity {

    @Id
    @Comment("[PK] - ID do preço")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "preco_id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Id
    @Comment("[PK] - Empresa ao qual o preço pertence")
    @ManyToOne(targetEntity = EmpresaEntity.class)
    @JoinColumn(name = "preco_empresa", updatable = false, nullable = false)
    private EmpresaEntity empresa;

    @Column(name = "preco_datacadastro", nullable = false, length = 10)
    @Comment("Data em que o preço foi cadastrado (padrão yyyy-MM-dd)")
    private String dataCadastro;

    @Column(name = "preco_horacadastro", nullable = false, length = 18)
    @Comment("Hora em que o preço foi cadastrado (padrão HH:mm:ss.sssssssss)")
    private String horaCadastro;

    @Column(name = "preco_valor", nullable = false)
    @Comment("Valor do preço")
    private Double valor;

    @Column(name = "preco_observacao", length = 30)
    @Comment("Observação sobre o preço")
    private String observacao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "preco_tipopreco", nullable = false)
    @Comment("Tipo do preço em valores constantes: 1 - VAREJO (Varejo), 2 - ATACADO (Atacado)")
    private TipoPrecoEnum tipoPreco;

    @ManyToOne(targetEntity = ColaboradorEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "preco_responsavel", nullable = false)
    @Comment("Usuário responsável pelo cadastro do preço")
    private ColaboradorEntity colaboradorResponsavel;

    @Transient
    public List<PrecoEntity> realizaValidacaoCriacaoListaPrecos(List<PrecoRequest> precosRequest,
                                                                ColaboradorEntity colaboradorLogado) {
        if (precosRequest.size() != 2)
            throw new InvalidRequestException("A lista de preços do produto deve possuir dois preços");

        boolean contemPrecoVarejo = false;
        boolean contemPrecoAtacado = false;

        for (PrecoRequest preco : precosRequest) {
            if (preco.getTipoPreco().equals(TipoPrecoEnum.VAREJO)) contemPrecoVarejo = true;
            if (preco.getTipoPreco().equals(TipoPrecoEnum.ATACADO)) contemPrecoAtacado = true;
        }

        if (!contemPrecoVarejo || !contemPrecoAtacado)
            throw new InvalidRequestException("A lista de preços do produto deve possuir um preço de atacado e um preço de varejo");

        List<PrecoEntity> precos = new ArrayList<>();

        for (PrecoRequest precoRequest : precosRequest) {

            PrecoEntity precoEntity = PrecoEntity.builder()
                    .dataCadastro(LocalDate.now().toString())
                    .horaCadastro(LocalDate.now().toString())
                    .valor(precoRequest.getValor())
                    .observacao(precoRequest.getObservacao())
                    .tipoPreco(precoRequest.getTipoPreco())
                    .colaboradorResponsavel(colaboradorLogado)
                    .empresa(colaboradorLogado.getEmpresa())
                    .build();

            precos.add(precoEntity);
        }

        return precos;
    }
}
