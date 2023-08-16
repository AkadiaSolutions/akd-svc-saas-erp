package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.requests.ClienteRequest;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClienteValidationService {

    @Autowired
    ClienteRepositoryImpl clienteRepositoryImpl;

    public void validaSeChavesUnicasJaExistemParaNovoCliente(ClienteRequest clienteRequest, ColaboradorEntity colaboradorLogado) {
        log.debug("Método de validação de chave única de cliente acessado...");
        if (clienteRequest.getCpfCnpj() != null)
            validaSeCpfCnpjJaExiste(clienteRequest.getCpfCnpj(), colaboradorLogado.getEmpresa().getId());
        if (clienteRequest.getInscricaoEstadual() != null)
            validaSeInscricaoEstadualJaExiste(clienteRequest.getInscricaoEstadual(), colaboradorLogado.getEmpresa().getId());
    }

    public void validaSeChavesUnicasJaExistemParaClienteAtualizado(ClienteRequest clienteRequest,
                                                                   ClienteEntity clienteEntity,
                                                                   ColaboradorEntity colaboradorLogado) {
        log.debug("Método de validação de chave única para atualização de cliente acessado...");
        if (clienteRequest.getCpfCnpj() != null && clienteEntity.getCpfCnpj() == null
                || clienteRequest.getCpfCnpj() != null
                && !clienteEntity.getCpfCnpj().equals(clienteRequest.getCpfCnpj()))
            validaSeCpfCnpjJaExiste(clienteRequest.getCpfCnpj(), colaboradorLogado.getEmpresa().getId());
        if (clienteRequest.getInscricaoEstadual() != null && clienteEntity.getInscricaoEstadual() == null
                || clienteRequest.getInscricaoEstadual() != null
                && !clienteEntity.getInscricaoEstadual().equalsIgnoreCase(clienteRequest.getInscricaoEstadual()))
            validaSeInscricaoEstadualJaExiste(clienteRequest.getInscricaoEstadual(), colaboradorLogado.getEmpresa().getId());
    }

    public void validaSeCpfCnpjJaExiste(String cpfCnpj, Long idEmpresa) {
        log.debug("Método de validação de chave única de CPF/CNPJ acessado");
        if (clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(cpfCnpj, idEmpresa).isPresent()) {
            String mensagemErro = cpfCnpj.length() == 14 ? "O CPF informado já existe" : "O CNPJ informado já existe";
            log.warn(mensagemErro + ": {}", cpfCnpj);
            throw new InvalidRequestException(mensagemErro);
        }
        log.debug("Validação de chave única de CPF/CNPJ... OK");
    }

    public void validaSeInscricaoEstadualJaExiste(String inscricaoEstadual, Long idEmpresa) {
        log.debug("Método de validação de chave única de EMAIL acessado");
        if (clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualIdentica(inscricaoEstadual, idEmpresa).isPresent()) {
            log.warn("A inscrição estadual informada já existe");
            throw new InvalidRequestException("A inscrição estadual informada já existe");
        }
        log.debug("Validação de chave única de INSCRIÇÃO ESTADUAL... OK");
    }

    public void validaSeClienteEstaExcluido(ClienteEntity cliente, String mensagemCasoEstejaExcluido) {
        log.debug("Método de validação de cliente excluído acessado");
        if (cliente.getExclusao() != null) {
            log.debug("Cliente de id {}: Validação de cliente já excluído falhou. Não é possível realizar operações " +
                    "em um cliente que já foi excluído.", cliente.getId());
            throw new InvalidRequestException(mensagemCasoEstejaExcluido);
        }
        log.debug("O cliente de id {} não está excluído", cliente.getId());
    }

}
