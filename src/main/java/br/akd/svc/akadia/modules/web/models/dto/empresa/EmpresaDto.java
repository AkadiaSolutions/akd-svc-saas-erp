package br.akd.svc.akadia.modules.web.models.dto.empresa;

import br.akd.svc.akadia.modules.backoffice.models.dto.ChamadoDto;
import br.akd.svc.akadia.modules.global.dto.EnderecoDto;
import br.akd.svc.akadia.modules.global.dto.TelefoneDto;
import br.akd.svc.akadia.modules.web.models.dto.empresa.fiscal.ConfigFiscalEmpresaDto;
import br.akd.svc.akadia.modules.web.models.enums.SegmentoEmpresaEnum;
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