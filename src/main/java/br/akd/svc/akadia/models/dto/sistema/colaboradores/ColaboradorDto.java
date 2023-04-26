package br.akd.svc.akadia.models.dto.sistema.colaboradores;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.global.TelefoneDto;
import br.akd.svc.akadia.models.dto.site.empresa.EmpresaDto;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloContratacaoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloTrabalhoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.StatusColaboradorEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoOcupacaoEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorDto {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private Long matricula;
    private byte[] fotoPerfil;
    private String nome;
    private String dataNascimento;
    private String email;
    private String cpfCnpj;
    private Boolean ativo;
    private Double salario;
    private String entradaEmpresa;
    private String saidaEmpresa;
    private byte[] contratoContratacao;
    private String ocupacao;
    private TipoOcupacaoEnum tipoOcupacaoEnum;
    private ModeloContratacaoEnum modeloContratacaoEnum;
    private ModeloTrabalhoEnum modeloTrabalhoEnum;
    private StatusColaboradorEnum statusColaboradorEnum;
    private ExclusaoColaboradorDto exclusao;
    private AcessoSistemaDto acessoSistema;
    private ConfiguracaoPerfilDto configuracaoPerfil;
    private EnderecoDto endereco;
    private TelefoneDto telefone;
    private ExpedienteDto expediente;
    private DispensaDto dispensa;
    private List<PontoDto> pontos = new ArrayList<>();
    private List<FeriasDto> historicoFerias = new ArrayList<>();
    private List<AdvertenciaDto> advertencias = new ArrayList<>();
    private EmpresaDto empresa;
}
