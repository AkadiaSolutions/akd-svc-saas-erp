package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.entities.global.EnderecoEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.models.entities.site.fiscal.ConfigFiscalEmpresaEntity;
import br.akd.svc.akadia.models.entities.site.fiscal.NfceConfigEntity;
import br.akd.svc.akadia.models.entities.site.fiscal.NfeConfigEntity;
import br.akd.svc.akadia.models.entities.site.fiscal.NfseConfigEntity;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadia.repositories.site.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    public void validaSeCnpjJaExiste(String cnpj) {
        if (empresaRepositoryImpl.implementaBuscaPorCnpj(cnpj).isPresent())
            throw new InvalidRequestException("O cnpj informado já existe");
    }

    public void validaSeEndpointJaExiste(String endpoint) {
        if (empresaRepositoryImpl.implementaBuscaPorEndpoint(endpoint).isPresent())
            throw new InvalidRequestException("O endpoint informado já existe");
    }

    public ClienteSistemaEntity criaNovaEmpresa(Long idCliente, EmpresaDto empresaDto) {

        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl.implementaBuscaPorId(idCliente);

        if (clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas() <= clienteSistema.getEmpresas().size())
            throw new InvalidRequestException("Este cliente já possui o número máximo de empresas cadastradas em seu plano: "
                    + clienteSistema.getPlano().getTipoPlanoEnum().getQtdLimiteEmpresasCadastradas() + " (max) com "
                    + clienteSistema.getEmpresas().size() + " empresas cadastradas");

        validaSeCnpjJaExiste(empresaDto.getCnpj());
        validaSeEndpointJaExiste(empresaDto.getEndpoint());

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
        return clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
    }

}
