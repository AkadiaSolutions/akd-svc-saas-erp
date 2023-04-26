package br.akd.svc.akadia.models.dto.sistema.colaboradores.responses;

import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.*;
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
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorResponse {
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
    private ExclusaoColaboradorResponse exclusao;
    private AcessoSistemaResponse acessoSistema;
    private ConfiguracaoPerfilEntity configuracaoPerfil;
    private EnderecoEntity endereco;
    private TelefoneEntity telefone;
    private ExpedienteEntity expediente;
    private DispensaEntity dispensa;
    private List<PontoEntity> pontos = new ArrayList<>();
    private List<FeriasEntity> historicoFerias = new ArrayList<>();
    private List<AdvertenciaEntity> advertencias = new ArrayList<>();
}
