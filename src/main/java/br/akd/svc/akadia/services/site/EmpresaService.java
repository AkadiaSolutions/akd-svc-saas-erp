package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.empresa.EmpresaDto;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoSistemaEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ConfiguracaoPerfilEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ModulosEnum;
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


    public void validaSeCnpjJaExiste(String cnpj) {
        log.debug("M??todo de valida????o de chave ??nica de CNPJ acessado");
        if (empresaRepositoryImpl.implementaBuscaPorCnpj(cnpj).isPresent()) {
            log.warn("O cnpj informado j?? existe");
            throw new InvalidRequestException("O cnpj informado j?? existe");
        }
        log.debug("Valida????o de chave ??nica de CNPJ... OK");
    }

    public void validaSeEndpointJaExiste(String endpoint) {
        log.debug("M??todo de valida????o de chave ??nica de endpoint acessado");
        if (empresaRepositoryImpl.implementaBuscaPorEndpoint(endpoint).isPresent()) {
            log.warn("O endpoint informado j?? existe");
            throw new InvalidRequestException("O endpoint informado j?? existe");
        }
        log.debug("Valida????o de chave ??nica de endpoint... OK");
    }

    public void validaSeRazaoSocialJaExiste(String razaoSocial) {
        log.debug("M??todo de valida????o de chave ??nica de Raz??o Social acessado");
        if (empresaRepositoryImpl.implementaBuscaPorRazaoSocial(razaoSocial).isPresent()) {
            log.warn("A raz??o social informada j?? existe");
            throw new InvalidRequestException("A raz??o social informada j?? existe");
        }
        log.debug("Valida????o de chave ??nica de Raz??o social... OK");
    }

    public void validaSeInscricaoEstadualJaExiste(String inscricaoEstadual) {
        log.debug("M??todo de valida????o de chave ??nica de Inscri????o estadual acessado");
        if (empresaRepositoryImpl.implementaBuscaPorInscricaoEstadual(inscricaoEstadual).isPresent()) {
            log.warn("A inscri????o estadual informada j?? existe");
            throw new InvalidRequestException("A inscri????o estadual informada j?? existe");
        }
        log.debug("Valida????o de chave ??nica de inscri????o estadual... OK");
    }

    public void validaSeInscricaoMunicipalJaExiste(String inscricaoMunicipal) {
        log.debug("M??todo de valida????o de chave ??nica de Inscri????o municipal acessado");
        if (empresaRepositoryImpl.implementaBuscaPorInscricaoMunicipal(inscricaoMunicipal).isPresent()) {
            log.warn("A inscri????o municipal informada j?? existe");
            throw new InvalidRequestException("A inscri????o municipal informada j?? existe");
        }
        log.debug("Valida????o de chave ??nica de inscri????o municipal... OK");
    }

    public void validacaoDeChaveUnicaParaNovaEmpresa(EmpresaDto empresaDto) {

        log.debug("M??todo de valida????o de chave ??nica para cria????o de nova empresa acessado...");

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
        log.debug("M??todo de valida????o de chave ??nica para atualiza????o de empresa acessado...");
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

        log.debug("M??todo de servi??o respons??vel pelo tratamento do objeto recebido e cria????o de nova empresa acessado");
        log.debug("Empresa recebida: {} | Id do cliente: {}", empresaDto, idCliente);

        log.debug("Iniciando acesso ao m??todo de implementa????o de busca de cliente por id...");
        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl.implementaBuscaPorId(idCliente);

        log.debug("Realizando filtro por empresas ativas do cliente...");
        List<EmpresaEntity> empresasAtivasCliente =
                clienteSistema.getEmpresas().stream()
                        .filter((EmpresaEntity filtroEmpresa) -> !filtroEmpresa.getDeletada())
                        .collect(Collectors.toList());

        log.debug("Empresas ativas do cliente: {}", empresasAtivasCliente);

        log.debug("Verificando se cliente j?? possui quantidade limite de empresas cadastradas para o seu plano...");
        if (clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas()
                <= clienteSistema.getEmpresas().size()) {
            log.warn("O cliente j?? possui o n??mero m??ximo de empresas cadastradas: {}. O permitido ??: {}",
                    empresasAtivasCliente.size(), clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas());
            throw new InvalidRequestException("Este cliente j?? possui o n??mero m??ximo de empresas cadastradas em seu plano: "
                    + clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas() + " (max) com "
                    + empresasAtivasCliente.size() + " empresas cadastradas");
        }

        log.debug("Iniciando acesso ao m??todo de valida????o de vari??veis de chave ??nica para empresa...");
        validacaoDeChaveUnicaParaNovaEmpresa(empresaDto);

        log.debug("Iniciando constru????o do objeto EmpresaEntity...");
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
        log.debug("Constru????o de objeto EmpresaEntity realizado com sucesso");

        log.debug("Adicionando a empresa ?? lista de empresas do cliente...");
        clienteSistema.getEmpresas().add(empresaEntity);

        log.debug("Iniciando acesso ao m??todo de implementa????o de persist??ncia do cliente...");
        ClienteSistemaEntity clienteSistemaEntity = clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);

        log.debug("Obtendo empresa criada da lista das empresas do cliente persistido...");
        EmpresaEntity empresaCriada = clienteSistemaEntity.getEmpresas().stream()
                .filter(empresaFiltrada -> empresaFiltrada.getRazaoSocial().equals(empresaDto.getRazaoSocial()))
                .collect(Collectors.toList()).get(0);

        log.debug("Iniciando acesso ao m??todo de cria????o de novo colaborador admin para empresa...");
        ColaboradorEntity colaborador = criaColaboradorAdminParaNovaEmpresa(empresaCriada);

        log.info("Empresa criada com sucesso");
        return CriaEmpresaResponse.builder()
                .idClienteEmpresa(clienteSistemaEntity.getId())
                .colaboradorCriado(colaborador)
                .build();
    }

    public ColaboradorEntity criaColaboradorAdminParaNovaEmpresa(EmpresaEntity empresaEntity) {

        log.debug("M??todo de cria????o de colaborador ADMIN DEFAULT para nova empresa acessado");

        log.debug("Iniciando acesso ao m??todo de gera????o de senha aleat??ria...");
        String senha = geraSenhaAleatoriaParaNovoLogin(empresaEntity);
        log.debug("Senha criada com sucesso");

        log.debug("Criando objeto para definir permiss??es do usu??rio...");
        Set<ModulosEnum> modulosLiberadosUsuario = new HashSet<>();

        log.debug("Iniciando acesso ao m??todo para realizar carga no objeto de permiss??es do usu??rio...");
        habilitaTodosModulosColaborador(modulosLiberadosUsuario);

        log.debug("Construindo objeto e iniciando m??todo de implementa????o de persist??ncia de colaborador...");
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
                        .nomeUsuario(geraNomeUsuarioParaResponsavelEmpresa())
                        .senha(senha)
                        .senhaCriptografada(new BCryptPasswordEncoder().encode(senha))
                        .privilegios(modulosLiberadosUsuario)
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

    public void habilitaTodosModulosColaborador(Set<ModulosEnum> privilegios) {
        log.debug("M??todo de setagem de todos os privil??gios ?? lista de privil??gios do colaborador acessado");
        log.debug("Iniciando setagem de privil??gios...");
        privilegios.add(ModulosEnum.HOME);
        privilegios.add(ModulosEnum.CLIENTES);
        privilegios.add(ModulosEnum.VENDAS);
        privilegios.add(ModulosEnum.LANCAMENTOS);
        privilegios.add(ModulosEnum.ESTOQUE);
        privilegios.add(ModulosEnum.DESPESAS);
        privilegios.add(ModulosEnum.FECHAMENTOS);
        privilegios.add(ModulosEnum.PATRIMONIOS);
        privilegios.add(ModulosEnum.FORNECEDORES);
        privilegios.add(ModulosEnum.COMPRAS);
        privilegios.add(ModulosEnum.COLABORADORES);
        privilegios.add(ModulosEnum.PRECOS);
        log.debug("Lista de privil??gios preenchida com sucesso");
    }

    public String geraNomeUsuarioParaResponsavelEmpresa() {
        log.debug("M??todo de cria????o de username aleat??rio acessado");

        log.debug("Iniciando processo de cria????o de username aleat??rio...");
        int rangeAleatoriedade = 999999 - (100000 + 1);
        boolean nomeUsuarioDisponivel = false;
        String nomeUsuario = null;
        while (!nomeUsuarioDisponivel) {
            nomeUsuario = String.valueOf((int) (Math.random() * rangeAleatoriedade) + 100000);
            log.debug("Nome de usu??rio gerado: {}. Iniciando verifica????o se nome de usu??rio est?? dispon??vel...", nomeUsuario);
            if (!colaboradorRepositoryImpl.implementaBuscaPorNomeUsuario(nomeUsuario).isPresent()) {
                log.debug("O nome de usu??rio gerado est?? dispon??vel. Finalizando itera????o...");
                nomeUsuarioDisponivel = true;
            }
            else {
                log.debug("O nome de usu??rio gerado j?? existe: {}. Iniciando cria????o de novo username...", nomeUsuario);
            }
        }

        log.debug("Nome de usu??rio criado com sucesso");
        return nomeUsuario;
    }

    public String geraSenhaAleatoriaParaNovoLogin(EmpresaEntity empresaEntity) {
        log.debug("M??todo de cria????o de senha aleat??ria acessado");
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

        log.debug("M??todo de servi??o de atualiza????o de empresa acessado");
        log.debug("Id da empresa: {} | Objeto recebido: {}", idEmpresa, empresaDto);

        log.debug("Iniciando acesso ao m??todo de implementa????o de busca de empresa por id...");
        EmpresaEntity empresa = empresaRepositoryImpl.implementaBuscaPorId(idEmpresa);

        log.debug("Iniciando valida????o se empresa a ser alterada foi deletada anteriormente...");
        if (Boolean.TRUE.equals(empresa.getDeletada())) {
            log.warn("A empresa n??o pode ser alterada pois foi deletada. Empresa: {}", empresa);
            throw new InvalidRequestException("N??o ?? poss??vel realizar altera????es em uma empresa que foi removida");
        }

        log.debug("Iniciando acesso ao m??todo de valida????o de chaves ??nicas para a atualiza????o da empresa...");
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
        log.debug("Setagem de atributos finalizada com sucesso");

        log.debug("Iniciando acesso ao m??todo de implementa????o de persist??ncia de empresa...");
        EmpresaEntity empresaAtualizada = empresaRepositoryImpl.implementaPersistencia(empresa);

        log.info("A empresa foi atualizada com sucesso");
        return empresaAtualizada;

    }

    public EmpresaEntity removeEmpresa(Long id) {

        log.debug("M??todo de servi??o de remo????o de empresa acessado. Id recebido: {}", id);

        log.debug("Iniciando acesso ao m??todo de implementa????o de busca de empresa por id...");
        EmpresaEntity empresa = empresaRepositoryImpl.implementaBuscaPorId(id);

        log.debug("Setando atributos de remo????o da empresa ao objeto empresa...");
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
        log.debug("Setagem de atributos de remo????o da empresa finalizados");

        log.debug("Iniciando acesso ao m??todo de implementa????o de persist??ncia de empresa...");
        EmpresaEntity empresaDeletada = empresaRepositoryImpl.implementaPersistencia(empresa);

        log.info("A empresa foi deletada com sucesso");
        return empresaDeletada;
    }

}
