package br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response;

import br.akd.svc.akadia.modules.global.entity.ArquivoEntity;
import br.akd.svc.akadia.modules.global.entity.EnderecoEntity;
import br.akd.svc.akadia.modules.global.entity.ExclusaoEntity;
import br.akd.svc.akadia.modules.global.entity.TelefoneEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModeloContratacaoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.ModeloTrabalhoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.StatusColaboradorEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.TipoOcupacaoEnum;
import br.akd.svc.akadia.modules.erp.colaboradores.acesso.models.dto.response.AcessoSistemaResponse;
import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorResponse {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String matricula;
    private String nome;
    private String dataNascimento;
    private String email;
    private String cpfCnpj;
    private Double salario;
    private String entradaEmpresa;
    private String saidaEmpresa;
    private String ocupacao;
    private TipoOcupacaoEnum tipoOcupacaoEnum;
    private ModeloContratacaoEnum modeloContratacaoEnum;
    private ModeloTrabalhoEnum modeloTrabalhoEnum;
    private StatusColaboradorEnum statusColaboradorEnum;
    private ArquivoEntity fotoPerfil;
    private ExclusaoEntity exclusao;
    private AcessoSistemaResponse acessoSistema;
    private ConfiguracaoPerfilEntity configuracaoPerfil;
    private ArquivoEntity contratoContratacao;
    private EnderecoEntity endereco;
    private TelefoneEntity telefone;
    private ExpedienteEntity expediente;
    private DispensaEntity dispensa;
    @Builder.Default
    private List<PontoEntity> pontos = new ArrayList<>();
    @Builder.Default
    private List<FeriasEntity> historicoFerias = new ArrayList<>();
    @Builder.Default
    private List<AdvertenciaEntity> advertencias = new ArrayList<>();
}
