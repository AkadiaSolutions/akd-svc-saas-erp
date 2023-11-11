package br.akd.svc.akadia.modules.erp.clientes.services;

import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.page.ClientePageResponse;
import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.ClienteResponse;
import br.akd.svc.akadia.modules.global.entity.ExclusaoEntity;
import br.akd.svc.akadia.modules.erp.clientes.models.entity.ClienteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ClienteTypeConverter {

    public ClientePageResponse converteListaDeClientesEntityParaClientesResponse(Page<ClienteEntity> clientesEntity) {
        log.debug("Método de conversão de clientes do tipo Entity para clientes do tipo Response acessado");

        log.debug("Criando lista vazia de objetos do tipo ClienteResponse...");
        List<ClienteResponse> clientesResponse = new ArrayList<>();

        log.debug("Iniciando iteração da lista de ClienteEntity obtida na busca para conversão para objetos do tipo " +
                "ClienteResponse...");
        for (ClienteEntity cliente : clientesEntity.getContent()) {
            ClienteResponse clienteResponse = ClienteResponse.builder()
                    .id(cliente.getId())
                    .dataCadastro(cliente.getDataCadastro())
                    .horaCadastro(cliente.getHoraCadastro())
                    .dataNascimento(cliente.getDataNascimento())
                    .nome(cliente.getNome())
                    .cpfCnpj(cliente.getCpfCnpj())
                    .inscricaoEstadual(cliente.getInscricaoEstadual())
                    .email(cliente.getEmail())
                    .statusCliente(cliente.getStatusCliente())
                    .tipoPessoa(cliente.getTipoPessoa())
                    .qtdOrdensRealizadas(cliente.getQtdOrdensRealizadas())
                    .giroTotal(cliente.getGiroTotal())
                    .exclusaoEntity(cliente.getExclusao() != null
                            ? ExclusaoEntity.builder()
                            .dataExclusao(cliente.getExclusao().getDataExclusao())
                            .horaExclusao(cliente.getExclusao().getHoraExclusao())
                            .responsavelExclusao(cliente.getExclusao().getResponsavelExclusao())
                            .build()
                            : null)
                    .endereco(cliente.getEndereco())
                    .telefone(cliente.getTelefone())
                    .nomeColaboradorResponsavel(cliente.getColaboradorResponsavel().getNome())
                    .build();
            clientesResponse.add(clienteResponse);
        }
        log.debug("Iteração finalizada com sucesso. Listagem de objetos do tipo ClienteResponse preenchida");

        log.debug("Iniciando criação de objeto do tipo ClientePageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        clientesEntity.getPageable();
        ClientePageResponse clientePageResponse = ClientePageResponse.builder()
                .content(clientesResponse)
                .empty(clientesEntity.isEmpty())
                .first(clientesEntity.isFirst())
                .last(clientesEntity.isLast())
                .number(clientesEntity.getNumber())
                .numberOfElements(clientesEntity.getNumberOfElements())
                .pageNumber(clientesEntity.getPageable().getPageNumber())
                .pageSize(clientesEntity.getPageable().getPageSize())
                .paged(clientesEntity.getPageable().isPaged())
                .unpaged(clientesEntity.getPageable().isUnpaged())
                .size(clientesEntity.getSize())
                .totalElements(clientesEntity.getTotalElements())
                .totalPages(clientesEntity.getTotalPages())
                .build();

        log.debug("Objeto do tipo ClientePageResponse criado com sucesso. Retornando objeto...");
        return clientePageResponse;
    }

    public ClienteResponse converteClienteEntityParaClienteResponse(ClienteEntity cliente) {
        log.debug("Método de conversão de objeto do tipo ClienteEntity para objeto do tipo ClienteResponse acessado");

        log.debug("Iniciando construção do objeto ClienteResponse...");
        ClienteResponse clienteResponse = ClienteResponse.builder()
                .id(cliente.getId())
                .dataCadastro(cliente.getDataCadastro())
                .horaCadastro(cliente.getHoraCadastro())
                .dataNascimento(cliente.getDataNascimento())
                .nome(cliente.getNome())
                .cpfCnpj(cliente.getCpfCnpj())
                .inscricaoEstadual(cliente.getInscricaoEstadual())
                .email(cliente.getEmail())
                .statusCliente(cliente.getStatusCliente())
                .tipoPessoa(cliente.getTipoPessoa())
                .qtdOrdensRealizadas(cliente.getQtdOrdensRealizadas())
                .giroTotal(cliente.getGiroTotal())
                .exclusaoEntity(cliente.getExclusao() != null
                        ? ExclusaoEntity.builder()
                        .dataExclusao(cliente.getExclusao().getDataExclusao())
                        .horaExclusao(cliente.getExclusao().getHoraExclusao())
                        .responsavelExclusao(cliente.getExclusao().getResponsavelExclusao())
                        .build()
                        : null)
                .endereco(cliente.getEndereco())
                .telefone(cliente.getTelefone())
                .nomeColaboradorResponsavel(cliente.getColaboradorResponsavel().getNome())
                .build();
        log.debug("Objeto ClienteResponse buildado com sucesso. Retornando...");
        return clienteResponse;
    }

}
