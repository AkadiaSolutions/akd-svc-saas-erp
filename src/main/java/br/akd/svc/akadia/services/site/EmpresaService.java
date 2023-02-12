package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.empresa.EmpresaDto;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoSistemaEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ConfiguracaoPerfilEntity;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.empresa.CriaEmpresaResponse;
import br.akd.svc.akadia.models.entities.site.empresa.DadosEmpresaDeletadaEntity;
import br.akd.svc.akadia.models.entities.site.empresa.EmpresaEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.ConfigFiscalEmpresaEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.NfceConfigEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.NfeConfigEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.NfseConfigEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.ModeloTrabalhoEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.StatusColaboradorEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TemaTelaEnum;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.TipoOcupacaoEnum;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;


    public void validaSeCnpjJaExiste(String cnpj) {
        if (empresaRepositoryImpl.implementaBuscaPorCnpj(cnpj).isPresent()) {
            throw new InvalidRequestException("O cnpj informado já existe");
        }
    }

    public void validaSeEndpointJaExiste(String endpoint) {
        if (empresaRepositoryImpl.implementaBuscaPorEndpoint(endpoint).isPresent()) {
            throw new InvalidRequestException("O endpoint informado já existe");
        }
    }

    public void validaSeRazaoSocialJaExiste(String razaoSocial) {
        if (empresaRepositoryImpl.implementaBuscaPorRazaoSocial(razaoSocial).isPresent()) {
            throw new InvalidRequestException("A razão social informada já existe");
        }
    }

    public void validaSeInscricaoEstadualJaExiste(String inscricaoEstadual) {
        if (empresaRepositoryImpl.implementaBuscaPorInscricaoEstadual(inscricaoEstadual).isPresent()) {
            throw new InvalidRequestException("A inscrição estadual informada já existe");
        }
    }

    public void validaSeInscricaoMunicipalJaExiste(String inscricaoMunicipal) {
        if (empresaRepositoryImpl.implementaBuscaPorInscricaoMunicipal(inscricaoMunicipal).isPresent()) {
            throw new InvalidRequestException("A inscrição municipal informada já existe");
        }
    }

    public void validacaoDeChaveUnicaParaNovaEmpresa(EmpresaDto empresaDto) {
        if (empresaDto.getCnpj() != null) validaSeCnpjJaExiste(empresaDto.getCnpj());
        if (empresaDto.getRazaoSocial() != null) validaSeRazaoSocialJaExiste(empresaDto.getRazaoSocial());
        if (empresaDto.getEndpoint() != null) validaSeEndpointJaExiste(empresaDto.getEndpoint());
        if (empresaDto.getInscricaoEstadual() != null)
            validaSeInscricaoEstadualJaExiste(empresaDto.getInscricaoEstadual());
        if (empresaDto.getInscricaoMunicipal() != null)
            validaSeInscricaoEstadualJaExiste(empresaDto.getInscricaoMunicipal());
    }

    public void validacaoDeChaveUnicaParaAtualizacaoDeEmpresa(EmpresaDto empresaDto,
                                                              EmpresaEntity empresaEditada) {
        if (empresaDto.getCnpj() != null && !empresaEditada.getCnpj().equals(empresaDto.getCnpj()))
            validaSeCnpjJaExiste(empresaDto.getCnpj());
        if (empresaDto.getEndpoint() != null && !empresaEditada.getEndpoint().equalsIgnoreCase(empresaDto.getEndpoint()))
            validaSeEndpointJaExiste(empresaDto.getEndpoint());
        if (empresaDto.getRazaoSocial() != null && !empresaEditada.getRazaoSocial().equalsIgnoreCase(empresaDto.getRazaoSocial()))
            validaSeRazaoSocialJaExiste(empresaDto.getRazaoSocial());
        if (empresaDto.getInscricaoEstadual() != null && !empresaEditada.getInscricaoEstadual().equalsIgnoreCase(empresaDto.getInscricaoEstadual()))
            validaSeInscricaoEstadualJaExiste(empresaDto.getInscricaoEstadual());
        if (empresaDto.getInscricaoMunicipal() != null && !empresaEditada.getInscricaoMunicipal().equalsIgnoreCase(empresaDto.getInscricaoMunicipal()))
            validaSeInscricaoMunicipalJaExiste(empresaDto.getInscricaoMunicipal());
    }

    @Transactional
    public CriaEmpresaResponse criaNovaEmpresa(Long idCliente, EmpresaDto empresaDto) {

        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl.implementaBuscaPorId(idCliente);

        List<EmpresaEntity> empresasAtivasCliente =
                clienteSistema.getEmpresas().stream()
                        .filter((EmpresaEntity filtroEmpresa) -> !filtroEmpresa.getDeletada())
                        .collect(Collectors.toList());

        if (clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas()
                <= clienteSistema.getEmpresas().size())
            throw new InvalidRequestException("Este cliente já possui o número máximo de empresas cadastradas em seu plano: "
                    + clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas() + " (max) com "
                    + empresasAtivasCliente.size() + " empresas cadastradas");

        validacaoDeChaveUnicaParaNovaEmpresa(empresaDto);

        EmpresaEntity empresaEntity = EmpresaEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .nome(empresaDto.getNome())
                .razaoSocial(empresaDto.getRazaoSocial())
                .cnpj(empresaDto.getCnpj()
                        .replace("-", "")
                        .replace("/", "")
                        .replace(".", "")
                        .trim())
                .endpoint(empresaDto.getEndpoint())
                .email(empresaDto.getEmail())
                .nomeFantasia(empresaDto.getNomeFantasia())
                .inscricaoEstadual(empresaDto.getInscricaoEstadual())
                .inscricaoMunicipal(empresaDto.getInscricaoMunicipal())
                .nomeResponsavel(empresaDto.getNomeResponsavel())
                .cpfResponsavel(empresaDto.getCpfResponsavel())
                .logo(empresaDto.getLogo())
                .deletada(false)
                .dadosEmpresaDeletada(DadosEmpresaDeletadaEntity.builder()
                        .dataRemocao(null)
                        .horaRemocao(null)
                        .cnpj(null)
                        .inscricaoMunicipal(null)
                        .inscricaoEstadual(null)
                        .razaoSocial(null)
                        .build())
                .segmentoEmpresaEnum(empresaDto.getSegmentoEmpresaEnum())
                .telefone(TelefoneEntity.builder()
                        .tipoTelefoneEnum(empresaDto.getTelefone().getTipoTelefoneEnum())
                        .prefixo(empresaDto.getTelefone().getPrefixo())
                        .numero(empresaDto.getTelefone().getNumero())
                        .build())
                .configFiscalEmpresa(ConfigFiscalEmpresaEntity.builder()
                        .discriminaImpostos(empresaDto.getConfigFiscalEmpresa().getDiscriminaImpostos())
                        .habilitaNfe(empresaDto.getConfigFiscalEmpresa().getHabilitaNfe())
                        .habilitaNfce(empresaDto.getConfigFiscalEmpresa().getHabilitaNfce())
                        .habilitaNfse(empresaDto.getConfigFiscalEmpresa().getHabilitaNfse())
                        .habilitaEnvioEmailDestinatario(empresaDto.getConfigFiscalEmpresa().getHabilitaEnvioEmailDestinatario())
                        .exibeReciboNaDanfe(empresaDto.getConfigFiscalEmpresa().getExibeReciboNaDanfe())
                        .cnpjContabilidade(empresaDto.getConfigFiscalEmpresa().getCnpjContabilidade())
                        .senhaCertificadoDigital(empresaDto.getConfigFiscalEmpresa().getSenhaCertificadoDigital())
                        .orientacaoDanfeEnum(empresaDto.getConfigFiscalEmpresa().getOrientacaoDanfeEnum())
                        .regimeTributarioEnum(empresaDto.getConfigFiscalEmpresa().getRegimeTributarioEnum())
                        .certificadoDigital(empresaDto.getConfigFiscalEmpresa().getCertificadoDigital())
                        .nfeConfig(Boolean.TRUE.equals(empresaDto.getConfigFiscalEmpresa().getHabilitaNfe())
                                ? NfeConfigEntity.builder()
                                .proximoNumeroProducao(empresaDto.getConfigFiscalEmpresa().getNfeConfig().getProximoNumeroProducao())
                                .proximoNumeroHomologacao(empresaDto.getConfigFiscalEmpresa().getNfeConfig().getProximoNumeroHomologacao())
                                .serieProducao(empresaDto.getConfigFiscalEmpresa().getNfeConfig().getSerieProducao())
                                .serieHomologacao(empresaDto.getConfigFiscalEmpresa().getNfeConfig().getSerieHomologacao())
                                .build()
                                : null)
                        .nfceConfig(Boolean.TRUE.equals(empresaDto.getConfigFiscalEmpresa().getHabilitaNfce())
                                ? NfceConfigEntity.builder()
                                .proximoNumeroProducao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getProximoNumeroProducao())
                                .proximoNumeroHomologacao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getProximoNumeroHomologacao())
                                .serieProducao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getSerieProducao())
                                .serieHomologacao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getSerieHomologacao())
                                .cscProducao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getCscProducao())
                                .cscHomologacao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getCscHomologacao())
                                .idTokenProducao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getIdTokenProducao())
                                .idTokenHomologacao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getIdTokenHomologacao())
                                .build()
                                : null)
                        .nfseConfig(Boolean.TRUE.equals(empresaDto.getConfigFiscalEmpresa().getHabilitaNfse())
                                ? NfseConfigEntity.builder()
                                .proximoNumeroProducao(empresaDto.getConfigFiscalEmpresa().getNfseConfig().getProximoNumeroProducao())
                                .proximoNumeroHomologacao(empresaDto.getConfigFiscalEmpresa().getNfseConfig().getProximoNumeroHomologacao())
                                .serieProducao(empresaDto.getConfigFiscalEmpresa().getNfseConfig().getSerieProducao())
                                .serieHomologacao(empresaDto.getConfigFiscalEmpresa().getNfseConfig().getSerieHomologacao())
                                .build()
                                : null)
                        .build())
                .endereco(empresaDto.getEndereco() != null
                        ? EnderecoEntity.builder()
                        .logradouro(empresaDto.getEndereco().getLogradouro())
                        .numero(empresaDto.getEndereco().getNumero())
                        .codigoPostal(empresaDto.getEndereco().getCodigoPostal())
                        .bairro(empresaDto.getEndereco().getBairro())
                        .complemento(empresaDto.getEndereco().getComplemento())
                        .cidade(empresaDto.getEndereco().getCidade())
                        .estadoEnum(empresaDto.getEndereco().getEstadoEnum())
                        .build()
                        : null)
                .build();

        clienteSistema.getEmpresas().add(empresaEntity);

        ClienteSistemaEntity clienteSistemaEntity = clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);

        EmpresaEntity empresaCriada = clienteSistemaEntity.getEmpresas().stream()
                .filter(empresaFiltrada -> empresaFiltrada.getRazaoSocial().equals(empresaDto.getRazaoSocial()))
                .collect(Collectors.toList()).get(0);

        ColaboradorEntity colaborador = criaColaboradorAdminParaNovaEmpresa(empresaCriada);

        return CriaEmpresaResponse.builder()
                .idClienteEmpresa(clienteSistemaEntity.getId())
                .colaboradorCriado(colaborador)
                .build();
    }

    public ColaboradorEntity criaColaboradorAdminParaNovaEmpresa(EmpresaEntity empresaEntity) {
        String senha = geraSenhaAleatoriaParaNovoLogin(empresaEntity);
        return colaboradorRepositoryImpl.implementaPersistencia(ColaboradorEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .fotoPerfil(null)
                .nome("admin")
                .dataNascimento(null)
                .email(null)
                .cpfCnpj(null)
                .ativo(true)
                .excluido(false)
                .salario(0.0)
                .entradaEmpresa(null)
                .saidaEmpresa(null)
                .contratoContratacao(null)
                .ocupacao("Administrador do sistema")
                .tipoOcupacaoEnum(TipoOcupacaoEnum.ADMINISTRADOR)
                .modeloTrabalhoEnum(ModeloTrabalhoEnum.PRESENCIAL)
                .statusColaboradorEnum(StatusColaboradorEnum.ATIVO)
                .acessoSistema(AcessoSistemaEntity.builder()
                        .acessoSistemaAtivo(true)
                        .nomeUsuario("admin")
                        .senha(senha)
                        .senhaCriptografada(new BCryptPasswordEncoder().encode(senha))
                        .build())
                .configuracaoPerfil(ConfiguracaoPerfilEntity.builder()
                        .dataUltimaAtualizacao(LocalDate.now().toString())
                        .horaUltimaAtualizacao(LocalTime.now().toString())
                        .temaTelaEnum(TemaTelaEnum.TELA_CLARA)
                        .build())
                .endereco(null)
                .telefone(null)
                .expediente(null)
                .dispensa(null)
                .pontos(new ArrayList<>())
                .historicoFerias(new ArrayList<>())
                .advertencias(new ArrayList<>())
                .parentescos(new ArrayList<>())
                .empresa(empresaEntity)
                .build()
        );
    }

    public String geraSenhaAleatoriaParaNovoLogin(EmpresaEntity empresaEntity) {
        return "@" + empresaEntity.getNome().replace(" ", "").substring(0, 2).toUpperCase() +
                empresaEntity.getCnpj()
                        .replace("-", ".")
                        .replace(".", "")
                        .replace("/", "")
                        .substring(0, 2) +
                empresaEntity.getDataCadastro().substring(0, 2) +
                empresaEntity.getSegmentoEmpresaEnum().getDesc().substring(0, 2).toUpperCase();
    }

    public EmpresaEntity atualizaEmpresa(Long idEmpresa, EmpresaDto empresaDto) {

        EmpresaEntity empresa = empresaRepositoryImpl.implementaBuscaPorId(idEmpresa);

        if (Boolean.TRUE.equals(empresa.getDeletada()))
            throw new InvalidRequestException("Não é possível realizar alterações em uma empresa que foi removida");

        validacaoDeChaveUnicaParaAtualizacaoDeEmpresa(empresaDto, empresa);

        empresa.setNome(empresaDto.getNome());
        empresa.setRazaoSocial(empresaDto.getRazaoSocial());
        empresa.setCnpj(empresaDto.getCnpj());
        empresa.setEndpoint(empresaDto.getEndpoint());
        empresa.setEmail(empresaDto.getEmail());
        empresa.setNomeFantasia(empresaDto.getNomeFantasia());
        empresa.setInscricaoEstadual(empresaDto.getInscricaoEstadual());
        empresa.setInscricaoMunicipal(empresaDto.getInscricaoMunicipal());
        empresa.setNomeResponsavel(empresaDto.getNomeResponsavel());
        empresa.setCpfResponsavel(empresaDto.getCpfResponsavel());
        empresa.setLogo(empresaDto.getLogo());
        empresa.getTelefone().setPrefixo(empresaDto.getTelefone().getPrefixo());
        empresa.getTelefone().setNumero(empresaDto.getTelefone().getNumero());
        empresa.getTelefone().setTipoTelefoneEnum(empresaDto.getTelefone().getTipoTelefoneEnum());
        empresa.getEndereco().setLogradouro(empresaDto.getEndereco().getLogradouro());
        empresa.getEndereco().setNumero(empresaDto.getEndereco().getNumero());
        empresa.getEndereco().setBairro(empresaDto.getEndereco().getBairro());
        empresa.getEndereco().setCidade(empresaDto.getEndereco().getCidade());
        empresa.getEndereco().setEstadoEnum(empresaDto.getEndereco().getEstadoEnum());
        empresa.getEndereco().setCodigoPostal(empresaDto.getEndereco().getCodigoPostal());
        empresa.getEndereco().setComplemento(empresaDto.getEndereco().getComplemento());
        empresa.getConfigFiscalEmpresa().setDiscriminaImpostos(empresaDto.getConfigFiscalEmpresa().getDiscriminaImpostos());
        empresa.getConfigFiscalEmpresa().setHabilitaNfe(empresaDto.getConfigFiscalEmpresa().getHabilitaNfe());
        empresa.getConfigFiscalEmpresa().setHabilitaNfce(empresaDto.getConfigFiscalEmpresa().getHabilitaNfce());
        empresa.getConfigFiscalEmpresa().setHabilitaNfse(empresaDto.getConfigFiscalEmpresa().getHabilitaNfse());
        empresa.getConfigFiscalEmpresa().setHabilitaEnvioEmailDestinatario(empresaDto.getConfigFiscalEmpresa().getHabilitaEnvioEmailDestinatario());
        empresa.getConfigFiscalEmpresa().setExibeReciboNaDanfe(empresaDto.getConfigFiscalEmpresa().getExibeReciboNaDanfe());
        empresa.getConfigFiscalEmpresa().setCnpjContabilidade(empresaDto.getConfigFiscalEmpresa().getCnpjContabilidade());
        empresa.getConfigFiscalEmpresa().setSenhaCertificadoDigital(empresaDto.getConfigFiscalEmpresa().getSenhaCertificadoDigital());
        empresa.getConfigFiscalEmpresa().setOrientacaoDanfeEnum(empresaDto.getConfigFiscalEmpresa().getOrientacaoDanfeEnum());
        empresa.getConfigFiscalEmpresa().setRegimeTributarioEnum(empresaDto.getConfigFiscalEmpresa().getRegimeTributarioEnum());
        empresa.getConfigFiscalEmpresa().setCertificadoDigital(empresaDto.getConfigFiscalEmpresa().getCertificadoDigital());
        empresa.getConfigFiscalEmpresa().getNfeConfig().setProximoNumeroProducao(empresaDto.getConfigFiscalEmpresa().getNfeConfig().getProximoNumeroProducao());
        empresa.getConfigFiscalEmpresa().getNfeConfig().setProximoNumeroHomologacao(empresaDto.getConfigFiscalEmpresa().getNfeConfig().getProximoNumeroProducao());
        empresa.getConfigFiscalEmpresa().getNfeConfig().setSerieProducao(empresaDto.getConfigFiscalEmpresa().getNfeConfig().getSerieProducao());
        empresa.getConfigFiscalEmpresa().getNfeConfig().setSerieHomologacao(empresaDto.getConfigFiscalEmpresa().getNfeConfig().getSerieHomologacao());
        empresa.getConfigFiscalEmpresa().getNfceConfig().setProximoNumeroProducao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getProximoNumeroProducao());
        empresa.getConfigFiscalEmpresa().getNfceConfig().setProximoNumeroHomologacao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getProximoNumeroProducao());
        empresa.getConfigFiscalEmpresa().getNfceConfig().setSerieProducao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getSerieProducao());
        empresa.getConfigFiscalEmpresa().getNfceConfig().setSerieHomologacao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getSerieHomologacao());
        empresa.getConfigFiscalEmpresa().getNfceConfig().setCscProducao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getCscProducao());
        empresa.getConfigFiscalEmpresa().getNfceConfig().setCscHomologacao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getCscHomologacao());
        empresa.getConfigFiscalEmpresa().getNfceConfig().setIdTokenProducao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getIdTokenProducao());
        empresa.getConfigFiscalEmpresa().getNfceConfig().setIdTokenHomologacao(empresaDto.getConfigFiscalEmpresa().getNfceConfig().getIdTokenHomologacao());
        empresa.getConfigFiscalEmpresa().getNfseConfig().setProximoNumeroProducao(empresaDto.getConfigFiscalEmpresa().getNfseConfig().getProximoNumeroProducao());
        empresa.getConfigFiscalEmpresa().getNfseConfig().setProximoNumeroHomologacao(empresaDto.getConfigFiscalEmpresa().getNfseConfig().getProximoNumeroProducao());
        empresa.getConfigFiscalEmpresa().getNfseConfig().setSerieProducao(empresaDto.getConfigFiscalEmpresa().getNfseConfig().getSerieProducao());
        empresa.getConfigFiscalEmpresa().getNfseConfig().setSerieHomologacao(empresaDto.getConfigFiscalEmpresa().getNfseConfig().getSerieHomologacao());

        return empresaRepositoryImpl.implementaPersistencia(empresa);

    }

    public EmpresaEntity removeEmpresa(Long id) {
        EmpresaEntity empresa = empresaRepositoryImpl.implementaBuscaPorId(id);

        empresa.setDeletada(true);

        empresa.getDadosEmpresaDeletada().setDataRemocao(LocalDate.now().toString());
        empresa.getDadosEmpresaDeletada().setHoraRemocao(LocalTime.now().toString());

        empresa.getDadosEmpresaDeletada().setCnpj(empresa.getCnpj());
        empresa.getDadosEmpresaDeletada().setInscricaoEstadual(empresa.getInscricaoEstadual());
        empresa.getDadosEmpresaDeletada().setInscricaoMunicipal(empresa.getInscricaoMunicipal());
        empresa.getDadosEmpresaDeletada().setRazaoSocial(empresa.getRazaoSocial());

        empresa.setCnpj(null);
        empresa.setInscricaoMunicipal(null);
        empresa.setInscricaoEstadual(null);
        empresa.setRazaoSocial(null);

        return empresaRepositoryImpl.implementaPersistencia(empresa);
    }

}
