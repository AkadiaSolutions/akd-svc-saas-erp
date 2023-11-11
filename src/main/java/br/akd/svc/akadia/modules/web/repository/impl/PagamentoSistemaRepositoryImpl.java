package br.akd.svc.akadia.modules.web.repository.impl;

import br.akd.svc.akadia.modules.web.models.entity.PagamentoSistemaEntity;
import br.akd.svc.akadia.modules.web.repository.PagamentoSistemaRepository;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PagamentoSistemaRepositoryImpl {

    @Autowired
    PagamentoSistemaRepository pagamentoSistemaRepository;

    public PagamentoSistemaEntity implementaBuscaPorCodigoPagamentoAsaas(String codigoPagamentoAsaas) {
        log.debug("Método de implementação de busca de pagamento por Codigo ASAAS acessado. Código recebido: {}", codigoPagamentoAsaas);
        Optional<PagamentoSistemaEntity> pagamentoOptional =
                pagamentoSistemaRepository.findByCodigoPagamentoAsaas(codigoPagamentoAsaas);

        PagamentoSistemaEntity pagamento;
        if (pagamentoOptional.isPresent()) {
            pagamento = pagamentoOptional.get();
            log.debug("Pagamento encontrado: {}", pagamento);
        }
        else {
            log.warn("Nenhum pagamento foi encontrado com o código ASAAS informado: {}", codigoPagamentoAsaas);
            throw new ObjectNotFoundException("Nenhum pagamento foi encontrado com o codigo Asaas informado");
        }

        log.debug("Retornando pagamento encontrado...");
        return pagamento;
    }

}
