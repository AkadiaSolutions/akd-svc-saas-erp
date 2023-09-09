package br.akd.svc.akadia.repositories.sistema.produtos.impl;

import br.akd.svc.akadia.models.entities.sistema.produto.ProdutoEntity;
import br.akd.svc.akadia.repositories.sistema.produtos.ProdutoRepository;
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
}
