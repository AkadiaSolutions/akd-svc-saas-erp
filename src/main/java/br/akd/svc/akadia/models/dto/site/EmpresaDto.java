package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.dto.bckoff.ChamadoDto;
import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.global.TelefoneDto;
import br.akd.svc.akadia.models.dto.site.fiscal.ConfigFiscalEmpresaDto;
import br.akd.svc.akadia.models.enums.site.SegmentoEmpresaEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDto {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String endpoint;
    private String email;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private byte[] logo;
    private Boolean deletada;
    private DadosEmpresaDeletadaDto dadosEmpresaDeletada;
    private SegmentoEmpresaEnum segmentoEmpresaEnum;
    private TelefoneDto telefone;
    private ConfigFiscalEmpresaDto configFiscalEmpresa;
    private EnderecoDto endereco;
    private List<ChamadoDto> chamados = new ArrayList<>();
}