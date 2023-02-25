package br.akd.svc.akadia.repositories.sistema.colaboradores.impl;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ColaboradorRepositoryImpl {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public List<ColaboradorEntity> buscaTodosOsColaboradores() {
        log.debug("Método de serviço que implementa busca por todos os colaboradores cadastrados acessado");
        return colaboradorRepository.findAll();
    }

    @Transactional
    public ColaboradorEntity implementaPersistencia(ColaboradorEntity colaborador) {
        log.debug("Método de serviço que implementa persistência do colaborador acessado");
        return colaboradorRepository.save(colaborador);
    }

}
