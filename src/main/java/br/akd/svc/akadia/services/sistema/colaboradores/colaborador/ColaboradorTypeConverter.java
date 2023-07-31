package br.akd.svc.akadia.services.sistema.colaboradores.colaborador;

import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorPageResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.ColaboradorResponse;
import br.akd.svc.akadia.models.dto.sistema.colaboradores.responses.acesso.AcessoSistemaResponse;
import br.akd.svc.akadia.models.entities.global.ExclusaoEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ColaboradorTypeConverter {
    public ColaboradorPageResponse converteListaDeColaboradoresEntityParaColaboradoresResponse(Page<ColaboradorEntity> colaboradoresEntity) {
        log.debug("Método de conversão de colaboradores do tipo Entity para colaboradores do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo ColaboradorResponse...");
        List<ColaboradorResponse> colaboradoresResponse = new ArrayList<>();

        log.debug("Iniciando iteração da lista de ColaboradorEntity obtida na busca para conversão para objetos do tipo " +
                "ColaboradorResponse...");
        for (ColaboradorEntity colaborador : colaboradoresEntity.getContent()) {
            ColaboradorResponse colaboradorResponse = ColaboradorResponse.builder()
                    .id(colaborador.getId())
                    .dataCadastro(colaborador.getDataCadastro())
                    .horaCadastro(colaborador.getHoraCadastro())
                    .matricula(colaborador.getMatricula())
                    .nome(colaborador.getNome())
                    .dataNascimento(colaborador.getDataNascimento())
                    .email(colaborador.getEmail())
                    .cpfCnpj(colaborador.getCpfCnpj())
                    .salario(colaborador.getSalario())
                    .entradaEmpresa(colaborador.getEntradaEmpresa())
                    .saidaEmpresa(colaborador.getSaidaEmpresa())
                    .contratoContratacao(colaborador.getContratoContratacao())
                    .ocupacao(colaborador.getOcupacao())
                    .tipoOcupacaoEnum(colaborador.getTipoOcupacaoEnum())
                    .modeloContratacaoEnum(colaborador.getModeloContratacaoEnum())
                    .modeloTrabalhoEnum(colaborador.getModeloTrabalhoEnum())
                    .statusColaboradorEnum(colaborador.getStatusColaboradorEnum())
                    .acessoSistema(AcessoSistemaResponse.builder()
                            .acessoSistemaAtivo(colaborador.getAcessoSistema().getAcessoSistemaAtivo())
                            .permissaoEnum(colaborador.getAcessoSistema().getPermissaoEnum())
                            .privilegios(colaborador.getAcessoSistema().getPrivilegios())
                            .build())
                    .configuracaoPerfil(colaborador.getConfiguracaoPerfil())
                    .exclusao(colaborador.getExclusao() != null
                            ? ExclusaoEntity.builder()
                            .dataExclusao(colaborador.getExclusao().getDataExclusao())
                            .horaExclusao(colaborador.getExclusao().getHoraExclusao())
                            .build()
                            : null)
                    .fotoPerfil(colaborador.getFotoPerfil())
                    .endereco(colaborador.getEndereco())
                    .telefone(colaborador.getTelefone())
                    .expediente(colaborador.getExpediente())
                    .dispensa(colaborador.getDispensa())
                    .pontos(colaborador.getPontos())
                    .historicoFerias(colaborador.getHistoricoFerias())
                    .advertencias(colaborador.getAdvertencias())
                    .build();
            colaboradoresResponse.add(colaboradorResponse);
        }
        log.debug("Iteração finalizada com sucesso. Listagem de objetos do tipo ColaboradorResponse preenchida");

        log.debug("Iniciando criação de objeto do tipo ColaboradorPageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        colaboradoresEntity.getPageable();
        ColaboradorPageResponse colaboradorPageResponse = ColaboradorPageResponse.builder()
                .content(colaboradoresResponse)
                .empty(colaboradoresEntity.isEmpty())
                .first(colaboradoresEntity.isFirst())
                .last(colaboradoresEntity.isLast())
                .number(colaboradoresEntity.getNumber())
                .numberOfElements(colaboradoresEntity.getNumberOfElements())
                .offset(colaboradoresEntity.getPageable().getOffset())
                .pageNumber(colaboradoresEntity.getPageable().getPageNumber())
                .pageSize(colaboradoresEntity.getPageable().getPageSize())
                .paged(colaboradoresEntity.getPageable().isPaged())
                .unpaged(colaboradoresEntity.getPageable().isUnpaged())
                .size(colaboradoresEntity.getSize())
                .totalElements(colaboradoresEntity.getTotalElements())
                .totalPages(colaboradoresEntity.getTotalPages())
                .build();

        log.debug("Objeto do tipo ColaboradorPageResponse criado com sucesso. Retornando objeto...");
        return colaboradorPageResponse;
    }

    public ColaboradorResponse converteColaboradorEntityParaColaboradorResponse(ColaboradorEntity colaborador) {
        log.debug("Método de conversão de objeto do tipo ColaboradorEntity para objeto do tipo ColaboradorResponse acessado");

        log.debug("Iniciando construção do objeto ColaboradorResponse...");
        ColaboradorResponse colaboradorResponse = ColaboradorResponse.builder()
                .id(colaborador.getId())
                .dataCadastro(colaborador.getDataCadastro())
                .horaCadastro(colaborador.getHoraCadastro())
                .nome(colaborador.getNome())
                .matricula(colaborador.getMatricula())
                .dataNascimento(colaborador.getDataNascimento())
                .email(colaborador.getEmail())
                .cpfCnpj(colaborador.getCpfCnpj())
                .salario(colaborador.getSalario())
                .entradaEmpresa(colaborador.getEntradaEmpresa())
                .saidaEmpresa(colaborador.getSaidaEmpresa())
                .contratoContratacao(colaborador.getContratoContratacao())
                .ocupacao(colaborador.getOcupacao())
                .tipoOcupacaoEnum(colaborador.getTipoOcupacaoEnum())
                .modeloTrabalhoEnum(colaborador.getModeloTrabalhoEnum())
                .modeloContratacaoEnum(colaborador.getModeloContratacaoEnum())
                .statusColaboradorEnum(colaborador.getStatusColaboradorEnum())
                .acessoSistema(AcessoSistemaResponse.builder()
                        .acessoSistemaAtivo(colaborador.getAcessoSistema().getAcessoSistemaAtivo())
                        .permissaoEnum(colaborador.getAcessoSistema().getPermissaoEnum())
                        .privilegios(colaborador.getAcessoSistema().getPrivilegios())
                        .build())
                .fotoPerfil(colaborador.getFotoPerfil())
                .configuracaoPerfil(colaborador.getConfiguracaoPerfil())
                .expediente(colaborador.getExpediente())
                .dispensa(colaborador.getDispensa())
                .pontos(colaborador.getPontos())
                .historicoFerias(colaborador.getHistoricoFerias())
                .exclusao(colaborador.getExclusao() != null
                        ? ExclusaoEntity.builder()
                        .dataExclusao(colaborador.getExclusao().getDataExclusao())
                        .horaExclusao(colaborador.getExclusao().getHoraExclusao())
                        .build()
                        : null)
                .endereco(colaborador.getEndereco())
                .telefone(colaborador.getTelefone())
                .build();
        log.debug("Objeto ColaboradorResponse buildado com sucesso. Retornando...");
        return colaboradorResponse;
    }

}
