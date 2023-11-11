package br.akd.svc.akadia.modules.erp.produtos.repository.impl;

import br.akd.svc.akadia.modules.erp.produtos.models.entity.ProdutoEntity;
import br.akd.svc.akadia.modules.erp.produtos.models.entity.id.ProdutoId;
import br.akd.svc.akadia.modules.erp.produtos.repository.ProdutoRepository;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProdutoRepositoryImpl {
    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoEntity implementaPersistencia(ProdutoEntity produtoEntity) {
        log.debug("Método de serviço que implementa persistência do objeto");
        return produtoRepository.save(produtoEntity);
    }

    public ProdutoEntity implementaBuscaPorId(ProdutoId produtoId) {
        log.debug("Método que implementa busca de produto por id acessado. Id: {}", produtoId);
        return produtoRepository
                .findById(produtoId)
                .orElseThrow(() -> new ObjectNotFoundException("Nenhum produto foi encontrado com o id informado"));
    }
}
