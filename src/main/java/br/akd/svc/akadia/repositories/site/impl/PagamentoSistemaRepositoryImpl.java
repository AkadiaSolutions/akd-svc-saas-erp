package br.akd.svc.akadia.repositories.site.impl;

import br.akd.svc.akadia.models.entities.site.PagamentoSistemaEntity;
import br.akd.svc.akadia.repositories.site.PagamentoSistemaRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoSistemaRepositoryImpl {

    @Autowired
    PagamentoSistemaRepository pagamentoSistemaRepository;

    public PagamentoSistemaEntity implementaBuscaPorCodigoPagamentoAsaas(String codigoPagamentoAsaas) {
        Optional<PagamentoSistemaEntity> pagamentoOptional =
                pagamentoSistemaRepository.findByCodigoPagamentoAsaas(codigoPagamentoAsaas);

        PagamentoSistemaEntity pagamento;
        if (pagamentoOptional.isPresent()) pagamento = pagamentoOptional.get();
        else throw new ObjectNotFoundException("Nenhum pagamento foi encontrado com o codigo Asaas informado");

        return pagamento;
    }

}
