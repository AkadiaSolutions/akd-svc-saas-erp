package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.enums.bckoff.CategoriaChamadoEnum;
import br.akd.svc.akadia.models.enums.bckoff.StatusChamadoEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoDto {
    private Long id;
    private String dataCriacao;
    private String horaCriacao;
    private Long ticket;
    private String descricao;
    private String dataBaixa;
    private String horaBaixa;
    private CategoriaChamadoEnum categoriaChamadoEnum;
    private StatusChamadoEnum statusChamadoEnum;
    private ColaboradorInternoDto atendenteResponsavel;
    private EmpresaDto empresa;
    private AvaliacaoDto avaliacao;
    private List<MensagemDto> mensagens = new ArrayList<>();
}
