package br.akd.svc.akadia.repositories.sistema.colaboradores.impl;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ColaboradorRepositoryImpl {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public List<ColaboradorEntity> implementaBuscaTodosOsColaboradores() {
        log.debug("Método de serviço que implementa busca por todos os colaboradores cadastrados acessado");
        return colaboradorRepository.findAll();
    }

    public Optional<ColaboradorEntity> implementaBuscaPorNomeUsuario(String nomeUsuario) {
        log.debug("Método de serviço que implementa busca por nome de usuário do colaborador");
        return colaboradorRepository.buscaPorUsername(nomeUsuario);
    }

    @Transactional
    public ColaboradorEntity implementaPersistencia(ColaboradorEntity colaborador) {
        log.debug("Método de serviço que implementa persistência do colaborador acessado");
        return colaboradorRepository.save(colaborador);
    }

}
