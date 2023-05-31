package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.empresa.EmpresaDto;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoSistemaEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ConfiguracaoPerfilEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ExclusaoColaboradorEntity;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.empresa.CriaEmpresaResponse;
import br.akd.svc.akadia.models.entities.site.empresa.DadosEmpresaDeletadaEntity;
import br.akd.svc.akadia.models.entities.site.empresa.EmpresaEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.ConfigFiscalEmpresaEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.NfceConfigEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.NfeConfigEntity;
import br.akd.svc.akadia.models.entities.site.empresa.fiscal.NfseConfigEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.*;
import br.akd.svc.akadia.repositories.sistema.colaboradores.impl.ColaboradorRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.sistema.colaboradores.ColaboradorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmpresaService {

    @Autowired
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    ColaboradorRepositoryImpl colaboradorRepositoryImpl;

    @Autowired
    ColaboradorService colaboradorService;

    public void validaSeCnpjJaExiste(String cnpj) {
        log.debug("Método de validação de chave única de CNPJ acessado");
        if (empresaRepositoryImpl.implementaBuscaPorCnpj(cnpj).isPresent()) {
            log.warn("O cnpj informado já existe");
            throw new InvalidRequestException("O cnpj informado já existe");
        }
        log.debug("Validação de chave única de CNPJ... OK");
    }

    public void validaSeEndpointJaExiste(String endpoint) {
        log.debug("Método de validação de chave única de endpoint acessado");
        if (empresaRepositoryImpl.implementaBuscaPorEndpoint(endpoint).isPresent()) {
            log.warn("O endpoint informado já existe");
            throw new InvalidRequestException("O endpoint informado já existe");
        }
        log.debug("Validação de chave única de endpoint... OK");
    }

    public void validaSeRazaoSocialJaExiste(String razaoSocial) {
        log.debug("Método de validação de chave única de Razão Social acessado");
        if (empresaRepositoryImpl.implementaBuscaPorRazaoSocial(razaoSocial).isPresent()) {
            log.warn("A razão social informada já existe");
            throw new InvalidRequestException("A razão social informada já existe");
        }
        log.debug("Validação de chave única de Razão social... OK");
    }

    public void validaSeInscricaoEstadualJaExiste(String inscricaoEstadual) {
        log.debug("Método de validação de chave única de Inscrição estadual acessado");
        if (empresaRepositoryImpl.implementaBuscaPorInscricaoEstadual(inscricaoEstadual).isPresent()) {
            log.warn("A inscrição estadual informada já existe");
            throw new InvalidRequestException("A inscrição estadual informada já existe");
        }
        log.debug("Validação de chave única de inscrição estadual... OK");
    }

    public void validaSeInscricaoMunicipalJaExiste(String inscricaoMunicipal) {
        log.debug("Método de validação de chave única de Inscrição municipal acessado");
        if (empresaRepositoryImpl.implementaBuscaPorInscricaoMunicipal(inscricaoMunicipal).isPresent()) {
            log.warn("A inscrição municipal informada já existe");
            throw new InvalidRequestException("A inscrição municipal informada já existe");
        }
        log.debug("Validação de chave única de inscrição municipal... OK");
    }

    public void validacaoDeChaveUnicaParaNovaEmpresa(EmpresaDto empresaDto) {

        log.debug("Método de validação de chave única para criação de nova empresa acessado...");

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
        log.debug("Método de validação de chave única para atualização de empresa acessado...");
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

        log.debug("Método de serviço responsável pelo tratamento do objeto recebido e criação de nova empresa acessado");
        log.debug("Empresa recebida: {} | Id do cliente: {}", empresaDto, idCliente);

        log.debug("Iniciando acesso ao método de implementação de busca de cliente por id...");
        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl.implementaBuscaPorId(idCliente);

        log.debug("Realizando filtro por empresas ativas do cliente...");
        List<EmpresaEntity> empresasAtivasCliente =
                clienteSistema.getEmpresas().stream()
                        .filter((EmpresaEntity filtroEmpresa) -> !filtroEmpresa.getDeletada())
                        .collect(Collectors.toList());

        log.debug("Empresas ativas do cliente: {}", empresasAtivasCliente);

        log.debug("Verificando se cliente já possui quantidade limite de empresas cadastradas para o seu plano...");
        if (clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas()
                <= clienteSistema.getEmpresas().size()) {
            log.warn("O cliente já possui o número máximo de empresas cadastradas: {}. O permitido é: {}",
                    empresasAtivasCliente.size(), clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas());
            throw new InvalidRequestException("Este cliente já possui o número máximo de empresas cadastradas em seu plano: "
                    + clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas() + " (max) com "
                    + empresasAtivasCliente.size() + " empresas cadastradas");
        }

        log.debug("Iniciando acesso ao método de validação de variáveis de chave única para empresa...");
        validacaoDeChaveUnicaParaNovaEmpresa(empresaDto);

        log.debug("Iniciando construção do objeto EmpresaEntity...");
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
                        .tipoTelefone(empresaDto.getTelefone().getTipoTelefone())
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
                        .estado(empresaDto.getEndereco().getEstado())
                        .build()
                        : null)
                .build();
        log.debug("Construção de objeto EmpresaEntity realizado com sucesso");

        log.debug("Adicionando a empresa à lista de empresas do cliente...");
        clienteSistema.getEmpresas().add(empresaEntity);

        log.debug("Iniciando acesso ao método de implementação de persistência do cliente...");
        ClienteSistemaEntity clienteSistemaEntity = clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);

        log.debug("Obtendo empresa criada da lista das empresas do cliente persistido...");
        EmpresaEntity empresaCriada = clienteSistemaEntity.getEmpresas().stream()
                .filter(empresaFiltrada -> empresaFiltrada.getRazaoSocial().equals(empresaDto.getRazaoSocial()))
                .collect(Collectors.toList()).get(0);

        log.debug("Iniciando acesso ao método de criação de novo colaborador admin para empresa...");
        ColaboradorEntity colaborador = criaColaboradorAdminParaNovaEmpresa(empresaCriada);

        log.info("Empresa criada com sucesso");
        return CriaEmpresaResponse.builder()
                .idClienteEmpresa(clienteSistemaEntity.getId())
                .colaboradorCriado(colaborador)
                .build();
    }

    public ColaboradorEntity criaColaboradorAdminParaNovaEmpresa(EmpresaEntity empresaEntity) {

        log.debug("Método de criação de colaborador ADMIN DEFAULT para nova empresa acessado");

        log.debug("Iniciando acesso ao método de geração de senha aleatória...");
        String senha = geraSenhaAleatoriaParaNovoLogin(empresaEntity);
        log.debug("Senha criada com sucesso");

        log.debug("Criando objeto para definir permissões do usuário...");
        Set<ModulosEnum> modulosLiberadosUsuario = new HashSet<>();

        log.debug("Iniciando acesso ao método para realizar carga no objeto de permissões do usuário...");
        habilitaTodosModulosColaborador(modulosLiberadosUsuario);

        log.debug("Construindo objeto e iniciando método de implementação de persistência de colaborador...");
        return colaboradorRepositoryImpl.implementaPersistencia(ColaboradorEntity.builder()
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .matricula(colaboradorService.geraMatriculaUnica())
                .fotoPerfil(null)
                .nome("admin")
                .dataNascimento(null)
                .email(null)
                .cpfCnpj(null)
                .salario(0.0)
                .entradaEmpresa(null)
                .saidaEmpresa(null)
                .contratoContratacao(null)
                .ocupacao("Administrador do sistema")
                .tipoOcupacaoEnum(TipoOcupacaoEnum.ADMINISTRADOR)
                .modeloTrabalhoEnum(ModeloTrabalhoEnum.PRESENCIAL)
                .modeloContratacaoEnum(ModeloContratacaoEnum.CLT)
                .statusColaboradorEnum(StatusColaboradorEnum.ATIVO)
                .acessoSistema(AcessoSistemaEntity.builder()
                        .acessoSistemaAtivo(true)
                        .senha(senha)
                        .senhaCriptografada(new BCryptPasswordEncoder().encode(senha))
                        .permissaoEnum(PermissaoEnum.LEITURA_AVANCADA_ALTERACAO)
                        .privilegios(modulosLiberadosUsuario)
                        .build())
                .configuracaoPerfil(ConfiguracaoPerfilEntity.builder()
                        .dataUltimaAtualizacao(LocalDate.now().toString())
                        .horaUltimaAtualizacao(LocalTime.now().toString())
                        .temaTelaEnum(TemaTelaEnum.TELA_CLARA)
                        .build())
                .exclusao(ExclusaoColaboradorEntity.builder()
                        .excluido(false)
                        .dataExclusao(null)
                        .horaExclusao(null)
                        .responsavelExclusao(null)
                        .build())
                .endereco(null)
                .telefone(null)
                .expediente(null)
                .dispensa(null)
                .pontos(new ArrayList<>())
                .historicoFerias(new ArrayList<>())
                .advertencias(new ArrayList<>())
                .acessos(new ArrayList<>())
                .acoes(new ArrayList<>())
                .empresa(empresaEntity)
                .build()
        );
    }

    public void habilitaTodosModulosColaborador(Set<ModulosEnum> privilegios) {
        log.debug("Método de setagem de todos os privilégios à lista de privilégios do colaborador acessado");
        log.debug("Iniciando setagem de privilégios...");
        privilegios.add(ModulosEnum.HOME);
        privilegios.add(ModulosEnum.CLIENTES);
        privilegios.add(ModulosEnum.VENDAS);
        privilegios.add(ModulosEnum.PDV);
        privilegios.add(ModulosEnum.ESTOQUE);
        privilegios.add(ModulosEnum.DESPESAS);
        privilegios.add(ModulosEnum.FECHAMENTOS);
        privilegios.add(ModulosEnum.PATRIMONIOS);
        privilegios.add(ModulosEnum.FORNECEDORES);
        privilegios.add(ModulosEnum.COMPRAS);
        privilegios.add(ModulosEnum.COLABORADORES);
        privilegios.add(ModulosEnum.PRECOS);
        log.debug("Lista de privilégios preenchida com sucesso");
    }

    public String geraSenhaAleatoriaParaNovoLogin(EmpresaEntity empresaEntity) {
        log.debug("Método de criação de senha aleatória acessado");
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

        log.debug("Método de serviço de atualização de empresa acessado");
        log.debug("Id da empresa: {} | Objeto recebido: {}", idEmpresa, empresaDto);

        log.debug("Iniciando acesso ao método de implementação de busca de empresa por id...");
        EmpresaEntity empresa = empresaRepositoryImpl.implementaBuscaPorId(idEmpresa);

        log.debug("Iniciando validação se empresa a ser alterada foi deletada anteriormente...");
        if (Boolean.TRUE.equals(empresa.getDeletada())) {
            log.warn("A empresa não pode ser alterada pois foi deletada. Empresa: {}", empresa);
            throw new InvalidRequestException("Não é possível realizar alterações em uma empresa que foi removida");
        }

        log.debug("Iniciando acesso ao método de validação de chaves únicas para a atualização da empresa...");
        validacaoDeChaveUnicaParaAtualizacaoDeEmpresa(empresaDto, empresa);

        log.debug("Iniciando setagem de atributos atualizados da empresa...");
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
        empresa.getTelefone().setTipoTelefone(empresaDto.getTelefone().getTipoTelefone());
        empresa.getEndereco().setLogradouro(empresaDto.getEndereco().getLogradouro());
        empresa.getEndereco().setNumero(empresaDto.getEndereco().getNumero());
        empresa.getEndereco().setBairro(empresaDto.getEndereco().getBairro());
        empresa.getEndereco().setCidade(empresaDto.getEndereco().getCidade());
        empresa.getEndereco().setEstado(empresaDto.getEndereco().getEstado());
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
        log.debug("Setagem de atributos finalizada com sucesso");

        log.debug("Iniciando acesso ao método de implementação de persistência de empresa...");
        EmpresaEntity empresaAtualizada = empresaRepositoryImpl.implementaPersistencia(empresa);

        log.info("A empresa foi atualizada com sucesso");
        return empresaAtualizada;

    }

    public EmpresaEntity removeEmpresa(Long id) {

        log.debug("Método de serviço de remoção de empresa acessado. Id recebido: {}", id);

        log.debug("Iniciando acesso ao método de implementação de busca de empresa por id...");
        EmpresaEntity empresa = empresaRepositoryImpl.implementaBuscaPorId(id);

        log.debug("Setando atributos de remoção da empresa ao objeto empresa...");
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
        log.debug("Setagem de atributos de remoção da empresa finalizados");

        log.debug("Iniciando acesso ao método de implementação de persistência de empresa...");
        EmpresaEntity empresaDeletada = empresaRepositoryImpl.implementaPersistencia(empresa);

        log.info("A empresa foi deletada com sucesso");
        return empresaDeletada;
    }

}
